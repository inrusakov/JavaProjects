package sample.Utility;

import sample.InterfacesAnnotations.*;

import java.lang.reflect.*;

import java.util.*;

public class Validator implements IValidator {
    @Override
    public Set<ValidationError> validate(Object object) {
        Set<ValidationError> report = new HashSet<ValidationError>();
        if (!Arrays.toString(object.getClass().getDeclaredAnnotations()).contains("Constrained"))
            return report;
        // Проверка полей простого объекта.
        for (Field f : object.getClass().getDeclaredFields()) {
            AnnotatedType annotatedType = f.getAnnotatedType();
            f.setAccessible(true);
            String reportName = object.getClass().getSimpleName() + "." + f.getName();

            try {
                Object value = f.get(object);
                if (value != null && annotatedType != null) {
                    // Если внутри объекта есть другой объект с Constrained.
                    var res = Arrays.stream(value.getClass().
                            getDeclaredAnnotations()).
                            filter(str -> str.toString().
                                    contains("Constrained")).
                            findAny().orElse(null);
                    if (res != null) {
                        Set<ValidationError> addition = validate(value);
                        if (addition.size() != 0)
                            report.addAll(addition);
                    }
                    // Если внутри есть коллекция.
                    if (value instanceof Collection<?>) {
                        Set<ValidationError> addition = validateCollection(value, annotatedType, reportName, object);
                        if (addition.size() != 0)
                            report.addAll(addition);
                    }
                }
            } catch (NullPointerException e) {
                report.add(new ValidationError((reportName + " cannot be null!"), reportName, object));
            } catch (IllegalAccessException e) {
                report.add(new ValidationError(("Illegal access error!"), reportName, object));
            } catch (IllegalArgumentException e) {
                report.add(new ValidationError((e.getMessage()), reportName, object));
            }

            // Проверка NotNull.
            if (Arrays.toString(annotatedType.getAnnotations()).contains("NotNull")) {
                try {
                    Object value = f.get(object);
                    if (validateNotNull(value, reportName) != null)
                        report.add(validateNotNull(value, reportName));
                } catch (NullPointerException e) {
                    report.add(new ValidationError((f.getName() + " cannot be Null!"), reportName, object));
                } catch (IllegalAccessException e) {
                    report.add(new ValidationError(("Illegal access error!"), reportName, object));
                } catch (IllegalArgumentException e) {
                    report.add(new ValidationError((e.getMessage()), reportName, object));
                }
            }

            // Проверка Positive.
            if (Arrays.toString(annotatedType.getAnnotations()).contains("Positive")) {
                try {
                    var value = f.get(object);
                    if (value != null)
                        if (value instanceof Integer)
                            if (validatePositive((int) value, reportName) != null)
                                report.add(validateNegative((int) value, reportName));
                } catch (NullPointerException e) {
                    report.add(new ValidationError((f.getName() + " cannot be Negative!"), reportName, object));
                } catch (IllegalAccessException e) {
                    report.add(new ValidationError(("Illegal access error!"), reportName, object));
                } catch (IllegalArgumentException e) {
                    report.add(new ValidationError((e.getMessage()), reportName, object));
                }
            }

            // Проверка Negative.
            if (Arrays.toString(annotatedType.getAnnotations()).contains("Negative")) {
                try {
                    var value = f.get(object);
                    if (value != null)
                        if (value instanceof Integer)
                            if (validateNegative((int) value, reportName) != null)
                                report.add(validateNegative((int) value, reportName));
                } catch (NullPointerException e) {
                    report.add(new ValidationError((f.getName() + " cannot be Positive!"), reportName, object));
                } catch (IllegalAccessException e) {
                    report.add(new ValidationError(("Illegal access error!"), reportName, object));
                } catch (IllegalArgumentException e) {
                    report.add(new ValidationError((e.getMessage()), reportName, object));
                }
            }

            // Проверка NotBlank.
            if (Arrays.toString(annotatedType.getAnnotations()).contains("NotBlank")) {
                try {
                    Object value = f.get(object);
                    if (value != null)
                        if (validateNotBlank(value, object, reportName) != null)
                            report.add(validateNotBlank(value, object, reportName));
                } catch (NullPointerException e) {
                    report.add(new ValidationError((f.getName() + " cannot be blank!"), reportName, object));
                } catch (IllegalAccessException e) {
                    report.add(new ValidationError(("Illegal access error!"), reportName, object));
                } catch (IllegalArgumentException e) {
                    report.add(new ValidationError((e.getMessage()), reportName, object));
                }
            }

            // Проверка NotEmpty.
            if (Arrays.toString(annotatedType.getAnnotations()).contains("NotEmpty")) {
                try {
                    Object value = f.get(object);
                    if (value != null)
                        if (validateNotEmpty(value, object, reportName) != null)
                            report.add(validateNotEmpty(value, object, reportName));
                } catch (NullPointerException e) {
                    report.add(new ValidationError((f.getName() + " cannot be empty!"), reportName, object));
                } catch (IllegalAccessException e) {
                    report.add(new ValidationError(("Illegal access error!"), reportName, object));
                } catch (IllegalArgumentException e) {
                    report.add(new ValidationError((e.getMessage()), reportName, object));
                }
            }

            // Проверка Size.
            if (Arrays.toString(annotatedType.getAnnotations()).contains("Size")) {
                try {
                    Size mina = (Size) Arrays.stream(Arrays.stream(annotatedType.getAnnotations())
                            .toArray()).filter(str -> str.toString().contains("Size")).findAny().orElse(null);
                    long max = mina != null ? mina.max() : 0;
                    long min = mina != null ? mina.min() : 0;
                    Object value = f.get(object);
                    if (value != null)
                        if (validateSize(value, object, reportName, min, max) != null)
                            report.add(validateSize(value, object, reportName, min, max));
                } catch (NullPointerException e) {
                    report.add(new ValidationError((f.getName() + " cannot be null!"), reportName, object));
                } catch (IllegalAccessException e) {
                    report.add(new ValidationError(("Illegal access error!"), reportName, object));
                } catch (IllegalArgumentException e) {
                    report.add(new ValidationError((e.getMessage()), reportName, object));
                }
            }

            // Проверка InRange.
            if (Arrays.toString(annotatedType.getAnnotations()).contains("InRange")) {
                try {
                    InRange mina = (InRange) Arrays.stream(Arrays.stream(annotatedType.getAnnotations())
                            .toArray()).filter(str -> str.toString().contains("InRange")).findAny().orElse(null);
                    long max = mina.max(), min = mina.min();
                    var value = f.get(object);
                    if (value != null)
                        if (value instanceof Integer)
                            if (validateInRange((int) value, object, reportName, min, max) != null)
                                report.add(validateInRange((int) value, object, reportName, min, max));
                } catch (NullPointerException e) {
                    report.add(new ValidationError((f.getName() + " cannot be null!"), reportName, object));
                } catch (IllegalAccessException e) {
                    report.add(new ValidationError(("Illegal access error!"), reportName, object));
                } catch (IllegalArgumentException e) {
                    report.add(new ValidationError((e.getMessage()), reportName, object));
                }
            }

            // Проверка AnyOf.
            if (Arrays.toString(annotatedType.getAnnotations()).contains("AnyOf")) {
                try {
                    AnyOf mina = (AnyOf) Arrays.stream(Arrays.stream(annotatedType.getAnnotations())
                            .toArray()).filter(str -> str.toString().contains("AnyOf")).findAny().orElse(null);
                    String[] params = Objects.requireNonNull(mina).value();
                    String value = "";
                    if (f.get(object) != null)
                        value = (String) f.get(object);
                    if (validateAnyOf(value, object, reportName, params) != null)
                        report.add(validateAnyOf(value, object, reportName, params));
                } catch (NullPointerException e) {
                    report.add(new ValidationError((f.getName() + " cannot be null!"), reportName, object));
                } catch (IllegalAccessException e) {
                    report.add(new ValidationError(("Illegal access error!"), reportName, object));
                } catch (IllegalArgumentException e) {
                    report.add(new ValidationError((e.getMessage()), reportName, object));
                }
            }
        }
        return report;
    }

    public Set<ValidationError> validateCollection(Object val, AnnotatedType f, String name, Object obj) {
        Set<ValidationError> bigReport = new HashSet<>();
        if (val instanceof Collection<?>) {
            Set<ValidationError> report = new HashSet<>();
            for (Object value : (Collection<?>) val) {
                Set<ValidationError> addition = new HashSet<>();
                AnnotatedParameterizedType apt = (AnnotatedParameterizedType) f;
                for (AnnotatedType typeArg : apt.getAnnotatedActualTypeArguments()) {

                    // Проверка коллекции на вложенные объекты Constrained или другие коллекции.
                    String reportName = name + "[" + ((List<?>) val).indexOf(value) + "]";
                    if (typeArg != null) {
                        if (value instanceof Collection<?>) {
                            addition = validateCollection(value, typeArg, reportName, obj);
                            if (addition.size() != 0)
                                report.addAll(addition);
                        }

                        var res = Arrays.stream(value.getClass().
                                getDeclaredAnnotations()).
                                filter(str -> str.toString().
                                        contains("Constrained")).
                                findAny().orElse(null);
                        if (res != null) {
                            addition.addAll(validate(value));
                            if (addition.size() != 0)
                                report.addAll(addition);
                        }
                    }

                    // Проверка NotNull.
                    if (Arrays.toString(typeArg.getAnnotations()).contains("NotNull")) {
                        try {
                            if (validateNotNull(value, reportName) != null)
                                addition.add(validateNotNull(value, reportName));
                        } catch (Exception e) {
                            report.add(new ValidationError((e.getMessage()), reportName, obj));
                        }
                    }

                    // Проверка Positive.
                    if (Arrays.toString(typeArg.getAnnotations()).contains("Positive")) {
                        try {
                            if (value != null)
                                if (value instanceof Number)
                                    if (validatePositive((int) value, reportName) != null)
                                        report.add(validateNegative((int) value, reportName));
                        } catch (Exception e) {
                            addition.add(new ValidationError((e.getMessage()), reportName, obj));
                        }
                    }

                    // Проверка Negative.
                    if (Arrays.toString(typeArg.getAnnotations()).contains("Negative")) {
                        try {
                            if (value != null)
                                if (value instanceof Number)
                                    if (validateNegative((int) value, reportName) != null)
                                        report.add(validateNegative((int) value, reportName));
                        } catch (Exception e) {
                            addition.add(new ValidationError((e.getMessage()), reportName, obj));
                        }
                    }

                    // Проверка NotBlank.
                    if (Arrays.toString(typeArg.getAnnotations()).contains("NotBlank")) {
                        try {
                            if (value != null)
                                if (validateNotBlank(value, obj, reportName) != null)
                                    report.add(validateNotBlank(value, obj, reportName));
                        } catch (Exception e) {
                            addition.add(new ValidationError((e.getMessage()), reportName, obj));
                        }
                    }

                    // Проверка NotEmpty.
                    if (Arrays.toString(typeArg.getAnnotations()).contains("NotEmpty")) {
                        try {
                            if (value != null)
                                if (validateNotEmpty(value, obj, reportName) != null)
                                    report.add(validateNotEmpty(value, obj, reportName));
                        } catch (Exception e) {
                            addition.add(new ValidationError((e.getMessage()), reportName, obj));
                        }
                    }

                    // Проверка Size.
                    if (Arrays.toString(typeArg.getAnnotations()).contains("Size")) {
                        try {
                            Size mina = (Size) Arrays.stream(Arrays.stream(typeArg.getAnnotations())
                                    .toArray()).filter(str -> str.toString().contains("Size")).findAny().orElse(null);
                            long max = mina != null ? mina.max() : 0;
                            long min = mina != null ? mina.min() : 0;
                            if (value != null)
                                if (validateSize(value, obj, reportName, min, max) != null)
                                    report.add(validateSize(value, obj, reportName, min, max));
                        } catch (Exception e) {
                            addition.add(new ValidationError((e.getMessage()), reportName, obj));
                        }
                    }

                    // Проверка InRange.
                    if (Arrays.toString(typeArg.getAnnotations()).contains("InRange")) {
                        try {
                            InRange mina = (InRange) Arrays.stream(Arrays.stream(typeArg.getAnnotations())
                                    .toArray()).filter(str -> str.toString().contains("InRange")).findAny().orElse(null);
                            long max = mina != null ? mina.max() : 0;
                            long min = mina != null ? mina.min() : 0;
                            if (value != null)
                                if (validateInRange((int) value, obj, reportName, min, max) != null)
                                    report.add(validateInRange((int) value, obj, reportName, min, max));
                        } catch (Exception e) {
                            addition.add(new ValidationError((e.getMessage()), reportName, obj));
                        }
                    }

                    // Проверка AnyOf.
                    if (Arrays.toString(typeArg.getAnnotations()).contains("AnyOf")) {
                        try {
                            AnyOf mina = (AnyOf) Arrays.stream(Arrays.stream(typeArg.getAnnotations())
                                    .toArray()).filter(str -> str.toString().contains("AnyOf")).findAny().orElse(null);
                            String[] params = Objects.requireNonNull(mina).value();
                            if (validateAnyOf((String) value, obj, reportName, params) != null)
                                report.add(validateAnyOf((String) value, obj, reportName, params));
                        } catch (Exception e) {
                            addition.add(new ValidationError((e.getMessage()), reportName, obj));
                        }
                    }
                    if (addition.size() > 0)
                        report.addAll(addition);
                }
            }
            bigReport.addAll(report);
        }
        return bigReport;
    }

    public ValidationError validateNotEmpty(Object value, Object object, String name) {
        if (value.getClass() == String.class)
            if (((String) value).isEmpty())
                throw new IllegalArgumentException(name + " cannot be empty!");

        if (value instanceof Collection<?>)
            if (((Collection<?>) value).isEmpty())
                throw new IllegalArgumentException(name + " cannot be empty!");
        return null;
    }

    public ValidationError validateAnyOf(String value, Object object, String name, String[] params) {
        String res = Arrays.stream(params)
                .filter(str -> str.contains(value))
                .findAny()
                .orElse(null);
        if (res == null)
            throw new IllegalArgumentException(value + " is not allowed!");
        return null;
    }

    public ValidationError validateSize(Object value, Object object, String name, long min, long max) {
        if (value.getClass() == String.class)
            if (((String) value).length() < min || ((String) value).length() > max)
                throw new IllegalArgumentException(name + " has wrong size!");

        if (value instanceof Collection<?>)
            if (((Collection<?>) value).size() < min || ((Collection<?>) value).size() > max)
                throw new IllegalArgumentException(name + " has wrong size!");
        return null;
    }

    public ValidationError validateNotBlank(Object value, Object object, String name) {
        if (value.getClass() == String.class)
            if (((String) value).isBlank())
                throw new IllegalArgumentException(name + " cannot be blank!");
        return null;
    }

    public ValidationError validateNegative(int value, String name) {
        if (value > 0)
            throw new IllegalArgumentException(name + " cannot be positive!");
        return null;
    }

    public ValidationError validatePositive(int value, String name) {
        if (value < 0)
            throw new IllegalArgumentException(name + " cannot be negative!");
        return null;
    }

    public ValidationError validateNotNull(Object value, String name) {
        if (value == null)
            throw new IllegalArgumentException(name + " cannot be null!");
        return null;
    }

    public ValidationError validateInRange(int value, Object object, String name, long min, long max) {
        if (value < min || value > max)
            throw new IllegalArgumentException(name + " is not in appropriate range!");
        return null;
    }
}



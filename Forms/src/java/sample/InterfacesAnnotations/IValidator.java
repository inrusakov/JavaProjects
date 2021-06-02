package sample.InterfacesAnnotations;

import sample.Utility.ValidationError;

import java.util.Set;

public interface IValidator {
    Set<ValidationError> validate(Object object);
}

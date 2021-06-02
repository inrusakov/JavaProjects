package sample.InterfacesAnnotations;

public interface IValidationError {
    String getMessage();
    String getPath();
    Object getFailedValue();
}

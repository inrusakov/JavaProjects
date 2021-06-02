package sample.Utility;

import sample.InterfacesAnnotations.IValidationError;

public class ValidationError implements IValidationError {
    private String message;
    private String path;
    private Object failedValue;

    ValidationError(String mess, String pat, Object val){
        message = mess;
        path = pat;
        failedValue = val;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getFailedValue() {
        return failedValue;
    }
}

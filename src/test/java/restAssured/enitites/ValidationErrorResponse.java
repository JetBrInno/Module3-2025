package restAssured.enitites;

import java.util.List;

public class ValidationErrorResponse {

    private String message;

    private String error;

    private List<String> wrong_type_fields;

    public ValidationErrorResponse(String message, String error, List<String> wrong_type_fields) {
        this.message = message;
        this.error = error;
        this.wrong_type_fields = wrong_type_fields;
    }

    public ValidationErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getWrong_type_fields() {
        return wrong_type_fields;
    }

    public void setWrong_type_fields(List<String> wrong_type_fields) {
        this.wrong_type_fields = wrong_type_fields;
    }
}

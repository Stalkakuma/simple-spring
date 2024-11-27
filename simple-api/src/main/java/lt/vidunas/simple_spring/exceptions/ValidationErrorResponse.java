package lt.vidunas.simple_spring.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse {
    private int status;
    private String error;
    private List<FieldError> errors;

    public ValidationErrorResponse(int status, String error, List<FieldError> errors) {
        this.status = status;
        this.error = error;
        this.errors = errors;
    }
    @Getter
    @Setter
    public static class FieldError {
        private String field;
        private String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

    }
}

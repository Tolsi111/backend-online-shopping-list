package graduate.work.onlineshoppinglist.exceptions.error_handling.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorCodes;
import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorMapping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({ "cause", "stack_trace", "suppressed", "localized_message", "stackTrace", "localizedMessage" })
public class CustomErrorResponse extends RuntimeException {
    private String code;

    private String message;

    private String description;

    public CustomErrorResponse(ErrorCodes code) {
        super();
        this.code = code.getValue();
        this.message = "";
        this.description = ErrorMapping.getDescriptionFromCode(code);
    }

    public CustomErrorResponse(ErrorCodes code, String message) {
        super();
        this.code = code.getValue();
        this.message = message;
        this.description = ErrorMapping.getDescriptionFromCode(code);
    }

    public CustomErrorResponse(CustomErrorResponse customErrorResponse) {
        super();
        this.code = customErrorResponse.getCode();
        this.message = customErrorResponse.getMessage();
        this.description = customErrorResponse.getDescription();
    }
}

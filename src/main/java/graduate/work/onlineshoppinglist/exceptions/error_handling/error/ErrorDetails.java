package graduate.work.onlineshoppinglist.exceptions.error_handling.error;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private ErrorCodes errorName;

    private String numericalCode;

    private String description;

//    private LocalDate creationDate;

    // shows a description of the cases that the error is thrown
    private String errorContext;

    public ErrorDetails(ErrorCodes errorCode, String description) {
        this.errorName = errorCode;
        this.numericalCode = errorCode.getValue();
        this.description = description;
    }

//    public ErrorDetails(ErrorCodes errorCode, String description, LocalDate creationDate) {
//        this.errorName = errorCode;
//        this.description = description;
//        this.creationDate = creationDate;
//        this.numericalCode = errorCode.getValue();
//    }
//
//    public ErrorDetails(ErrorCodes errorCode, String description, LocalDate creationDate, String errorContext) {
//        this.errorName = errorCode;
//        this.description = description;
//        this.creationDate = creationDate;
//        this.errorContext = errorContext;
//        this.numericalCode = errorCode.getValue();
//    }
}

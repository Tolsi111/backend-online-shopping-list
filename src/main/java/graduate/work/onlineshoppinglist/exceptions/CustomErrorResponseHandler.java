package graduate.work.onlineshoppinglist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import graduate.work.onlineshoppinglist.exceptions.error_handling.error.ErrorCodes;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.CustomErrorResponse;
import graduate.work.onlineshoppinglist.exceptions.error_handling.response.Response;
import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class CustomErrorResponseHandler {

    @ExceptionHandler
    public ResponseEntity<Response<String>> handleException(CustomErrorResponse exc) {
        return new ResponseEntity<>(new Response<>(exc), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Response<String>> handleException(MissingServletRequestParameterException exc) {
        return new ResponseEntity<>(new Response<>(
                new CustomErrorResponse(ErrorCodes.BAD_REQUEST_MISSING_REQUEST_PARAMETER, exc.getParameterName())),
                                    HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Response<String>> handleException(HttpMessageNotReadableException exc) {
        return new ResponseEntity<>(
                new Response<>(new CustomErrorResponse(ErrorCodes.BAD_REQUEST_INVALID_PARAMETER_FORMAT,
                                                       exc.getCause().getMessage())),
                HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Response<String>> handleException(RuntimeException exc) {
        exc.printStackTrace();
        return new ResponseEntity<>(new Response<>(new CustomErrorResponse(ErrorCodes.INTERNAL_SERVER_GENERIC_ERROR)),
                                    HttpStatus.OK);
    }
}

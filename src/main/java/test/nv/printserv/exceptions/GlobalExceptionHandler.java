package test.nv.printserv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private String generateJSONError(String error)
    {
        return "{\"Error\" : \"" + error + "\"}";
    }

    @ExceptionHandler(JobValidationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String jobValidationExceptionHandler(JobValidationException exception) {
        return generateJSONError(exception.getMessage());
    }

    @ExceptionHandler(JobNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public String jobNotFoundExceptionHandler(JobNotFoundException exception) {
        return generateJSONError(exception.getMessage());
    }

    @ExceptionHandler(JobAlreadyExistException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String jobAlreadyExistExceptionHandler(JobAlreadyExistException exception) {
        return generateJSONError(exception.getMessage());
    }

}

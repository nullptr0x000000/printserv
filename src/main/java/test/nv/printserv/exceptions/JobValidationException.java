package test.nv.printserv.exceptions;

public class JobValidationException extends RuntimeException{
    public JobValidationException(String validationError)
    {
        super(validationError + "; Jobs are not added;");
    }
}

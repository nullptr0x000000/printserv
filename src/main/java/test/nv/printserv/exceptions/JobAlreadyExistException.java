package test.nv.printserv.exceptions;

public class JobAlreadyExistException extends RuntimeException {
    public JobAlreadyExistException(String existingJob)
    {
        super("Job already exists: " + existingJob + "; Jobs are not added;");
    }
}

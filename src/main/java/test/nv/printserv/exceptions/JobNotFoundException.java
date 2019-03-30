package test.nv.printserv.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException()
    {
        super("Job is not found;");
    }
}

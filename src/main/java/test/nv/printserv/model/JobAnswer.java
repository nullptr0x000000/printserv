package test.nv.printserv.model;

import test.nv.printserv.hibernate.JobEntity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JobAnswer {

    private int jobId;
    private String device;
    private String user;
    private String type;
    private int amount;
    private String time;

    public JobAnswer(JobEntity jobEntity)
    {
        jobId = jobEntity.getPrimaryKey().getId();
        type = jobEntity.getType();
        user = jobEntity.getUser();
        device = jobEntity.getPrimaryKey().getDevice();
        amount = jobEntity.getAmount();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        time = dateFormat.format(jobEntity.getTime());
    }

    public int getJobId() {
        return jobId;
    }

    public String getDevice() {
        return device;
    }

    public String getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

}

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

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public JobAnswer(JobEntity jobEntity)
    {
        setJobId(jobEntity.getPrimaryKey().getId());
        setType(jobEntity.getType());
        setUser(jobEntity.getUser());
        setDevice(jobEntity.getPrimaryKey().getDevice());
        setAmount(jobEntity.getAmount());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        setTime(dateFormat.format(jobEntity.getTime()));
    }

}

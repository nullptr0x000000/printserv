package test.nv.printserv.hibernate;

import test.nv.printserv.model.JobRequest;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "JobsTable")
public class JobEntity implements Serializable {

    @EmbeddedId
    private JobPrimaryKey primaryKey;
    @Column(name = "type")
    private String type;
    @Column(name = "user")
    private String user;
    @Column(name = "amount")
    private int amount;
    @Column(name = "time")
    private Date time;

    @Override
    public String toString()
    {
        return primaryKey.toString() + "; TYPE: " + type + "; USER: " + user + "; AMOUNT: " + amount + "; TIME: " + time;
    }

    public JobEntity(){}

    public JobEntity(JobRequest jobRequest)
    {
        primaryKey = new JobPrimaryKey(jobRequest.getId(), jobRequest.getDevice());
        type = jobRequest.getType();
        user = jobRequest.getUser();
        amount = jobRequest.getAmount();
        time = jobRequest.getTime();
    }

    public Date getTime() {
        return time;
    }

    public JobPrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public int getAmount() {
        return amount;
    }
}

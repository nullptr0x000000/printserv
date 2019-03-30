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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public JobPrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    private void setPrimaryKey(JobPrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    private void setUser(String user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return primaryKey.toString() + "; TYPE: " + getType() + "; USER: " + getUser() + "; AMOUNT: " + getAmount() + "; TIME: " + getTime();
    }

    public JobEntity(){}

    public JobEntity(JobRequest jobRequest)
    {
        setPrimaryKey(new JobPrimaryKey(jobRequest.getId(), jobRequest.getDevice()));
        setType(jobRequest.getType());
        setUser(jobRequest.getUser());
        setAmount(jobRequest.getAmount());
        setTime(jobRequest.getTime());
    }
}

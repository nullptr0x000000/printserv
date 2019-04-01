package test.nv.printserv.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Date;

@XmlRootElement(name = "job")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class JobRequest {

    private static final String[] types = {"print", "copy", "scan", "fax"};

    private int id;
    private String type;
    private String user;
    private String device;
    private int amount;
    private Date time;
    private String validationError;

    public String getValidationError() {
        return validationError;
    }


    public Date getTime() {
        return time;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public String getDevice() {
        return device;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString()
    {
        return "id:" + getId() + ";type:" + getType() + ";user:" + getUser() + ";device:" + getDevice() + ";amount:" + getAmount();
    }

    public boolean validate()
    {
        if (!Arrays.asList(types).contains(getType())) {
            validationError =  "Wrong type: " + toString();
            return false;
        }

        if (getType() == null){
            validationError =  "Type is not specified: " + toString();
            return false;
        }

        if (getUser() == null){
            validationError =  "User is not specified: " + toString();
            return false;
        }

        if (getDevice() == null){
            validationError =  "Device is not specified: " + toString();
            return false;
        }

        if (getTime() == null){
            validationError =  "Time setting error: " + toString();
            return false;
        }

        if(getAmount() < 1){
            validationError =  "Amount must be bigger than 0: " + toString();
            return false;
        }

        return true;
    }

    public JobRequest(int id, String type, String user, String device, int amount)
    {
        this.id = id;
        this.type = type;
        this.user = user;
        this.device = device;
        this.amount = amount;
        time = new Date();
    }

    public JobRequest(){time = new Date();}

}

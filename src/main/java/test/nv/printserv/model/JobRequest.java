package test.nv.printserv.model;

import test.nv.printserv.exceptions.JobValidationException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    public void setValidationError(String validationError) {
        this.validationError = validationError;
    }


    public Date getTime() {
        return time;
    }

    public void setTime() {
        time = new Date();
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    private void setType(String type){
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    private void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    private void setDevice(String device) {
        this.device = device;
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
        return "id:" + getId() + ";type:" + getType() + ";user:" + getUser() + ";device:" + getDevice() + ";amount:" + getAmount();
    }

    public boolean Validate()
    {
        validationError = "";

        if (!Arrays.asList(types).contains(getType()))
            setValidationError("Wrong type");

        if (getType() == null)
            setValidationError("Type is not specified");

        if (getUser() == null)
            setValidationError("User is not specified");

        if (getDevice() == null)
            setValidationError("Device is not specified");

        if (getTime() == null)
            setValidationError("Time setting error");

        if(getAmount() < 1)
            setValidationError("Amount must be bigger than 0");

        if(!getValidationError().equals(""))
            setValidationError(getValidationError() + ": " + toString());

        return getValidationError().equals("");
    }

    public JobRequest(int id, String type, String user, String device, int amount) throws JobValidationException
    {
        setId(id);
        setType(type);
        setUser(user);
        setDevice(device);
        setAmount(amount);
        setTime();
    }

    public JobRequest(){setTime();}

}

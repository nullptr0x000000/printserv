package test.nv.printserv.hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JobPrimaryKey implements Serializable {
    @Column(name = "id")
    private int id;
    @Column(name = "device")
    private String device;

    @Override
    public String toString()
    {
        return "ID: " + id + "; DEVICE: " + device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobPrimaryKey)) return false;
        JobPrimaryKey that = (JobPrimaryKey) o;
        return Objects.equals(id, that.getId()) &&
                Objects.equals(device, that.getDevice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, device);
    }

    JobPrimaryKey(){}

    JobPrimaryKey(int id, String device){
        this.id = id;
        this.device = device;
    }

    public int getId(){return id;}

    public String getDevice(){return device;}


}

package test.nv.printserv.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.NONE)
public class JobRequestList {

    @XmlElement(name = "job")
    public ArrayList<JobRequest> jobRequestList = new ArrayList<>();

    public ArrayList<JobRequest> getJobRequestList() {
        return jobRequestList;
    }

    public void setJobRequestList(ArrayList<JobRequest> jobRequestList) {
        this.jobRequestList = jobRequestList;
    }

    public List<Map<String,Integer>> countAmountForEachUser()
    {
        List<Map<String,Integer>> answerMapList = new ArrayList<>();

        for(JobRequest jobRequest : getJobRequestList())
        {
            boolean userExists = false;
            int count = 0;

            for (Map answerMap : answerMapList)
                if (answerMap.containsKey(jobRequest.getUser()))
                {
                    userExists = true;
                    break;
                }

            if (userExists)
                continue;

            for(JobRequest job : getJobRequestList())
                if (jobRequest.getUser().equals(job.getUser()))
                    count += job.getAmount();

            final int amount = count;
            answerMapList.add(new HashMap<String, Integer>(){{put(jobRequest.getUser(), amount);}});
        }

        return answerMapList;
    }
}

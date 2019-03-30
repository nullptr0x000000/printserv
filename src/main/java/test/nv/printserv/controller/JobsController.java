package test.nv.printserv.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.nv.printserv.exceptions.JobAlreadyExistException;
import test.nv.printserv.exceptions.JobValidationException;
import test.nv.printserv.hibernate.JobDao;
import test.nv.printserv.hibernate.JobEntity;
import test.nv.printserv.model.JobRequest;
import test.nv.printserv.model.JobRequestList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("jobs")
public class JobsController {

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE})
    public List<Map<String,Integer>> handleXMLPostRequest (@RequestBody JobRequestList jobsXml)
    {
        JobDao jobDao = new JobDao();
        List<JobEntity> jobsToDBList = new ArrayList<>();

        for(JobRequest jobRequest : jobsXml.getJobRequestList())
            if (jobRequest.Validate())
            {
                jobsToDBList.add(new JobEntity(jobRequest));
            }
            else throw new JobValidationException(jobRequest.getValidationError());

        if (!jobDao.persistList(jobsToDBList))
            throw new JobAlreadyExistException(jobDao.getExistingJobError());

        return jobsXml.countAmountForEachUser();
    }

}

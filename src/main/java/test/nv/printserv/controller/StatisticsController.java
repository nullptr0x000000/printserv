package test.nv.printserv.controller;

import org.springframework.web.bind.annotation.*;
import test.nv.printserv.exceptions.JobNotFoundException;
import test.nv.printserv.hibernate.JobDao;
import test.nv.printserv.hibernate.JobEntity;
import test.nv.printserv.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @GetMapping
    public List<JobAnswer> handleGetRequest(@RequestParam Map<String, String> queryParameters)
    {
        String device = queryParameters.get("device");
        String type = queryParameters.get("type");
        String user = queryParameters.get("user");
        String timeFrom = queryParameters.get("timeFrom");
        String timeTo = queryParameters.get("timeTo");

        JobDao jobDao = new JobDao();

        List<JobAnswer> jobAnswerList = new ArrayList<>();
        List<JobEntity> query = jobDao.findByParameters(device,type,user,timeFrom,timeTo);

        if(!query.isEmpty())
            for (JobEntity jobEntity : query)
                jobAnswerList.add(new JobAnswer(jobEntity));
        else throw new JobNotFoundException();

        return jobAnswerList;
    }

}

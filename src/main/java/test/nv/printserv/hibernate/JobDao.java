package test.nv.printserv.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class JobDao {

    private Session currentSession;
    private Transaction currentTransaction;
    private String existingJobError;

    public JobDao() {}

    public String getExistingJobError() {
        return existingJobError;
    }

    private void setExistingJobError(String existingJobError) {
        this.existingJobError = existingJobError;
    }

    private Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    private Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    private void closeCurrentSession() {
        currentSession.close();
    }

    private void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.addAnnotatedClass(JobEntity.class).buildSessionFactory(builder.build());
    }

    private Session getCurrentSession() {
        return currentSession;
    }

    private void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    private Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    private void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    private void persist(JobEntity entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().save(entity);
        closeCurrentSessionwithTransaction();
    }

    public boolean persistList(List<JobEntity> jobEntityList)
    {
        if (!isJobsUnique(jobEntityList))
            return false;
        else
            for (JobEntity jobEntity : jobEntityList)
                persist(jobEntity);

        return true;
    }

    private boolean isJobsUnique(List<JobEntity> jobEntityList)
    {
        JobEntity findedJob;
        for(JobEntity jobEntity : jobEntityList)
        {
            findedJob = findByPrimaryKey(jobEntity.getPrimaryKey());
            if(findedJob != null)
            {
                setExistingJobError(findedJob.toString());
                return false;
            }
        }
        return true;
    }

    /*public void update(JobEntity entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().update(entity);
        closeCurrentSessionwithTransaction();
    }*/

    public JobEntity findByPrimaryKey(JobPrimaryKey id) {
        openCurrentSession();
        JobEntity jobEntity = (JobEntity) getCurrentSession().get(JobEntity.class, id);
        closeCurrentSession();
        return jobEntity;
    }

    @SuppressWarnings("unchecked")
    public List<JobEntity> findByParameters(String device, String type, String user, String timeFrom, String timeTo)
    {
        openCurrentSession();
        String query = makeQuery(device, type, user, timeFrom, timeTo);
        List<JobEntity> jobEntities = (List<JobEntity>) getCurrentSession().createQuery(query).list();
        closeCurrentSession();
        return jobEntities;
    }

    private String makeQuery(String device, String type, String user, String timeFrom, String timeTo)
    {
        boolean isFirstCond = true;

        String query = "FROM JobEntity WHERE ";

        if(parameterNotNullOrEmpty(device))
        {
            query += " device = '" + device + "'";
            isFirstCond = false;
        }

        if(parameterNotNullOrEmpty(type))
        {
            query += appendAndStatement(isFirstCond);
            query += " type = '" + type + "'";
            isFirstCond = false;
        }

        if(parameterNotNullOrEmpty(user))
        {
            query += appendAndStatement(isFirstCond);
            query += " user = '" + user + "'";
            isFirstCond = false;
        }

        if(parameterNotNullOrEmpty(timeFrom))
        {
            query += appendAndStatement(isFirstCond);
            query += " time >= '" + timeFrom + "'";
            isFirstCond = false;
        }

        if(parameterNotNullOrEmpty(timeTo))
        {
            query += appendAndStatement(isFirstCond);
            query += " time <= '" + timeTo + "'"; ////////
            isFirstCond = false;
        }
        
        if(isFirstCond)
            return "FROM JobEntity ORDER BY time";
        else
            query+=" ORDER BY time";

        return query;
    }

    private boolean parameterNotNullOrEmpty(String parameter)
    {
        if(parameter == null)
            return false;
        return !parameter.equals("");
    }

    private String appendAndStatement(boolean isFirstCond)
    {
        if(isFirstCond)
            return "";
        else return " AND";
    }


    /*public void delete(JobPrimaryKey id) {
        openCurrentSessionwithTransaction();
        getCurrentSession().delete(findByPrimaryKey(id));
        closeCurrentSessionwithTransaction();
    }*/

    /*@SuppressWarnings("unchecked")
    public List<JobEntity> findAll() {
        openCurrentSession();
        List<JobEntity> jobEntities = (List<JobEntity>) getCurrentSession().createQuery("from JobEntity").list();
        closeCurrentSession();
        return jobEntities;
    }

    public void deleteAll() {
        openCurrentSessionwithTransaction();
        List<JobEntity> entityList = findAll();
        for (JobEntity entity : entityList) {
            delete(entity.getPrimaryKey());
        }
        closeCurrentSessionwithTransaction();
    }*/
}

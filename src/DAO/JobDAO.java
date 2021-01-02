package DAO;

import Utils.DBUtils;
import bean.Job;
import bean.JobBasicInfo;

import java.util.List;

public class JobDAO {
    public List<Job> getJobList(String sqlRow) {
        List<Job> list = DBUtils.getList(Job.class, sqlRow);
        return list;
    }

    public Integer getJobCount(String sql, Object... parm) {
        Integer count = DBUtils.getCount(sql, parm);
        return count;
    }

    public Integer addJobBasicInfo(JobBasicInfo job) {
        String sql = "insert into job_info(company_id, job_name, job_salary, job_info, job_address, state) values(?,?,?,?,?,?) ;";
        Integer key = DBUtils.updataForPrimary(sql, job.getCompany_id(), job.getJob_name(), job.getJob_salary(), job.getJob_info(), job.getJob_address(), job.getState());
        return key;
    }

    public void addJob_interview(Integer id, Integer job_id) {
        String sql = "insert into job_interview(id, job_id) values(?,?);";
        DBUtils.updata(sql, id, job_id);
    }

    public void deleteJob_interview(Integer id, Integer job_id) {
        String sql = "delete from job_interview where id =? and job_id=?;";
        DBUtils.updata(sql, id, job_id);
    }

    public Integer getJob_interviewCount(Integer user_id) {
        String sql = "select count(*) from job_interview where id = ? ;";
        Integer count = DBUtils.getCount(sql, user_id);
        return count;
    }

    public List<Job> getJob_interviewByUser_id(Integer user_id) {
        String sql = "select j.*, c.name, c.benefit_info, c.ico from job_info j inner join company c on job_id in (SELECT distinct job_id FROM job_interview where id = ?) where j.company_id = c.company_id;";
        List<Job> list = DBUtils.getList(Job.class, sql, user_id);
        return list;
    }

    public boolean updateJobBasicInfo(JobBasicInfo job) {
        String sql = "update job_info set job_name=?, job_salary=?, job_info=?, job_address=?, state=? where job_id=?";
        boolean updata = DBUtils.updata(sql, job.getJob_name(), job.getJob_salary(), job.getJob_info(), job.getJob_address(), job.getState(), job.getJob_id());
        return updata;
    }

    public boolean deleteJob_info(Integer id) {
        String sql = "delete from job_info where job_id = ?";
        boolean updata = DBUtils.updata(sql, id);
        return updata;
    }
}

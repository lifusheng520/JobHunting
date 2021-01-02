package DAO;

import Utils.DBUtils;
import bean.Job;

import java.util.List;

public class SearchDAO {

    public List<Job> getSearchJobsByInput(String content) {
        String sql = "select a.job_id, a.job_name, a.job_salary, a.job_info, a.job_address, a.state, b.company_id, b.name, b.benefit_info, b.ico from job_info a inner join company b on a.company_id=b.company_id where a.job_name like ? ;";
        List<Job> list = DBUtils.getList(Job.class, sql, content);
        return list;
    }

    public List<Job> getSearchJobsById(String id) {
        String sql = "select a.job_id, a.job_name, a.job_salary, a.job_info, a.job_address, a.state, b.company_id, b.name, b.benefit_info, b.ico from job_info a inner join company b on a.company_id=b.company_id where a.company_id = ? ;";
        List<Job> list = DBUtils.getList(Job.class, sql, id);
        return list;
    }

    public List<Job> getSearchJobsByIdAndInput(String id, String content) {
        String sql = "select a.job_id, a.job_name, a.job_salary, a.job_info, a.job_address, a.state, b.company_id, b.name, b.benefit_info, b.ico from job_info a inner join company b on a.company_id=b.company_id where a.company_id = ? and a.job_name like ? ;";
        List<Job> list = DBUtils.getList(Job.class, sql, id, content);
        return list;
    }

}

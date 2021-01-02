package DAO;

import Utils.DBUtils;
import bean.ApplyInfo;
import bean.Job;

import java.util.List;

public class ApplyDAO {

    public void addApplyInfo(String id, String job_id) {
        String sql = "insert into apply(id, job_id) values(?, ?)";
        DBUtils.updata(sql, id, job_id);
    }

    public List<Job> getJobInfo(Integer id) {
//        String sql="select c.job_id, c.company_id, c.job_name, c.job_salary, c.job_info, c.job_address, c.state, d.name, d.benefit_info, d.ico from (select b.* from apply a inner join job_info b on a.job_id = b.job_id where a.id=?)as c,company as d where d.company_id = c.company_id";
        String sql = "select c.job_id, c.company_id, c.job_name, c.job_salary, c.job_info, c.job_address, c.state, d.name, d.benefit_info, d.ico, c.reply from (select b.*,a.reply from apply a inner join job_info b on a.job_id = b.job_id where a.id=?)as c,   company as d where d.company_id = c.company_id";
        List<Job> list = DBUtils.getList(Job.class, sql, id);
        return list;
    }

    public boolean deleteJobInfo(String id, String job_id) {
        String sql = "delete from apply where id = ? and job_id = ? ;";
        boolean b = DBUtils.updata(sql, id, job_id);
        return b;
    }

    public List<ApplyInfo> getApplyOfAppliants(Integer userId) {
        String sql = "select r.resume_id, r.realname, r.gender, r.birthday, r.location, r.telephone, r.email, r.job_experience, r.head_shot, a.job_id, a.reply from apply a inner join resume r on a.id = r.resume_id where job_id in( select job_id from job_interview where id = ? );";
        List<ApplyInfo> list = DBUtils.getList(ApplyInfo.class, sql, userId);
        return list;
    }

    public boolean updateReply(Integer id, Integer job_id, String reply) {
        String sql="update apply set reply = ? where id=? and job_id=? ;";
        boolean updata = DBUtils.updata(sql, reply, id, job_id);
        return updata;
    }

}

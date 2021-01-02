package DAO;

import Utils.DBUtils;
import bean.User;

public class ApplicantDAO {
    //保存注册对象
    public boolean saveApplicant(User user) {
        String sql = "insert into applicant(account, password, register_date, user_type) values(?,?,?,?)";
        long time = user.getDate().getTime();
        boolean flag = DBUtils.save(sql, user.getAccount(), user.getPassword(), new java.sql.Date(time), user.getUser_type());
        return flag;
    }

    // 根据账号密码查询用户
    public User getApplicantByAccount(String account) {
        String sql = "select id, account, password, user_type from applicant where account=? ";
        return DBUtils.getSingleObj(User.class, sql, account);
    }

    public Integer selectUserAccountCount(String account) {
        String sql = "select count(*) from applicant a where a.account = ?";
        Integer count = DBUtils.getCount(sql, account);
        return count;
    }

    public Integer isExistResume(Integer applicantId) {
        String sql = "select resume_id from resume where resume_id=?";
        Integer resumeID = DBUtils.getCount(sql, applicantId);
        return resumeID;
    }

    public Integer isExistCompany(Integer id){
        String sql="select count(*) from company where company_id = ?";
        Integer count = DBUtils.getCount(sql, id);
        return count;
    }
}

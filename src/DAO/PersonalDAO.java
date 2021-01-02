package DAO;

import Utils.DBUtils;
import bean.Resume;
import bean.User;

public class PersonalDAO {

    public User getUserInfo(Integer id){
        String sql=" select * from applicant where id=? ";
        User user = DBUtils.getSingleObj(User.class, sql, id);
        return user;
    }

    public Resume getHeadShot(Integer id){
        String sql="select * from resume where resume_id=? ";
        Resume resume = DBUtils.getSingleObj(Resume.class, sql, id);
        return resume;
    }

    public boolean updatePassword(String account, String newPassowrd){
        String sql="update applicant set password=? where account=? ";
        boolean flag = DBUtils.updata(sql, newPassowrd, account);
        return flag;
    }
}

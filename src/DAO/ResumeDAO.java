package DAO;

import Utils.DBUtils;
import bean.Resume;

public class ResumeDAO {
    public static void updateHeadShot(int resumeId, String headShot) {
        String sql="update resume set head_shot = ? where resume_id=?";
        DBUtils.updata(sql,headShot,resumeId);
    }

    public Integer saveResumeBasicInfo(Resume resume) {
        String sql = "insert into resume(resume_id,realname,gender,birthday,location,telephone,email,job_experience,head_shot) " +
                "values(?,?,?,?,?,?,?,?,?)";
        return DBUtils.updataForPrimary(sql, resume.getResume_id(),
                resume.getRealname(),
                resume.getGender(),
                resume.getBirthday(),
                resume.getLocation(),
                resume.getTelephone(),
                resume.getEmail(),
                resume.getJob_experience(),
                resume.getHead_shot());
    }

    public Resume getResumeBasicInfoById(Integer resumeID) {
        String sql=" SELECT * FROM `resume` where resume_id=? ";
        Resume resumeBasicInfo = DBUtils.getSingleObj(Resume.class,sql,resumeID);
        return resumeBasicInfo;
    }

    public boolean updateResumeBasicInfo(Resume resume) {
        String sql = "update resume set realname=?, gender=?, birthday=?, location=?, telephone=?, email=?, job_experience=? where resume_id=?";
        boolean b = DBUtils.updata(sql, resume.getRealname(), resume.getGender(), resume.getBirthday(), resume.getLocation(), resume.getTelephone(), resume.getEmail(), resume.getJob_experience(), resume.getResume_id());
        return b;
    }
}

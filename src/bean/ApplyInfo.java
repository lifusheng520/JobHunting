package bean;

public class ApplyInfo {
    private int resume_id;
    private String realname;
    private String gender;
    private String birthday;
    private String location;
    private String telephone;
    private String email;
    private String job_experience;
    private String head_shot;
    private Integer job_id;
    private String reply;

    public ApplyInfo() {
    }

    public ApplyInfo(int resume_id, String realname, String gender, String birthday, String location, String telephone, String email, String job_experience, String head_shot, Integer job_id) {
        this.resume_id = resume_id;
        this.realname = realname;
        this.gender = gender;
        this.birthday = birthday;
        this.location = location;
        this.telephone = telephone;
        this.email = email;
        this.job_experience = job_experience;
        this.head_shot = head_shot;
        this.job_id = job_id;
    }

    public ApplyInfo(int resume_id, String realname, String gender, String birthday, String location, String telephone, String email, String job_experience, String head_shot, Integer job_id, String reply) {
        this.resume_id = resume_id;
        this.realname = realname;
        this.gender = gender;
        this.birthday = birthday;
        this.location = location;
        this.telephone = telephone;
        this.email = email;
        this.job_experience = job_experience;
        this.head_shot = head_shot;
        this.job_id = job_id;
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getResume_id() {
        return resume_id;
    }

    public void setResume_id(int resume_id) {
        this.resume_id = resume_id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob_experience() {
        return job_experience;
    }

    public void setJob_experience(String job_experience) {
        this.job_experience = job_experience;
    }

    public String getHead_shot() {
        return head_shot;
    }

    public void setHead_shot(String head_shot) {
        this.head_shot = head_shot;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }
}

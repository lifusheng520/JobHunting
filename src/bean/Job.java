package bean;

public class Job {
    private Integer job_id;
    private String job_name;
    private String job_salary;
    private String job_info;
    private String job_address;
    private Integer state;

    private Integer company_id;
    private String name;
    private String benefit_info;
    private String ico;

    private String reply;

    public Job() {

    }

    public Job(Integer job_id, Integer company_id, String job_name, String job_salary, String job_info, String job_address, Integer state) {
        this.job_id = job_id;
        this.company_id = company_id;
        this.job_name = job_name;
        this.job_salary = job_salary;
        this.job_info = job_info;
        this.job_address = job_address;
        this.state = state;
    }

    public Job(Integer job_id, Integer company_id, String job_name, String job_salary, String job_info, String job_address, Integer state, String name, String benefit_info, String ico) {
        this.job_id = job_id;
        this.company_id = company_id;
        this.job_name = job_name;
        this.job_salary = job_salary;
        this.job_info = job_info;
        this.job_address = job_address;
        this.state = state;
        this.name = name;
        this.benefit_info = benefit_info;
        this.ico = ico;
    }

    public Job(Integer job_id, String job_name, String job_salary, String job_info, String job_address, Integer state, Integer company_id, String name, String benefit_info, String ico, String reply) {
        this.job_id = job_id;
        this.job_name = job_name;
        this.job_salary = job_salary;
        this.job_info = job_info;
        this.job_address = job_address;
        this.state = state;
        this.company_id = company_id;
        this.name = name;
        this.benefit_info = benefit_info;
        this.ico = ico;
        this.reply = reply;
    }


    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_salary() {
        return job_salary;
    }

    public void setJob_salary(String job_salary) {
        this.job_salary = job_salary;
    }

    public String getJob_info() {
        return job_info;
    }

    public void setJob_info(String job_info) {
        this.job_info = job_info;
    }

    public String getJob_address() {
        return job_address;
    }

    public void setJob_address(String job_address) {
        this.job_address = job_address;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBenefit_info() {
        return benefit_info;
    }

    public void setBenefit_info(String benefit_info) {
        this.benefit_info = benefit_info;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }
}

package bean;

public class JobBasicInfo {

    private Integer job_id;
    private Integer company_id;
    private String job_name;
    private String job_salary;
    private String job_info;
    private String job_address;
    private Integer state;

    public JobBasicInfo() {
    }

    public JobBasicInfo(Integer company_id, String job_name, String job_salary, String job_info, String job_address, Integer state) {
        this.company_id = company_id;
        this.job_name = job_name;
        this.job_salary = job_salary;
        this.job_info = job_info;
        this.job_address = job_address;
        this.state = state;
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
}

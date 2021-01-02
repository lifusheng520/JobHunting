package bean;

public class Company {
    private Integer company_id;
    private String name;
    private String benefit_info;
    private String ico;

    public Company() {
    }

    public Company(String name, String benefit_info, String ico) {
        this.name = name;
        this.benefit_info = benefit_info;
        this.ico = ico;
    }

    public Company(Integer company_id, String name, String benefit_info, String ico) {
        this.company_id = company_id;
        this.name = name;
        this.benefit_info = benefit_info;
        this.ico = ico;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
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

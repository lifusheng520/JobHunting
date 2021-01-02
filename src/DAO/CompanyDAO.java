package DAO;

import Utils.DBUtils;
import bean.Company;

import java.util.List;

public class CompanyDAO {

    public List<Company> getCompanys() {
        String sql = "select * from company";
        List<Company> list = DBUtils.getList(Company.class, sql);
        return list;
    }

    public Integer addCompany(Company company) {
        String sql = "insert into company(name, benefit_info, ico) values(?,?,?) ;";
        Integer key = DBUtils.updataForPrimary(sql, company.getName(), company.getBenefit_info(), company.getIco());
        return key;
    }

    public boolean updateCompany(Company company) {
        String sql = "update company set name=?, benefit_info=?, ico=? where company_id=?";
        boolean updata = DBUtils.updata(sql, company.getName(), company.getBenefit_info(), company.getIco(), company.getCompany_id());
        return updata;
    }

    public boolean deleteCompany(Integer id){
        String sql="delete from company where company_id = ? ;";
        boolean updata = DBUtils.updata(sql, id);
        return updata;
    }

}

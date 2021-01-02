package DAO;

public class test {
    public static void main(String[] args) {
        String sql="select a.job_id, a.job_name, a.job_salary, a.job_info, a.job_address, a.state, b.company_id, b.name, b.benefit_info, b.ico from job_info a inner join company b on a.company_id=b.company_id where a.job_name like \"%?%\" ;";
        System.out.println(sql);


    }
}

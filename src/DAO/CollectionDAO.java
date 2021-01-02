package DAO;

import Utils.DBUtils;
import bean.Job;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionDAO {

    public void addCollection(Integer account, String id) {
        String sql = "insert into collection(account, job_id) values(?,?) ";
        DBUtils.updata(sql, account, id);
    }

    public List<Job> getCollectionJobIdSet(Integer id) {
        String sql = "select a.job_id, a.company_id, a.job_name, a.job_salary, a.job_info, a.job_address, a.state, b.name, b.benefit_info, b.ico from job_info a inner join company b on a.company_id = b.company_id where a.job_id in (SELECT distinct job_id FROM `collection` where account=?) ";

        List<Job> list = DBUtils.getList(Job.class, sql, id);

        return list;
    }

    public void deleteCollections(String [] ids){
        String sql = "delete from collection where job_id = ?;";

        for(String s : ids){
            DBUtils.updata(sql, s);
        }
    }

}

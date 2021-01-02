package bean;

import java.util.Date;

public class User {
    private int id;
    private String account;
    private String password;
    private Date date;
    private Integer user_type;

    public User() {
    }

    public User(String account, String password, Date date) {
        this.account = account;
        this.password = password;
        this.date = date;
    }

    public User(int id, String account, String password, Date date) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.date = date;
    }

    public User(String account, String password, Date date, Integer user_type) {
        this.account = account;
        this.password = password;
        this.date = date;
        this.user_type = user_type;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

package me.chenfuduo.myjobforcontest.bean;

/**
 * Created by chenfuduo on 2015/9/17.
 */
public class JobInfo {
    private String address;
    private String company;
    private String holdtime;
    private int id;
    private String name;

    public JobInfo(String address, String company, String holdtime, int id,String name) {
        this.address = address;
        this.company = company;
        this.holdtime = holdtime;
        this.id = id;
        this.name = name;
    }

    public JobInfo() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHoldtime() {
        return holdtime;
    }

    public void setHoldtime(String holdtime) {
        this.holdtime = holdtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", holdtime='" + holdtime + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

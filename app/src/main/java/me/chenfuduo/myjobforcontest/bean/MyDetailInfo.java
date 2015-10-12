package me.chenfuduo.myjobforcontest.bean;

/**
 * Created by chenfuduo on 2015/9/17.
 */
public class MyDetailInfo {
    private String address;
    private String company;
    private String holdtime;
    private String infotext;
    private String web;
    private String name;

    public MyDetailInfo(String address, String company, String holdtime, String infotext, String web, String name) {
        this.address = address;
        this.company = company;
        this.holdtime = holdtime;
        this.infotext = infotext;
        this.web = web;
        this.name = name;
    }

    public MyDetailInfo() {
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

    public String getInfotext() {
        return infotext;
    }

    public void setInfotext(String infotext) {
        this.infotext = infotext;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyDetailInfo{" +
                "address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", holdtime='" + holdtime + '\'' +
                ", infotext='" + infotext + '\'' +
                ", web='" + web + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

package team.A15.easyschool.adapter.enity;

import cn.bmob.v3.BmobObject;

/**
 * @package: team.A15.easyschool.adapter.enity
 * @author: CJH
 * @description:
 * @date: 2019/12/4 16:11
 * @version: 1.0
 */
public class TeamInfo extends BmobObject {

    /**
     * 比赛名称
     */
    private String title;


    /**
     * 详细描述
     */
    private String detail;


    /**
     * 发布时间
     */
    private String date;

    /**
     * 校区
     */
    private String campus;

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    private String contactWay;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    @Override
    public String toString() {
        return "TeamInfo{" +
                "title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", date='" + date + '\'' +
                ", campus='" + campus + '\'' +
                ", contactWay='" + contactWay + '\'' +
                '}';
    }
}

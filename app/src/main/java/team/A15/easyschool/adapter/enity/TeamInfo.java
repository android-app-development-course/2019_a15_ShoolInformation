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
     * 摘要
     */
    private String summary;

    /**
     * 详细描述
     */
    private String detail;


    /**
     * 发布时间
     */
    private String date;

    /**
     * 区域
     */
    private String area;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

package team.A15.easyschool.adapter.enity;


import cn.bmob.v3.BmobObject;

/**
 * @package: team.A15.easyschool.adapter.enity
 * @author: CJH
 * @description:
 * @date: 2019/11/26 21:50
 * @version: 1.0
 */
public class LostInfo extends BmobObject {
    /**
     * 标题
     */
    private String title;

    /**
     * 具体描述
     */
    private String detail;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 图片链接
     */
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

}

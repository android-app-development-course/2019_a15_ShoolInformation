package team.A15.easyschool.adapter.enity;

import android.media.Image;


import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * @package: team.A15.easyschool.adapter.enity
 * @author: CJH
 * @description: 二手物品信息类
 * @date: 2019/11/21 14:29
 * @version: 1.0
 */
public class GoodInfo extends BmobObject {

    /**
     * 物品名称
     */
    private String GoodName;

    /**
     * 物品描述
     */
    private String description;

    /**
     * 物品图片
     * 后期要改为后端云对应类型
     */
    private BmobFile image;

    /**
     * 物品价格
     */
    private String price;

    /**
     * 联系方式
     */
    private String contactInfo;

    /**
     * 类型标签
     */
    private String tag;

    /**
     * 发布时间
     */
    private String time;

    public String getGoodName() {
        return GoodName;
    }

    public void setGoodName(String goodName) {
        GoodName = goodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

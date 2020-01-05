package team.A15.easyschool.widget;

import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @package: team.A15.easyschool.widget
 * @author: CJH
 * @description:
 * @date: 2019/12/16 19:40
 * @version: 1.0
 */
public class MyBannerItemList {
    private List<BannerItem> bannerItemList;

    private List<String> url;

    public List<BannerItem> getBannerItemList() {
        return bannerItemList;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public void setBannerItemList(List<BannerItem> bannerItemList) {
        this.bannerItemList = bannerItemList;
    }

    public MyBannerItemList() {
        this.bannerItemList = new ArrayList<>();
        this.url = new ArrayList<>();
    }
}

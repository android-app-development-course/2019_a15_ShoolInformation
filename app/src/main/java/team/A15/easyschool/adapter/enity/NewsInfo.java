

package team.A15.easyschool.adapter.enity;

/**
 * @package: com.xuexiang.templateproject.adapter
 * @author: CJH
 * @description: 新闻信息实体类
 * @date: 2019/11/14 20:51
 * @version: 1.0
 */
public class NewsInfo {
    /**
     * 发布时间
     */
    private String date;
    /**
     * 标题
     */
    private String title;
    /**
     * 详情链接
     */
    private String url;
    /**
     * 概要
     */
    private String summary;
    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 阅读量
     */
    private String readingNum;

    public String getReadingNum() {
        return readingNum;
    }

    public void setReadingNum(String readingNum) {
        this.readingNum = readingNum;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

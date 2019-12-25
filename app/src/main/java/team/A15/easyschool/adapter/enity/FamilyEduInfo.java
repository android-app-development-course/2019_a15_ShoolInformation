package team.A15.easyschool.adapter.enity;

/**
 * @package: com.xuexiang.templateproject.adapter.enity
 * @author: CJH
 * @description: 家教信息实体类
 * @date: 2019/11/14 20:52
 * @version: 1.0
 */
public class FamilyEduInfo {
    /**
     * 家教编号
     */
    private String num;

    /**
     * 家教城区
     */
    private String area;

    /**
     * 学生年级
     */
    private String stuGrade;
    /**
     * 学生性别
     */
    private String stuSex;
    /**
     * 学生科目
     */
    private String subject;
    /**
     * 教师要求
     */
    private String requirement;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private String detail;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public String toString() {
        return "FamilyEduInfo{" +
                "num='" + num + '\'' +
                ", area='" + area + '\'' +
                ", stuGrade='" + stuGrade + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", subject='" + subject + '\'' +
                ", requirement='" + requirement + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}


package team.A15.easyschool.fragment.FamilyEdu;

import android.widget.TextView;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import team.A15.easyschool.R;
import team.A15.easyschool.core.BaseFragment;

/**
 * @package: team.A15.easyschool.fragment.FamilyEdu
 * @author: CJH
 * @description: 家教详情页
 * @date: 2019/11/19 21:47
 * @version: 1.0
 */
@Page(name = "家教详情", params = {FamilyEduDtiFragment.KEY_AREA, FamilyEduDtiFragment.KEY_DETAIL,
        FamilyEduDtiFragment.KEY_GRADE, FamilyEduDtiFragment.KEY_NUM, FamilyEduDtiFragment.KEY_REQUIRE,
        FamilyEduDtiFragment.KEY_SEX, FamilyEduDtiFragment.KEY_SUBJECT})
public class FamilyEduDtiFragment extends BaseFragment {
    public final static String KEY_NUM = "num";
    public final static String KEY_AREA = "area";
    public final static String KEY_GRADE = "grade";
    public final static String KEY_SEX = "sex";
    public final static String KEY_SUBJECT = "subject";
    public final static String KEY_REQUIRE = "require";
    public final static String KEY_DETAIL = "detail";

//    绑定数据
    @AutoWired(name = KEY_NUM)
    String num;

    @AutoWired(name = KEY_AREA)
    String area;

    @AutoWired(name = KEY_GRADE)
    String grade;

    @AutoWired(name = KEY_SEX)
    String sex;

    @AutoWired(name = KEY_SUBJECT)
    String subject;

    @AutoWired(name = KEY_REQUIRE)
    String require;

    @AutoWired(name = KEY_DETAIL)
    String detail;

    @BindView(R.id.tv_num)
    TextView tv_num;

    @BindView(R.id.tv_area)
    TextView tv_area;

    @BindView(R.id.tv_sex)
    TextView tv_sex;

    @BindView(R.id.tv_detail)
    TextView tv_detail;

    @BindView(R.id.tv_require)
    TextView tv_require;

    @BindView(R.id.tv_subject)
    TextView tv_subject;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_familyedu_detail;
    }

    @Override
    protected void initViews() {
        update();
    }

    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    private void update(){
        tv_num.setText("家教编号：" + num);
        tv_area.setText("区域：" + area);
        tv_detail.setText("详细要求：\n" + detail);
        tv_require.setText("教师要求：" + require);
        tv_subject.setText("辅导科目：" + grade + subject);
        tv_sex.setText("学生性别：" + sex);
    }

}

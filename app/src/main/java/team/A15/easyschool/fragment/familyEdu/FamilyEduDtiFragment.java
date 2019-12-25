package team.A15.easyschool.fragment.familyEdu;

import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.popupwindow.popup.XUIPopup;

import butterknife.BindView;
import team.A15.easyschool.R;
import team.A15.easyschool.core.BaseFragment;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

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

    private XUIPopup normalPopup;

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

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.icon_action_about) {
            @Override
            public void performAction(View view) {
                initNormalPopupIfNeed();
                normalPopup.setAnimStyle(XUIPopup.ANIM_GROW_FROM_CENTER);
                normalPopup.setPreferredDirection(XUIPopup.DIRECTION_TOP);
                normalPopup.show(view);
            }
        });
        return null;
    }


    private void initNormalPopupIfNeed() {
        if (normalPopup == null) {
            normalPopup = new XUIPopup(getContext());
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(normalPopup.generateLayoutParam(
                    DensityUtils.dp2px(getContext(), 250),
                    WRAP_CONTENT
            ));
            textView.setLineSpacing(DensityUtils.dp2px(4), 1.0f);
            int padding = DensityUtils.dp2px(20);
            textView.setPadding(padding, padding, padding, padding);
            textView.setText(getResources().getText(R.string.family_edu_tip));
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.xui_config_color_content_text));
            textView.setTypeface(XUI.getDefaultTypeface());
            normalPopup.setContentView(textView);
            normalPopup.setOnDismissListener(() -> {
            });
        }
    }
}

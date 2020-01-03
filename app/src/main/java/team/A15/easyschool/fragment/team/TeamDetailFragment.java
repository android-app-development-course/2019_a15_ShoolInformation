package team.A15.easyschool.fragment.team;

import android.os.Bundle;
import android.widget.TextView;

import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;

import butterknife.BindView;
import team.A15.easyschool.R;
import team.A15.easyschool.core.BaseFragment;

/**
 * @package: team.A15.easyschool.fragment.team
 * @author: CJH
 * @description:
 * @date: 2019/12/23 23:31
 * @version: 1.0
 */
@Page(name = "组队详情", params = {TeamDetailFragment.KEY_TITLE, TeamDetailFragment.KEY_CAMPUS,
        TeamDetailFragment.KEY_DATE, TeamDetailFragment.KEY_CONTACTWAY,
        TeamDetailFragment.KEY_DETAIL,"params"})
public class TeamDetailFragment extends BaseFragment {
    static final String KEY_TITLE = "title";
    static final String KEY_CAMPUS = "campus";
    static final String KEY_DATE = "date";
    static final String KEY_CONTACTWAY = "contactWay";
    static final String KEY_DETAIL = "detail";

    @AutoWired(name = KEY_TITLE)
    String title;

    @AutoWired(name = KEY_DATE)
    String date;

    @AutoWired(name = KEY_CONTACTWAY)
    String contactWay;

    @AutoWired(name = KEY_DETAIL)
    String detail;

    @AutoWired(name = KEY_CAMPUS)
    String campus;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.tv_campus)
    TextView tvCampus;

    @BindView(R.id.tv_contactWay)
    TextView tvContactway;

    @BindView(R.id.tv_detail)
    TextView tvDetail;


    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_team_detail;
    }

    @Override
    protected void initViews() {
        update();
    }

    private void update(){
        tvTitle.setText("赛事名称：    " + title);
        tvDate.setText("项目时间：    " + date);
        tvContactway.setText("联系方式：    " + contactWay);
        tvDetail.setText("详细说明：\n    " + detail);
        tvCampus.setText("队员要求：     " + campus);
    }
}

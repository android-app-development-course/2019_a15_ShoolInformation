package team.A15.easyschool.fragment.lost;

import android.widget.TextView;

import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import team.A15.easyschool.R;
import team.A15.easyschool.core.BaseFragment;

/**
 * @package: team.A15.easyschool.fragment.competition
 * @author: CJH
 * @description:
 * @date: 2019/11/26 22:50
 * @version: 1.0
 */
@Page(name = "失物详情")
public class lostDtiFragment extends BaseFragment {
    public static final String KEY_TITLE = "title";
//    public static final String KEY_Title

    @BindView(R.id.tv_title)
    TextView tv_name;

    @BindView(R.id.tv_contactWay)
    TextView tv_contactWay;

    @BindView(R.id.tv_detail)
    TextView tv_detail;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lost_detail;
    }

    @Override
    protected void initViews() {

    }
}

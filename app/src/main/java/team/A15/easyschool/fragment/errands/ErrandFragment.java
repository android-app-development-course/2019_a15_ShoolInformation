package team.A15.easyschool.fragment.errands;

import android.os.Handler;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.LostInfo;
import team.A15.easyschool.adapter.lostCardViewAdapter;
import team.A15.easyschool.core.BaseFragment;

/**
 * @package: team.A15.easyschool.fragment.errands
 * @author: CJH
 * @description:
 * @date: 2019/12/22 21:11
 * @version: 1.0
 */
@Page(name = "有偿跑腿")
public class ErrandFragment extends BaseFragment {
    /**
     * 标题栏
     */
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    private lostCardViewAdapter adapter;

    private List<LostInfo> lostInfoList;

    private Handler loadingHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycle_view;
    }

    @Override
    protected void initViews() {
        multipleStatusView.showEmpty();
    }

    @Override
    protected TitleBar initTitle() {
        titleBar.setTitle(getPageTitle());
        titleBar.setLeftClickListener(view -> popToBack());
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_add_white_24dp) {
            @Override
            public void performAction(View view) {

            }
        });
        return null;
    }
}

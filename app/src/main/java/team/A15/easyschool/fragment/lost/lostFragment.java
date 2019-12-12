package team.A15.easyschool.fragment.lost;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;

import butterknife.BindView;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.LostInfo;
import team.A15.easyschool.adapter.lostCardViewAdapter;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.utils.Utils;

/**
 * @package: team.A15.easyschool.fragment.competition
 * @author: CJH
 * @description: 比赛组队
 * @date: 2019/11/26 21:30
 * @version: 1.0
 */
@Page(name = "失物招领")
public class lostFragment extends BaseFragment {

    /**
     * 标题栏
     */
    @BindView(R.id.toolbar)
    TitleBar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.statefulLayout)
    StatefulLayout statefulLayout;

    private lostCardViewAdapter lostCardViewAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycle_view;
    }

    @Override
    protected void initViews() {
        WidgetUtils.initRecyclerView(recyclerView,0);
        Utils.initSmartRefreshLayout(this, smartRefreshLayout,
                getResources().getString(R.string.style_refresh_layout));
        recyclerView.setAdapter(lostCardViewAdapter = new lostCardViewAdapter());
        statefulLayout.showEmpty();
    }

    @Override
    protected void initListeners() {
        //点击项目跳转
        lostCardViewAdapter.setOnItemClickListener(new RecyclerViewHolder.OnItemClickListener<LostInfo>() {
            @Override
            public void onItemClick(View itemView, LostInfo item, int position) {
                Bundle params = new Bundle();
//                params.putString();
            }
        });
        //新建失物消息
        floatingActionButton.setOnClickListener(view -> openPage(lostCreateFragment.class));
    }

    @Override
    protected TitleBar initTitle() {
        toolbar.setTitle(getPageTitle());
        return null;
    }
}

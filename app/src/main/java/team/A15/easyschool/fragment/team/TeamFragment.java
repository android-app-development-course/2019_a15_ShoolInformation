package team.A15.easyschool.fragment.team;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.TeamCardViewListAdapter;
import team.A15.easyschool.adapter.enity.TeamInfo;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.fragment.lost.lostNewFragment;
import team.A15.easyschool.utils.Utils;

/**
 * @package: team.A15.easyschool.fragment.team
 * @author: CJH
 * @description: 赛事组队
 * @date: 2019/12/4 16:08
 * @version: 1.0
 */
@Page(name = "比赛组队")
public class TeamFragment extends BaseFragment {

    /**
     * 标题栏
     */
    @BindView(R.id.toolbar)
    TitleBar toolbar;

    /**
     * 页面切换
     */
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    /**
     * 悬浮按钮
     */
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    /**
     * 循环列表
     */
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    /**
     * 状态页面
     */
    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    private TeamCardViewListAdapter adapter;

    private List<TeamInfo> dataList;

    private Handler loadingHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (multipleStatusView.getViewStatus() == MultipleStatusView.STATUS_LOADING) {
                if (dataList.isEmpty()){
                    multipleStatusView.showEmpty();
                }else{
                    multipleStatusView.showContent();
                }

            }
            return true;
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycle_view;
    }

    @Override
    protected void initViews() {
        //初始化组件
        WidgetUtils.initRecyclerView(recyclerView,0);
        Utils.initSmartRefreshLayout(this,smartRefreshLayout,getResources().getString(R.string.style_refresh_layout));
        recyclerView.setAdapter(adapter = new TeamCardViewListAdapter());
//        adapter.refresh(DemoDataProvider.getGoodsList());
        //是否联网
        if (false){
            multipleStatusView.showNoNetwork();
        }else {
            //加载数据
//            dataList = DemoDataProvider.getGoodsList();
            multipleStatusView.showLoading();
            loadingHandler.sendEmptyMessageDelayed(0, 5000);
            //开一个线程加载数据
        }
        //加载数据
        adapter.refresh(dataList);
    }

    @Override
    protected void initListeners() {
        //下拉刷新动作
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            /**
             * 下拉刷新动作
             */
            refreshLayout.finishRefresh();
        },2000));


        //上拉加载更多动作
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            /**
             * 上拉加载更多动作
             */
            refreshLayout.finishLoadMore();
            refreshLayout.resetNoMoreData();
        },2000));


        //点击项目跳转
        adapter.setOnItemClickListener((itemView, item, position) -> {
            Bundle params = new Bundle();
//                params.putString();
        });
        //新建失物消息
        floatingActionButton.setOnClickListener(view -> openPage(lostNewFragment.class));
    }

    @Override
    protected TitleBar initTitle() {
        toolbar.setTitle(getPageName());
        toolbar.setLeftClickListener(view -> popToBack());
        return null;
    }
}

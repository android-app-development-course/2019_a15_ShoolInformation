package team.A15.easyschool.fragment.team;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.MultipleStatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.TeamCardViewListAdapter;
import team.A15.easyschool.adapter.enity.TeamInfo;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.utils.Utils;

/**
 * @package: team.A15.easyschool.fragment.team
 * @author: CJH
 * @description:
 * @date: 2019/12/23 22:26
 * @version: 1.0
 */
@Page(name = "比赛组队")
public class TeamFragment extends BaseFragment {

    /**
     * 标题栏
     */
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    /**
     * 页面切换
     */
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

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

    private List<TeamInfo> teamInfoList;

    private Handler loadingHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycle_view;
    }

    @Override
    protected void initViews() {
        loadingHandler = new Handler(msg -> {
            if (multipleStatusView != null
                    && multipleStatusView.getViewStatus() == MultipleStatusView.STATUS_LOADING) {
                    multipleStatusView.showContent();
            }
            return true;
        });

        //初始化组件
        WidgetUtils.initRecyclerView(recyclerView,0);
        Utils.initSmartRefreshLayout(this,smartRefreshLayout,getResources().getString(R.string.style_refresh_layout));
        recyclerView.setAdapter(adapter = new TeamCardViewListAdapter());
        teamInfoList = new ArrayList<>();
        loadData();
    }

    @Override
    protected TitleBar initTitle() {
        titleBar.setTitle(getPageName());
        titleBar.setLeftClickListener(view -> popToBack());
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_add_white_24dp) {
            @Override
            public void performAction(View view) {
                openPage(TeamCreateFragment.class);
            }
        });
        return null;
    }


    private void query(){
        BmobQuery<TeamInfo> query = new BmobQuery<>();
        query.order("-createdAt").findObjects(new FindListener<TeamInfo>() {
            @Override
            public void done(List<TeamInfo> list, BmobException e) {
                if (e != null){
                    XLogger.e(e);
                }else{
                    teamInfoList.addAll(list);
                    adapter.refresh(teamInfoList);
                }
            }
        });
    }

    @Override
    protected void initListeners() {
        //下拉刷新动作
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            /**
             * 下拉刷新动作
             */
            teamInfoList.clear();
            query();
            refreshLayout.finishRefresh();
        },2000));


        //上拉加载更多动作
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            /**
             * 上拉加载更多动作
             */
            refreshLayout.finishLoadMore(true);
        },2000));


        //打开详情页
        adapter.setOnItemClickListener((itemView, item, position) -> {
            Bundle params = new Bundle();
            params.putString(TeamDetailFragment.KEY_DETAIL,item.getDetail());
            params.putString(TeamDetailFragment.KEY_DATE,item.getDate());
            params.putString(TeamDetailFragment.KEY_CAMPUS,item.getCampus());
            params.putString(TeamDetailFragment.KEY_TITLE,item.getTitle());
            params.putString(TeamDetailFragment.KEY_CONTACTWAY,item.getContactWay());
            openPage(TeamDetailFragment.class,params);
        });

        multipleStatusView.setOnRetryClickListener(view -> loadData());
    }

    private void loadData(){
        if (Utils.isNetworkConnected(getContext())){
            //加载数据
            query();
            multipleStatusView.showLoading();
            loadingHandler.sendEmptyMessageDelayed(0, 3000);
        }else {
            multipleStatusView.showNoNetwork();
        }
    }
}

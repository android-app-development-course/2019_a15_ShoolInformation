package team.A15.easyschool.fragment.trade;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.statelayout.MultipleStatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.GoodCardViewListAdapter;
import team.A15.easyschool.adapter.enity.GoodInfo;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.utils.Utils;

/**
 * @package: team.A15.easyschool.fragment.SecHandGood
 * @author: CJH
 * @description: 二手物品交易
 * @date: 2019/11/21 14:49
 * @version: 1.0
 */
@Page(name = "闲置交易")
public class TradeFragment extends BaseFragment {

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

    private GoodCardViewListAdapter adapter;

    private List<GoodInfo> goodInfoList;

    private Handler loadingHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycle_view;
    }

    @Override
    protected void initViews() {

        loadingHandler = new Handler(msg -> {
            if (multipleStatusView != null && multipleStatusView.getViewStatus() == MultipleStatusView.STATUS_LOADING) {
                multipleStatusView.showContent();

            }
            return true;
        });

        //初始化组件
        WidgetUtils.initRecyclerView(recyclerView,20);
        Utils.initSmartRefreshLayout(this,smartRefreshLayout,getResources().getString(R.string.style_refresh_layout));
        recyclerView.setAdapter(adapter = new GoodCardViewListAdapter());
        goodInfoList = new ArrayList<>();
        loadData();
    }

    @Override
    protected TitleBar initTitle() {
        titleBar.setTitle(getPageName());
        titleBar.setLeftClickListener(view -> popToBack());
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_add_white_24dp) {
            @Override
            public void performAction(View view) {
                openPage(TradeCreateFragment.class);
            }
        });
        return null;
    }

    @Override
    protected void initListeners() {
        //下拉刷新动作
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            /**
             * 下拉刷新动作
             */
            goodInfoList.clear();
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
            params.putString(TradeDetailFragment.KEY_NAME, item.getGoodName());
            params.putString(TradeDetailFragment.KEY_PRICE, item.getPrice());
            params.putString(TradeDetailFragment.KEY_CONTACT_WAY, item.getContactInfo());
            params.putString(TradeDetailFragment.KEY_DESCRIPTION, item.getDescription());
            params.putString(TradeDetailFragment.KEY_DEAl_WAY, item.getDealWay());
            openNewPage(TradeDetailFragment.class,"params",params);
        });
    }

    private void query(){
        BmobQuery<GoodInfo> query = new BmobQuery<>();
        query.order("-createdAt").findObjects(new FindListener<GoodInfo>() {
            @Override
            public void done(List<GoodInfo> list, BmobException e) {
                if (e != null){
                    multipleStatusView.showEmpty();
                    XLogger.e(e);
                }else{
                    goodInfoList.addAll(list);
                    adapter.refresh(goodInfoList);
                }

            }
        });
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

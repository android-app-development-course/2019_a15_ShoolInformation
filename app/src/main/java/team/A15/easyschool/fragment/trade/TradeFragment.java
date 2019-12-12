package team.A15.easyschool.fragment.trade;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.MultipleStatusView;

import java.util.List;

import butterknife.BindView;
import team.A15.easyschool.DemoDataProvider;
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

    private GoodCardViewListAdapter adapter;

    private List<GoodInfo> dataList;

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
        recyclerView.setAdapter(adapter = new GoodCardViewListAdapter());
        adapter.refresh(DemoDataProvider.getGoodsList());
        //是否联网
        if (false){
            multipleStatusView.showNoNetwork();
        }else {
            //加载数据
            dataList = DemoDataProvider.getGoodsList();
            multipleStatusView.showLoading();
            loadingHandler.sendEmptyMessageDelayed(0, 5000);
            //开一个线程加载数据
        }
        //加载数据
        adapter.refresh(dataList);

    }

    @Override
    protected TitleBar initTitle() {
        toolbar.setTitle(getPageName());
        toolbar.setLeftClickListener(view -> popToBack());
        return null;
    }

    @Override
    protected void initListeners() {
        //打开详情页
        adapter.setOnItemClickListener((itemView, item, position) -> {
            Bundle params = new Bundle();
            XLogger.i(item.getGoodName());
            params.putString(TradeDetailFragment.KEY_NAME, item.getGoodName());
            params.putString(TradeDetailFragment.KEY_PRICE, item.getPrice());
            params.putString(TradeDetailFragment.KEY_CONTACT_WAY, item.getContactInfo());
            params.putString(TradeDetailFragment.KEY_DESCRIPTION, item.getDescription());
            params.putString(TradeDetailFragment.KEY_DEAl_WAY, item.getDealWay());
            //params.putString(TradeDetailFragment.KEY_IMAGE_URL, item.getImageUrl());
            openPage(TradeDetailFragment.class, params);
        });

        //发帖
        floatingActionButton.setOnClickListener(view -> openPage(TradeCreateFragment.class));
    }

}

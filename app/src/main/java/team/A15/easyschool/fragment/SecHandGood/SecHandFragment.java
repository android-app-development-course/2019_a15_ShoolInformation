package team.A15.easyschool.fragment.SecHandGood;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import butterknife.BindView;
import team.A15.easyschool.DemoDataProvider;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.GoodCardViewListAdapter;
import team.A15.easyschool.adapter.enity.FamilyEduInfo;
import team.A15.easyschool.adapter.enity.GoodInfo;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.fragment.FamilyEdu.FamilyEduDtiFragment;
import team.A15.easyschool.utils.Utils;

/**
 * @package: team.A15.easyschool.fragment.SecHandGood
 * @author: CJH
 * @description: 二手物品交易
 * @date: 2019/11/21 14:49
 * @version: 1.0
 */
@Page(name = "闲置交易")
public class SecHandFragment extends BaseFragment {

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

    private GoodCardViewListAdapter adapter;

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
    }

    @Override
    protected TitleBar initTitle() {
        toolbar.setTitle(getPageName());
        toolbar.setLeftClickListener(view -> popToBack());
        return null;
    }

    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((itemView, item, position) -> {
            Bundle params = new Bundle();
            params.putString(SecHandDtiFragment.KEY_NAME, item.getGoodName());
            params.putString(SecHandDtiFragment.KEY_PRICE, item.getPrice());
            params.putString(SecHandDtiFragment.KEY_CONTACTWAY, item.getContactInfo());
            params.putString(SecHandDtiFragment.KEY_DESCRIPTION, item.getDescription());
            openPage(FamilyEduDtiFragment.class, params);
        });
    }
}

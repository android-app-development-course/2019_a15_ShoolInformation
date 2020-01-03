package team.A15.easyschool.fragment.trade;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.Objects;

import butterknife.BindView;
import team.A15.easyschool.R;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.utils.XToastUtils;

/**
 * @package: team.A15.easyschool.fragment.SecHandGood
 * @author: CJH
 * @description: 二手信息物品详情页
 * @date: 2019/11/24 10:17
 * @version: 1.0
 */
@Page(name = "物品详情", params = {TradeDetailFragment.KEY_NAME, TradeDetailFragment.KEY_PRICE,
        TradeDetailFragment.KEY_CONTACT_WAY, TradeDetailFragment.KEY_DESCRIPTION,
        TradeDetailFragment.KEY_DEAl_WAY, TradeDetailFragment.KEY_IMAGE_URL,"params"})
public class TradeDetailFragment extends BaseFragment {
    final static String KEY_IMAGE_URL = "imageURL";
    final static String KEY_NAME = "title";
    final static String KEY_PRICE = "price";
    final static String KEY_CONTACT_WAY = "contactWay";
    final static String KEY_DESCRIPTION = "description";
    final static String KEY_DEAl_WAY = "dealWay";

    @BindView(R.id.appbar_layout_toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapse_layout)
    CollapsingToolbarLayout collapseLayout;

    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;

    @BindView(R.id.fab_scrolling)
    FloatingActionButton fabScrolling;

    @BindView(R.id.app_compat_image_view)
    AppCompatImageView appCompatImageView;

    @AutoWired(name = "params")
    Bundle bundle;

    private String imageUrl;

    private String title;

    private String price;

    private String description;

    private String contactWay;

    private String dealWay;

    /**
     * 名称
     */
    @BindView(R.id.tv_name)
    TextView tv_name;

    /**
     * 具体描述
     */
    @BindView(R.id.tv_description)
    TextView tv_description;

    /**
     * 价格
     */
    @BindView(R.id.tv_price)
    TextView tv_price;

    /**
     * 联系方式
     */
    @BindView(R.id.tv_contactWay)
    TextView tv_contactWay;

    /**
     * 交易方式
     */
    @BindView(R.id.tv_deal_way)
    TextView tv_dealWay;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trade_deatil;
    }

    @Override
    protected void initViews() {
        loadString();
        StatusBarUtils.translucent(Objects.requireNonNull(getActivity()));
        update();
    }

    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    @SuppressLint("SetTextI18n")
    private void update(){
        tv_name.setText("物品名称：     " + title);
        tv_price.setText("物品价格：     " + price);
        tv_contactWay.setText("联系方式：     " + contactWay);
        tv_dealWay.setText("交易方式：     " + dealWay);
        tv_description.setText("具体描述：\n     " + description);
//        appCompatImageView.setImageDrawable();
    }

    @Override
    protected void initListeners() {
        fabScrolling.setOnClickListener(v -> XToastUtils.toast("分享"));

        appbarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                StatusBarUtils.setStatusBarDarkMode(getActivity());
            } else {
                StatusBarUtils.setStatusBarLightMode(getActivity());
            }
        });
    }

    private void loadString(){
        title = bundle.getString(KEY_NAME);
        contactWay = bundle.getString(KEY_CONTACT_WAY);
        price = bundle.getString(KEY_PRICE);
        imageUrl = bundle.getString(KEY_IMAGE_URL);
        dealWay = bundle.getString(KEY_DEAl_WAY);
        description = bundle.getString(KEY_DESCRIPTION);
    }

    @Override
    protected TitleBar initTitle() {
        toolbar.setNavigationOnClickListener(view -> popToBack());
        return null;
    }
}


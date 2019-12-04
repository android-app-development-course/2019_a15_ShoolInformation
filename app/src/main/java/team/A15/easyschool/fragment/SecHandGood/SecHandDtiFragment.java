package team.A15.easyschool.fragment.SecHandGood;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.utils.TitleBar;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.utils.StatusBarUtils;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
@Page(name = "物品详情", params = {SecHandDtiFragment.KEY_NAME, SecHandDtiFragment.KEY_PRICE,
        SecHandDtiFragment.KEY_CONTACT_WAY, SecHandDtiFragment.KEY_DESCRIPTION,
        SecHandDtiFragment.KEY_DEAl_WAY, SecHandDtiFragment.KEY_IMAGE_URL})
public class SecHandDtiFragment extends BaseFragment {
    public final static String KEY_IMAGE_URL = "imageURL";
    public final static String KEY_NAME = "name";
    public final static String KEY_PRICE = "price";
    public final static String KEY_CONTACT_WAY = "contactWay";
    public final static String KEY_DESCRIPTION = "description";
    public final static String KEY_DEAl_WAY = "dealWay";

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

    @AutoWired(name = KEY_IMAGE_URL)
    String imageUrl;

    @AutoWired(name = KEY_NAME)
    String name;

    @AutoWired(name = KEY_PRICE)
    String price;

    @AutoWired(name = KEY_DESCRIPTION)
    String description;

    @AutoWired(name = KEY_CONTACT_WAY)
    String contactWay;

    @AutoWired(name = KEY_DEAl_WAY)
    String dealWay;

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
        return R.layout.fragment_second_hand_deatil;
    }

    @Override
    protected void initViews() {
        StatusBarUtils.translucent(getActivity());
        update();
    }
    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    private void update(){
        tv_name.setText("物品名称：     " + name);
        tv_price.setText("物品价格：     " + price);
        tv_contactWay.setText("联系方式：     " + contactWay);
        tv_dealWay.setText("交易方式：     " + dealWay);
        tv_description.setText("具体描述：\n     " + description);
        appCompatImageView.setImageDrawable(getResources().getDrawable(R.drawable.timg));
    }

    @Override
    protected void initListeners() {
        toolbar.setNavigationOnClickListener(v -> popToBack());

        fabScrolling.setOnClickListener(v -> XToastUtils.toast("分享"));

        appbarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                StatusBarUtils.setStatusBarDarkMode(getActivity());
            } else {
                StatusBarUtils.setStatusBarLightMode(getActivity());
            }
        });
    }

    @Override
    protected TitleBar initTitleBar() {
        toolbar.setTitle(getPageTitle());
        return null;
    }
}


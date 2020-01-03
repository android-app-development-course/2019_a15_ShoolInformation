package team.A15.easyschool.fragment.lost;

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
 * @package: team.A15.easyschool.fragment.competition
 * @author: CJH
 * @description:
 * @date: 2019/11/26 22:50
 * @version: 1.0
 */
@Page(name = "失物详情", params = {LostDtiFragment.KEY_TITLE, LostDtiFragment.KEY_CONTACT_WAY,
LostDtiFragment.KEY_DETAIL,"params"})
public class LostDtiFragment extends BaseFragment {
    final static  String KEY_TITLE = "title";
    final static  String KEY_CONTACT_WAY = "contactWay";
    final static  String KEY_DETAIL = "detail";

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

    private String title;

    private String contactWay;

    private String detail;

    @AutoWired(name = "params")
    Bundle bundle;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_contactWay)
    TextView tvContactWay;

    @BindView(R.id.tv_detail)
    TextView tvDetail;

    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lost_detail;
    }

    @Override
    protected void initViews() {
        loadString();
        StatusBarUtils.translucent(Objects.requireNonNull(getActivity()));
        update();
    }

    @Override
    protected TitleBar initTitle() {
        toolbar.setNavigationOnClickListener(v -> popToBack());
        toolbar.setTitle(getPageTitle());
        return null;
    }

    void update(){
        tvTitle.setText("物品名称：    " + title);
        tvContactWay.setText("联系方式：    " + contactWay);
        tvDetail.setText("详细描述：\n    " + detail);
        appCompatImageView.setImageDrawable(getResources().getDrawable(R.drawable.timg));
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
        if (bundle != null){
            title = bundle.getString(KEY_TITLE);
            contactWay = bundle.getString(KEY_CONTACT_WAY);
            detail = bundle.getString(KEY_DETAIL);
        }
    }
}

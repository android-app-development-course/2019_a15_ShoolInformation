package team.A15.easyschool.fragment.home;


import android.content.Intent;

import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import cn.bmob.v3.BmobUser;
import team.A15.easyschool.DemoDataProvider;
import team.A15.easyschool.R;
import team.A15.easyschool.activity.LoginActivity;
import team.A15.easyschool.activity.MainActivity;
import team.A15.easyschool.adapter.enity.User;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.core.webview.AgentWebActivity;
import team.A15.easyschool.fragment.LoginFragment;
import team.A15.easyschool.fragment.SettingsFragment;
import team.A15.easyschool.fragment.errands.ErrandFragment;
import team.A15.easyschool.fragment.familyEdu.FamilyEduFragment;
import team.A15.easyschool.fragment.team.TeamFragment;
import team.A15.easyschool.fragment.trade.TradeFragment;
import team.A15.easyschool.fragment.lost.LostFragment;

import team.A15.easyschool.widget.MyBannerItemList;
import team.A15.easyschool.widget.RadiusImageBanner;


@Page
public class HomeFragment extends BaseFragment implements SuperTextView.OnSuperTextViewClickListener {

    /**
     * 轮播条数据
     */
    private MyBannerItemList myBannerItemList;

    /**
     * 轮播条
     */
    @BindView(R.id.rib_news)
    RadiusImageBanner radiusImageBanner;

    /**
     * 家教信息
     */
    @BindView(R.id.stv_family_edu)
    SuperTextView familyEdu;

    /**
     * 家教信息
     */
    @BindView(R.id.stv_lost)
    SuperTextView lost;

    /**
     * 空课室
     */
    @BindView(R.id.stv_empty_classrom)
    SuperTextView emptyClassrom;

    @BindView(R.id.stv_second_hand)
    SuperTextView trade;

    @BindView(R.id.stv_team)
    SuperTextView team;

    @BindView(R.id.stv_errand)
    SuperTextView errand;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        myBannerItemList = new MyBannerItemList();
        myBannerItemList.getBannerItemList().addAll(DemoDataProvider.getBannerList());
        radiusImageBanner.setSource(myBannerItemList.getBannerItemList()).startScroll();

    }

    @Override
    protected void initListeners() {
        familyEdu.setOnSuperTextViewClickListener(this);
        emptyClassrom.setOnSuperTextViewClickListener(this);
        lost.setOnSuperTextViewClickListener(this);
        trade.setOnSuperTextViewClickListener(this);
        team.setOnSuperTextViewClickListener(this);
        radiusImageBanner.setOnItemClickListener((view, item, position) -> {
            AgentWebActivity.goWeb(getContext(), myBannerItemList.getBannerItemList().get(position).getTitle());
        });
        errand.setOnSuperTextViewClickListener(this);

    }
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @SingleClick
    @Override
    public void onClick(SuperTextView view) {
//        if (!User.isLogin()) {
//            Intent intent = new Intent(getActivity(),LoginActivity.class);
//            startActivity(intent);
//        }else{
            switch(view.getId()) {
                case R.id.stv_family_edu:
                    //  家教信息跳转
                    openNewPage(FamilyEduFragment.class);
                    break;
                case R.id.stv_lost:
                    // 寻物系统
                    openNewPage(LostFragment.class);
                    break;
                case R.id.stv_empty_classrom:
                    // 空课室跳转
                    String url = "https://i.scnu.edu.cn/zixi/?from=qrcode";
                    AgentWebActivity.goWeb(getContext(), url);
                    break;
                case R.id.stv_second_hand:
                    openNewPage(TradeFragment.class);
                    break;
                case R.id.stv_team:
                    openNewPage(TeamFragment.class);
                    break;
                case R.id.stv_errand:
                    openNewPage(ErrandFragment.class);
                default:
                    break;
            }
//        }
    }


}


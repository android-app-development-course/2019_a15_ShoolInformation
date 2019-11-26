/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package team.A15.easyschool.fragment.home;


import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;

import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

import butterknife.BindView;
import team.A15.easyschool.DemoDataProvider;
import team.A15.easyschool.R;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.core.webview.AgentWebActivity;
import team.A15.easyschool.fragment.familyEdu.FamilyEduFragment;
import team.A15.easyschool.fragment.SecHandGood.SecHandFragment;
import team.A15.easyschool.widget.RadiusImageBanner;


@Page
public class HomeFragment extends BaseFragment {

    /**
     * 轮播条数据
     */
    private List<BannerItem> bannerData;

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
     * 空课室
     */
    @BindView(R.id.stv_empty_classrom)
    SuperTextView emptyClassrom;

    @BindView(R.id.stv_second_hand)
    SuperTextView secondHand;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        //banner的数据传进去
        bannerData = DemoDataProvider.getBannerList();
        radiusImageBanner.setSource(bannerData).startScroll();
    }

    @Override
    protected void initListeners() {

//        家教信息跳转
        familyEdu.setOnSuperTextViewClickListener(superTextView -> {
            openNewPage(FamilyEduFragment.class);
        });

//        空课室跳转
        emptyClassrom.setOnSuperTextViewClickListener(superTextView -> {
            String url = "https://i.scnu.edu.cn/zixi/?from=qrcode";
            AgentWebActivity.goWeb(getContext(), url);
        });

        secondHand.setOnSuperTextViewClickListener(superTextView -> openNewPage(SecHandFragment.class));

//        轮播图点击跳转
        radiusImageBanner.setOnItemClickL(position -> AgentWebActivity.goWeb(getContext(), bannerData.get(position).getTitle()));
    }


    @Override
    protected TitleBar initTitle() {
        return null;
    }
}

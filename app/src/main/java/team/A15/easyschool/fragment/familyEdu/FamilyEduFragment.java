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

package team.A15.easyschool.fragment.familyEdu;

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
import team.A15.easyschool.DemoDataProvider;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.FamilyEduCardViewListAdapter;
import team.A15.easyschool.adapter.enity.FamilyEduInfo;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.utils.Utils;

/**
 * @package: team.A15.easyschool.fragment
 * @author: CJH
 * @description:
 * @date: 2019/11/19 13:09
 * @version: 1.0
 */
@Page(name = "家教信息")
public class FamilyEduFragment extends BaseFragment {

    /**
     * 标题栏
     */
    @BindView(R.id.toolbar)
    TitleBar titleBar;

    /**
     * 下拉刷新栏
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

    /**
     * 悬浮按钮
     */
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private FamilyEduCardViewListAdapter familyEduCardViewListAdapter;

    private List<FamilyEduInfo> dataList;

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

    /**
     * 初始化界面
     */
    @Override
    protected void initViews(){
//        初始化smartlayout
        Utils.initSmartRefreshLayout(this, smartRefreshLayout, getResources().getString(R.string.style_refresh_layout));

//        循环列表
        WidgetUtils.initRecyclerView(recyclerView, 0);
        recyclerView.setAdapter(familyEduCardViewListAdapter = new FamilyEduCardViewListAdapter());
        floatingActionButton.setVisibility(View.GONE);
        //是否联网
        if (false){
            multipleStatusView.showNoNetwork();
        }else {
            //加载数据
            dataList = DemoDataProvider.getFamilyEduInfoList();
            multipleStatusView.showLoading();
            loadingHandler.sendEmptyMessageDelayed(0, 5000);
            //开一个线程加载数据
        }
        //加载数据
        familyEduCardViewListAdapter.refresh(dataList);
    }

    final View.OnClickListener mRetryClickListener = (new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //如果网络正常
            if (false){
                //加载数据
                multipleStatusView.showLoading();
                loadingHandler.sendEmptyMessageDelayed(0, 3000);
            }else{
                multipleStatusView.showNoNetwork();
            }
        }
    });

    @Override
    protected void initListeners() {
        //下拉刷新动作
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            /**
             * 下拉刷新动作
             */
            refreshLayout.finishRefresh();
            refreshLayout.resetNoMoreData();
        },2000));


        //上拉加载更多动作
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            /**
             * 上拉加载更多动作
             */
            refreshLayout.finishLoadMore();
        },2000));



        familyEduCardViewListAdapter.setOnItemClickListener((itemView, item, position) -> {
            Bundle params = new Bundle();
            params.putString(FamilyEduDtiFragment.KEY_NUM, item.getNum());
            params.putString(FamilyEduDtiFragment.KEY_AREA,item.getArea());
            params.putString(FamilyEduDtiFragment.KEY_GRADE,item.getStuGrade());
            params.putString(FamilyEduDtiFragment.KEY_REQUIRE, item.getRequirement());
            params.putString(FamilyEduDtiFragment.KEY_SEX, item.getStuSex());
            params.putString(FamilyEduDtiFragment.KEY_SUBJECT, item.getSubject());
            params.putString(FamilyEduDtiFragment.KEY_DETAIL, item.getDetail());
            openPage(FamilyEduDtiFragment.class, params);
        });
    }

    @Override
    protected TitleBar initTitle() {
        titleBar.setTitle(getPageName());
        titleBar.setLeftClickListener(view -> popToBack());
        return null;
    }
}

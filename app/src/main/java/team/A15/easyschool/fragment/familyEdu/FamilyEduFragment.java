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

import android.os.AsyncTask;
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


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.title_bar)
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

    private FamilyEduCardViewListAdapter familyEduCardViewListAdapter;

    private List<FamilyEduInfo> familyEduInfoList;

    private int page = 1;

    private Handler loadingHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycle_view;
    }

    /**
     * 初始化界面
     */
    @Override
    protected void initViews(){
        loadingHandler = new Handler(msg -> {
            if (multipleStatusView != null && multipleStatusView.getViewStatus() == MultipleStatusView.STATUS_LOADING) {
                if (familyEduInfoList.isEmpty()){
                    multipleStatusView.showEmpty();
                }else{
                    multipleStatusView.showContent();
//                    floatingActionButton.setVisibility(View.GONE);
                }
            }
            return true;
        });

//        初始化smartlayout
        Utils.initSmartRefreshLayout(this, smartRefreshLayout, getResources().getString(R.string.style_refresh_layout));
//        循环列表
        WidgetUtils.initRecyclerView(recyclerView, 0);
        recyclerView.setAdapter(familyEduCardViewListAdapter = new FamilyEduCardViewListAdapter());
        familyEduInfoList = new ArrayList<>();
        loadData();
    }

    final View.OnClickListener retryClickListener = (v -> {
        loadData();
    });

    @Override
    protected void initListeners() {
        //下拉刷新动作
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            /**
             * 下拉刷新动作
             */
            familyEduInfoList.clear();
            page = 1;
            new FamilyEduAsyncTask().execute(page++);
            refreshLayout.finishRefresh();
        },2000));


        //上拉加载更多动作
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            /**
             * 上拉加载更多动作
             */
            new FamilyEduAsyncTask().execute(page++);
            refreshLayout.finishLoadMore(true);
        },2000));

        multipleStatusView.setOnRetryClickListener(retryClickListener);

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

    private void loadData(){
        if (Utils.isNetworkConnected(getContext())){
            new FamilyEduAsyncTask().execute(page++);
            multipleStatusView.showLoading();
            loadingHandler.sendEmptyMessageDelayed(0, 3000);
        }else {
            multipleStatusView.showNoNetwork();
        }
    }

    /**
     * 爬异步类
     */
    class FamilyEduAsyncTask extends AsyncTask<Integer, Void, Void>{
        @Override
        protected Void doInBackground(Integer... integers) {
            Document document = null;
            try {
                document = Jsoup.connect("http://module.scnu.edu.cn/index.php?m=familyedu&c=index&a=index&siteid=126&page=" + integers[0]).get();
                Elements elements = document.select(".table-list").select("tbody").select("tr");
                for (Element element : elements) {
                    FamilyEduInfo familyEduInfo = new FamilyEduInfo();
                    familyEduInfo.setNum(element.select("td").get(0).text());
                    familyEduInfo.setArea(element.select("td").get(1).text());
                    familyEduInfo.setStuGrade(element.select("td").get(2).text());
                    familyEduInfo.setStuSex(element.select("td").get(3).text());
                    familyEduInfo.setSubject(element.select("td").get(4).text());
                    familyEduInfo.setRequirement(element.select("td").get(6).text());
                    familyEduInfo.setDetail(element.select("td").get(7).text());
                    XLogger.e(familyEduInfo.toString());
                    familyEduInfoList.add(familyEduInfo);
                }
            } catch (IOException e) {
                XLogger.e(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            familyEduCardViewListAdapter.refresh(familyEduInfoList);
        }
    }
}

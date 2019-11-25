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

package team.A15.easyschool.fragment.news;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;
import com.xuexiang.xutil.net.NetworkUtils;

import butterknife.BindView;
import team.A15.easyschool.DemoDataProvider;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.NewsCardViewListAdapter;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.core.webview.AgentWebActivity;
import team.A15.easyschool.utils.Utils;


@Page
public class NewsFragment extends BaseFragment {

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.statefulLayout)
    StatefulLayout statefulLayout;

    private NewsCardViewListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        Utils.initSmartRefreshLayout(this,smartRefreshLayout, getResources().getString(R.string.style_refresh_layout));
        WidgetUtils.initRecyclerView(recyclerView, DensityUtils.dp2px(5), ThemeUtils.resolveColor(getContext(), R.attr.xui_config_color_background));
        recyclerView.setAdapter(adapter = new NewsCardViewListAdapter());
        adapter.refresh(DemoDataProvider.getDemoNewInfos());

    }

    /**
     * 不需要标题栏
     * @return
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }


    /**
     * 初始化监听器
     */
    @Override
    protected void initListeners() {
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            /**
             * 上拉加载
             * @param refreshLayout
             */
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.loadMore(DemoDataProvider.getDemoNewInfos());
                        refreshLayout.finishLoadMore();
                    }
                }, 1000);
            }

            /**
             * 下拉刷新
             * @param refreshLayout
             */
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        adapter.refresh(DemoDataProvider.getDemoNewInfos());
                        refreshLayout.finishRefresh();
                    }
                }, 3000);
            }
        });

        //点击跳转
        adapter.setOnItemClickListener((itemView, item, position) ->
                AgentWebActivity.goWeb(getContext(), adapter.getItem(position).getUrl()));

        //设置刷新加载时禁止所有列表操作
        smartRefreshLayout.setDisableContentWhenRefresh(true);
        smartRefreshLayout.setDisableContentWhenLoading(true);
        smartRefreshLayout.autoRefresh();
    }

    @Override
    protected com.xuexiang.xpage.utils.TitleBar initTitleBar() {
        return null;
    }
}

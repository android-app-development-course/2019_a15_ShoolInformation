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
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.adapter.SmartViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;
import com.xuexiang.xutil.net.NetworkUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import team.A15.easyschool.DemoDataProvider;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.NewsCardViewListAdapter;
import team.A15.easyschool.adapter.enity.FamilyEduInfo;
import team.A15.easyschool.adapter.enity.NewsInfo;
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

    private List<NewsInfo> newsInfoList;

    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        Utils.initSmartRefreshLayout(this,smartRefreshLayout, getResources().getString(R.string.style_refresh_layout));
        WidgetUtils.initRecyclerView(recyclerView, DensityUtils.dp2px(5), ThemeUtils.resolveColor(getContext(), R.attr.xui_config_color_background));
        recyclerView.setAdapter(adapter = new NewsCardViewListAdapter());
        newsInfoList = new ArrayList<>();
        new NewsAsyncTask().execute(page++);
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
                refreshLayout.getLayout().postDelayed(() -> {
                    new NewsAsyncTask().execute(page++);
                    refreshLayout.finishLoadMore();
                }, 5000);
            }

            /**
             * 下拉刷新
             * @param refreshLayout
             */
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(() -> {
                    newsInfoList.clear();
                    page = 1;
                    new NewsAsyncTask().execute(page++);
                    refreshLayout.finishRefresh();
                }, 5000);
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

    /**
     * 新闻爬虫
     */
    class NewsAsyncTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            Document document = null;
            try {
                document = Jsoup.connect("https://news.scnu.edu.cn/t/" + integers[0]).get();
                for (int i = 1; i <= 10; i++){
                    NewsInfo newsInfo = new NewsInfo();
                    String title = document.select("#tag-article > li:nth-child(" + i + ") > span > a").text();
                    String summary = document.select("#tag-article > li:nth-child(" + i + ") > p").text();
                    String temp = document.select("#tag-article > li:nth-child(" + i + ") > div.tpinfo").text();
                    String date = temp.substring(0, temp.indexOf("|")).trim();
                    String readingNum = temp.substring(temp.indexOf("|") + 1, temp.lastIndexOf("|")).trim();
                    newsInfo.setTitle(title);
                    newsInfo.setSummary(summary);
                    newsInfo.setReadingNum(readingNum);
                    newsInfo.setDate(date);

                    //设置图片链接
                    temp = document.select("#tag-article > li:nth-child(" + i + ") > div.img-thumb > img").attr("src");
                    String imageUrl = "https://news.scnu.edu.cn/" + temp.substring(0, temp.lastIndexOf('.'));
                    newsInfo.setImageUrl(imageUrl);

                    //设置跳转链接
                    temp = document.select("#tag-article > li:nth-child(" + i + ") > span > a").attr("href");
                    String url = "https://news.scnu.edu.cn" + temp;
                    newsInfo.setUrl(url);
                    newsInfoList.add(newsInfo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.refresh(newsInfoList);
        }
    }
}



package team.A15.easyschool.adapter;

import androidx.annotation.NonNull;


import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.NewsInfo;

/**
 * @package: com.xuexiang.templateproject.adapter
 * @author: CJH
 * @description: 新闻卡片的apapter
 * @date: 2019/11/14 20:52
 * @version: 1.0
 */
public class NewsCardViewListAdapter extends BaseRecyclerAdapter<NewsInfo> {
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_news_list_item;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, NewsInfo item) {
        if (item != null){
            holder.text(R.id.tv_time, item.getDate());
            holder.text(R.id.tv_summary, item.getSummary());
            holder.text(R.id.tv_title, item.getTitle());
            holder.text(R.id.tv_read, "阅读量 " + item.getReadingNum());
            holder.image(R.id.iv_image, item.getImageUrl());
        }
    }
}

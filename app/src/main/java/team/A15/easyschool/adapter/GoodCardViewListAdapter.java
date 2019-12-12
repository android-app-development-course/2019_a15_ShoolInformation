package team.A15.easyschool.adapter;


import androidx.annotation.NonNull;

import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.GoodInfo;

/**
 * @package: team.A15.easyschool.adapter
 * @author: CJH
 * @description: 闲置交易
 * @date: 2019/11/21 14:28
 * @version: 1.0
 */
public class GoodCardViewListAdapter extends BaseRecyclerAdapter<GoodInfo> {

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_trade_card_viewlist_item;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, GoodInfo item) {
        if (item != null){
            holder.text(R.id.tv_price, item.getPrice());
            holder.text(R.id.tv_tag, item.getDealWay());
            holder.text(R.id.tv_summary, item.getDescription());
            holder.text(R.id.tv_title, item.getGoodName());
            holder.text(R.id.tv_time, item.getCreatedAt());
        }
    }
}

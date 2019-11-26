package team.A15.easyschool.adapter;



import androidx.annotation.NonNull;

import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.LostInfo;

/**
 * @package: team.A15.easyschool.adapter
 * @author: CJH
 * @description:
 * @date: 2019/11/26 21:49
 * @version: 1.0
 */
public class lostCardViewAdapter extends BaseRecyclerAdapter<LostInfo> {

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_lost_card_view_list_item;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, LostInfo item) {
        if (item != null){
            holder.text(R.id.tv_title, item.getTitle());
            holder.text(R.id.tv_contactWay, item.getContactWay());
            holder.text(R.id.tv_detail, item.getDetail());
            holder.text(R.id.tv_time, item.getTime());
        }
    }
}

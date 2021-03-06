package team.A15.easyschool.adapter;

import androidx.annotation.NonNull;

import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.TeamInfo;

/**
 * @package: team.A15.easyschool.adapter.enity
 * @author: CJH
 * @description:
 * @date: 2019/12/4 16:17
 * @version: 1.0
 */
public class TeamCardViewListAdapter extends BaseRecyclerAdapter<TeamInfo> {
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_team_card_viewlist_item;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, TeamInfo item) {
        if (item != null){
            holder.text(R.id.tv_title, item.getTitle());
            holder.text(R.id.tv_date, item.getCreatedAt());
            holder.text(R.id.tv_requirement,item.getCampus());
            holder.text(R.id.tv_detail, item.getDetail());
//            holder.text(R.id.tv_campus, item.getCampus());
        }
    }
}

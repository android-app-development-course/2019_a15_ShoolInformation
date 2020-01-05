

package team.A15.easyschool.adapter;


import androidx.annotation.NonNull;


import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.FamilyEduInfo;

/**
 * @package: com.xuexiang.templateproject.adapter
 * @author: CJH
 * @description: 家教卡片的apapter
 * @date: 2019/11/14 20:53
 * @version: 1.0
 */
public class FamilyEduCardViewListAdapter extends BaseRecyclerAdapter<FamilyEduInfo> {
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_familyedu_card_viewlist_item;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, FamilyEduInfo item) {
        if (item != null){
            holder.text(R.id.tv_subject, item.getStuGrade() + item.getSubject());
            holder.text(R.id.tv_teacherReq, item.getRequirement());
            holder.text(R.id.tv_area, item.getArea());
        }
    }
}

package team.A15.easyschool.fragment.lost;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.LostInfo;
import team.A15.easyschool.adapter.lostCardViewAdapter;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.fragment.team.TeamCreateFragment;
import team.A15.easyschool.utils.Utils;

/**
 * @package: team.A15.easyschool.fragment.competition
 * @author: CJH
 * @description: 比赛组队
 * @date: 2019/11/26 21:30
 * @version: 1.0
 */
@Page(name = "寻物系统")
public class LostFragment extends BaseFragment {

    /**
     * 标题栏
     */
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    @BindView(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    private lostCardViewAdapter adapter;

    private List<LostInfo> lostInfoList;

    private Handler loadingHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycle_view;
    }

    @Override
    protected void initViews() {
        //组件初始化
        WidgetUtils.initRecyclerView(recyclerView,0);
        Utils.initSmartRefreshLayout(this, smartRefreshLayout,
                getResources().getString(R.string.style_refresh_layout));
        recyclerView.setAdapter(adapter = new lostCardViewAdapter());

        loadingHandler = new Handler(msg -> {
            if (multipleStatusView != null &&
                    multipleStatusView.getViewStatus() == MultipleStatusView.STATUS_LOADING) {
                if (lostInfoList.isEmpty()){
                    multipleStatusView.showEmpty();
                }else{
                    multipleStatusView.showContent();
                }
            }
            return true;
        });

        loadData();

    }

    @Override
    protected void initListeners() {
        //点击项目跳转
        adapter.setOnItemClickListener((itemView, item, position) -> {
            Bundle params = new Bundle();
            params.putString(LostDtiFragment.KEY_TITLE, item.getTitle());
            params.putString(LostDtiFragment.KEY_CONTACT_WAY, item.getContactWay());
            params.putString(LostDtiFragment.KEY_DETAIL, item.getDetail());
            openNewPage(LostDtiFragment.class,"params",params);
        });

    }

    @Override
    protected TitleBar initTitle() {
        titleBar.setTitle(getPageTitle());
        titleBar.setLeftClickListener(view -> popToBack());
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_add_white_24dp) {
            @Override
            public void performAction(View view) {
                openPage(LostCreateFragment.class);
            }
        });
        return null;
    }

    private void loadData(){
        if (Utils.isNetworkConnected(getContext())){
            //加载数据
            lostInfoList = new ArrayList<>();
            query();
            multipleStatusView.showLoading();
            loadingHandler.sendEmptyMessageDelayed(0, 5000);
        }else {
            multipleStatusView.showNoNetwork();
        }
    }

    private void query(){
            BmobQuery<LostInfo> query = new BmobQuery<>();
            query.order("-createdAt").findObjects(new FindListener<LostInfo>() {
                @Override
                public void done(List<LostInfo> list, BmobException e) {
                    if (e != null){
                        XLogger.e(e);
                    }else{
                        lostInfoList.addAll(list);
                        adapter.refresh(lostInfoList);
                    }
                }
            });
    }
}

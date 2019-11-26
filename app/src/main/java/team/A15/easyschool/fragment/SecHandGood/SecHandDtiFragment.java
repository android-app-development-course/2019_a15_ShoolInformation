package team.A15.easyschool.fragment.SecHandGood;


import android.widget.TextView;

import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import butterknife.BindView;
import team.A15.easyschool.R;
import team.A15.easyschool.core.BaseFragment;

/**
 * @package: team.A15.easyschool.fragment.SecHandGood
 * @author: CJH
 * @description: 二手信息物品详情页
 * @date: 2019/11/24 10:17
 * @version: 1.0
 */
@Page(name = "物品详情", params = {SecHandDtiFragment.KEY_NAME, SecHandDtiFragment.KEY_PRICE,
        SecHandDtiFragment.KEY_CONTACT_WAY, SecHandDtiFragment.KEY_DESCRIPTION})
public class SecHandDtiFragment extends BaseFragment {
//    public final static String KEY_IMAGE = "image";
    public final static String KEY_NAME = "name";
    public final static String KEY_PRICE = "price";
    public final static String KEY_CONTACT_WAY = "contactWay";
    public final static String KEY_DESCRIPTION = "description";

    @AutoWired(name = KEY_NAME)
    String name;

    @AutoWired(name = KEY_PRICE)
    String price;

    @AutoWired(name = KEY_DESCRIPTION)
    String description;

    @AutoWired(name = KEY_CONTACT_WAY)
    String contactWay;

    /**
     * 货物图片
     */
    @BindView(R.id.iv_image)
    RadiusImageView iv_image;

    @BindView(R.id.tv_name)
    TextView tv_name;

    /**
     * 具体描述
     */
    @BindView(R.id.tv_description)
    TextView tv_description;

    /**
     * 价格
     */
    @BindView(R.id.tv_price)
    TextView tv_price;

    /**
     * 联系方式
     */
    @BindView(R.id.tv_contactWay)
    TextView tv_contactWay;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second_hand_deatil;
    }

    @Override
    protected void initViews() {
        update();
    }
    @Override
    protected void initArgs() {
        XRouter.getInstance().inject(this);
    }

    private void update(){
        tv_name.setText("物品名称：     " + name);
        tv_price.setText("物品价格：     " + price);
        tv_contactWay.setText("联系方式：     " + contactWay);
        tv_description.setText("具体描述：\n" + description);
    }
}


package team.A15.easyschool.fragment.team;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.luck.picture.lib.config.PictureConfig;
import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.toast.XToast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobACL;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.TeamInfo;
import team.A15.easyschool.adapter.enity.User;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.utils.XToastUtils;

/**
 * @package: team.A15.easyschool.fragment.team
 * @author: CJH
 * @description:
 * @date: 2019/12/23 22:37
 * @version: 1.0
 */
@Page(name = "发布信息")
public class TeamCreateFragment extends BaseFragment {

    @BindView(R.id.et_title)
    EditText etTitle;

    @BindView(R.id.et_campus)
    EditText etCampus;

    @BindView(R.id.et_date)
    EditText etDate;

    @BindView(R.id.et_contact_way)
    EditText etContactWay;

    @BindView(R.id.et_detail)
    MultiLineEditText etDetail;


    @BindView(R.id.btn_send)
    RoundButton btnSend;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_team_create;
    }

    @Override
    protected void initViews() {

    }

    @OnClick(R.id.btn_send)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send: {
                String title = etTitle.getText().toString();
                String campus = etCampus.getText().toString();
                String contactWay = etContactWay.getText().toString();
                String date = etDate.getText().toString();
                String detail = etDetail.getEditText().getText().toString();
                boolean textEmpty = title.isEmpty() || campus.isEmpty() ||
                        contactWay.isEmpty() || date.isEmpty() || detail.isEmpty();
                if (textEmpty) {
                    XToastUtils.warning("请完整填写信息");
                } else {
                    //信息封装成类
                    TeamInfo teamInfo = new TeamInfo();
                    teamInfo.setDate(date);
                    teamInfo.setContactWay(contactWay);
                    teamInfo.setCampus(campus);
                    teamInfo.setTitle(title);
                    teamInfo.setDetail(detail);
                    teamInfo.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e != null) {
                                XLogger.e(e);
                                XToastUtils.error("发布失败");
                            } else {
                                XToastUtils.success("发布成功");
                                popToBack();
                            }
                        }
                    });
                }
                break;
            }
            default:
                break;
        }
    }
}

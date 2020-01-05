package team.A15.easyschool.fragment.lost;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import team.A15.easyschool.R;
import team.A15.easyschool.adapter.enity.LostInfo;
import team.A15.easyschool.core.BaseFragment;
import team.A15.easyschool.utils.Utils;
import team.A15.easyschool.utils.XToastUtils;

/**
 * @package: team.A15.easyschool.fragment.lost
 * @author: CJH
 * @description:
 * @date: 2019/12/24 19:35
 * @version: 1.0
 */
@Page(name = "创建失物信息")
public class LostCreateFragment extends BaseFragment {
    @BindView(R.id.et_detail)
    MultiLineEditText etDetail;

    @BindView(R.id.et_title)
    EditText etTitle;

    @BindView(R.id.et_contact_way)
    EditText etContactWay;

    private List<LocalMedia> localMediaList = new ArrayList<>();

    @BindView(R.id.btn_add_pic)
    ImageButton btnAddPic;

    private File imagefile;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lost_create;
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.btn_add_pic, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_add_pic:
                Utils.getPictureSelector(this)
                        .selectionMedia(localMediaList)
                        .maxSelectNum(1)
                        .selectionMode(PictureConfig.SINGLE)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.btn_send:
                String title = etTitle.getText().toString();
                String contactWay = etContactWay.getText().toString();
                String detail = etDetail.getEditText().getText().toString();
                boolean textEmpty = title.isEmpty() ||
                        contactWay.isEmpty()||
                        detail.isEmpty();
                if (textEmpty){
                    XToastUtils.warning("请完整填写信息");
                }else{
                    //信息封装成类
                    LostInfo lostInfo = new LostInfo();
                    lostInfo.setContactWay(contactWay);
                    lostInfo.setDetail(detail);
                    lostInfo.setTitle(title);
                    if (imagefile != null && imagefile.exists()){
                        BmobFile bmobFile = new BmobFile(imagefile);
                        bmobFile.uploadblock(new UploadFileListener() {
                            @Override
                            public void done(BmobException e) {
                                //将获得的图片url存下来
                                if (e == null){
                                    lostInfo.setImageUrl(bmobFile.getFileUrl());
                                }else{
                                    XLogger.e(e);
                                }

                            }
                        });
                    }
                    lostInfo.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e != null){
                                XToastUtils.error("发布失败");
                                XLogger.e(e);
                            }else{
                                XToastUtils.success("发布成功");
                                popToBack();
                            }
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    localMediaList = PictureSelector.obtainMultipleResult(data);
                    String imagePath = localMediaList.get(0).getCompressPath();
                    imagefile = new File(imagePath);
                    if (imagefile.exists()) {
                        XToastUtils.success("添加图片成功");
                        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                        btnAddPic.setImageBitmap(bitmap);
                    } else {
                        XToastUtils.error("添加图片失败");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

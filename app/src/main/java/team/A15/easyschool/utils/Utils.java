package team.A15.easyschool.utils;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;

import java.lang.reflect.Constructor;

/**
 * @package: team.A15.easyschool.utils
 * @author: CJH
 * @description:
 * @date: 2019/11/19 20:15
 * @version: 1.0
 */
public class Utils {

    /**
     * 下拉框架初始化
     * @param fragment
     * @param smartRefreshLayout
     * @param style
     */
    public static void initSmartRefreshLayout(Fragment fragment, SmartRefreshLayout smartRefreshLayout, String style){
        RefreshHeader header = null;
        try {
            Class<?> headerClass = Class.forName(style);
            Constructor<?> constructor = headerClass.getConstructor(Context.class);
            header = (RefreshHeader) constructor.newInstance(fragment.getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (header != null) {
            smartRefreshLayout.setRefreshHeader(header);
            smartRefreshLayout.autoRefresh();
        }
    }

}

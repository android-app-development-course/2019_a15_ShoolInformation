/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package team.A15.easyschool;

import android.graphics.BitmapFactory;

import com.xuexiang.xaop.logger.XLogger;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import team.A15.easyschool.adapter.enity.FamilyEduInfo;
import team.A15.easyschool.adapter.enity.GoodInfo;
import team.A15.easyschool.adapter.enity.NewsInfo;
import team.A15.easyschool.utils.XToastUtils;

//import com.xuexiang.xuidemo.adapter.entity.NewInfo;
//import com.xuexiang.xuidemo.fragment.components.imageview.preview.ImageViewInfo;
//import com.xuexiang.xuidemo.fragment.components.imageview.preview.NineGridInfo;

/**
 * 演示数据
 *
 * @author xuexiang
 * @since 2018/11/23 下午5:52
 */
public class DemoDataProvider {

    /**
     * 页数
     */
    private static final int pageLimit = 10;

    /**
     *
     */
    private static int goodSkipPage = 0;


    public static String[] titles = new String[]{
            "https://news.scnu.edu.cn/28034",
            "https://news.scnu.edu.cn/26824",
            "https://news.scnu.edu.cn/26325",
            "https://news.scnu.edu.cn/25364",
            "https://news.scnu.edu.cn/25199"
    };

    public static String[] urls = new String[]{//640*360 360/640=0.5625
            "https://news.scnu.edu.cn/media/image/2019/11/20191115af6672.jpg",
            "https://news.scnu.edu.cn/media/image/2019/09/201909306b4569.jpg",
            "https://news.scnu.edu.cn/media/image/2019/09/20190920c1a6fc.jpg",
            "https://news.scnu.edu.cn/media/image/2019/07/201907010dd858.jpg",
            "https://news.scnu.edu.cn/media/image/2019/06/2019061761b3b5.jpg",
    };

    public static List<BannerItem> getBannerList() {
        ArrayList<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.imgUrl = urls[i];
            item.title = titles[i];

            list.add(item);
        }
        return list;
    }

    public static List<FamilyEduInfo> getFamilyEduInfoList(){
        List<FamilyEduInfo> list = new ArrayList<>();
        list.add(new FamilyEduInfo("203929", "黄埔区", "一年级、四年级",
                "男", "小学全科作业辅导", "未填写 / /",
                "【请两名教师轮流辅导】学生是一名一年级、一名四年级两个孩子，成绩中上，现想请2名教师轮流进行作业辅导。要求教师有教学方法，师范生或教师父母是教师者优先。辅导时间：一周5次，周一至周五晚18:00开始辅导，2h/次，可以留教师吃饭，饭前饭后各辅导1h。辅导价格：50元/h，报销车费。辅导地点：黄埔区丰乐南路金逸雅居。参考路线【本部】地铁华师站→地铁裕丰围站（换乘2次，共13站）约35mins；【大学城】地铁大学城北站→地铁裕丰围站（换乘2次，共7站）约25mins")
        );
        list.add(new FamilyEduInfo("203583", "番禺区", "初三",
                "男", "生物", "女",
                "【请1~2位教师分别轮流辅导】 学生是一位初一男生，做作业不够自觉，想请1~2位教师分别轮流辅导全科作业，主要是监督和答疑 要求教师：男生优先，研究生优先 辅导时间：周一至周五晚7：30~9:30，具体时间协商，2h/次，5次周 辅导价格：60元/h，数学系，英语系，研究生70元/h，不报销车费 辅导地点：天河区林和东路中怡城市花园 参考路线：【本部】地铁华师站→地铁林和西站（换乘一次共4站）约15mins【大学城】地铁大学城北站（换乘三次共10站）约35mins")
        );
        list.add(new FamilyEduInfo("9530", "越秀区", "初二",
                "女", "地理", "男",
                "【紧急家教】学生是一位一年级的女生，有点小顽皮，现想找一位教师辅导其全科。要求教师是女教师，仪态端正。 辅导时间：3-4次/周，2h/次，周一至周五19:00-21:00、周末，具体协商。 辅导价格：50-70元/h，具体协商，报销车费。 辅导地点：天河区元邦明月星辉小区。 参考路线：【本部】地铁华师站→地铁长寿路站（换乘1次，共11站）约35min【大学城】地铁大学城北站→地铁长寿路站（换乘3次，共18站）约55min")
        );
        return list;
    }

    public static List<NewsInfo> getDemoNewInfos(){
        List<NewsInfo> list = new ArrayList<>();
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setSummary("11月18日上午，华南师范大学2019年教学节暨双创周开幕式在石牌校区国际会议厅 ...");
        newsInfo.setReadingNum("388");
        newsInfo.setDate("2019-11-20 16:52:17");
        newsInfo.setTitle("我校举办首届教学节暨双创周开幕式");
        newsInfo.setImageUrl("https://news.scnu.edu.cn/media/image/2019/11/201911203439b4.jpg");
        newsInfo.setUrl("https://news.scnu.edu.cn/28259");
        list.add(newsInfo);
        NewsInfo newsInfo1 = new NewsInfo();
        newsInfo1.setSummary("为进一步学习领会习近平总书记提出“人工智能是新一轮科技革命和产业变革的重要驱动力 ...");
        newsInfo1.setReadingNum("371");
        newsInfo1.setDate("2019-11-20 10:40:10");
        newsInfo1.setTitle("金耀初为党委中心理论学习扩大会议作“人工智能”专题辅导报告");
        newsInfo1.setImageUrl("https://news.scnu.edu.cn/media/image/2019/11/20191119af9a34.jpg");
        newsInfo1.setUrl("https://news.scnu.edu.cn/28249");
        list.add(newsInfo1);
        return list;
    }

    public static List<GoodInfo> getGoodsList(){
        List<GoodInfo> list = new ArrayList<>();
        GoodInfo goodInfo = new GoodInfo();
        goodInfo.setGoodName("三体");
        goodInfo.setDescription("去年购入，一套全有，95新，无笔记，无折角");
        goodInfo.setPrice("70");
        goodInfo.setContactInfo("13229798448（手机号）");
        //goodInfo.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574830701878&di=2b17a638319e3068649526244d34b9d5&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D2944261094%2C2808495216%26fm%3D214%26gp%3D0.jpg");
        goodInfo.setDealWay("大学城面交");
        list.add(goodInfo);

        GoodInfo goodInfo1 = new GoodInfo();
        goodInfo1.setGoodName("蓝月亮洗衣液");
        goodInfo1.setDescription("双十一购入，买太多了，现出两瓶500ml的");
        goodInfo1.setPrice("40");
        goodInfo1.setContactInfo("石牌东十九XXX敲门面交");
        //goodInfo1.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574830833738&di=fad19cef6b3062a890b84af5c305e418&imgtype=0&src=http%3A%2F%2Fpic5.58cdn.com.cn%2Fzhuanzh%2Fn_v2634e206e722249a290e0e323b64bac02.jpg%3Fw%3D750%26h%3D0");
        goodInfo1.setDealWay("石牌面交");
        list.add(goodInfo1);
        return list;
//        List<GoodInfo> retult = new ArrayList<GoodInfo>();
//        BmobQuery<GoodInfo> query = new BmobQuery<>();
//        query.setLimit(pageLimit).setSkip(goodSkipPage).order("-createdAt").findObjects(new FindListener<GoodInfo>() {
//            @Override
//            public void done(List<GoodInfo> list, BmobException e) {
//
//            }
//        });
//        goodSkipPage++;
//        return retult;
    }

//    public static List<Object> getUsertGuides() {
//        List<Object> list = new ArrayList<>();
//        list.add(R.drawable.guide_img_1);
//        list.add(R.drawable.guide_img_2);
//        list.add(R.drawable.guide_img_3);
//        list.add(R.drawable.guide_img_4);
//        return list;
//    }
//
//    public static Class<? extends ViewPager.PageTransformer> transformers[] = new Class[]{
//            DepthTransformer.class,
//            FadeSlideTransformer.class,
//            FlowTransformer.class,
//            RotateDownTransformer.class,
//            RotateUpTransformer.class,
//            ZoomOutSlideTransformer.class,
//    };
//
//
//    public static String[] dpiItems = new String[]{
//            "480 × 800",
//            "1080 × 1920",
//            "720 × 1280",
//    };
//
//    public static AdapterItem[] menuItems = new AdapterItem[]{
//            new AdapterItem("登陆", R.drawable.icon_password_login),
//            new AdapterItem("筛选", R.drawable.icon_filter),
//            new AdapterItem("设置", R.drawable.icon_setting),
//    };
//
//    public static List<List<ImageViewInfo>> sPics;
//    public static List<List<ImageViewInfo>> sVideos;
//
//    public static List<ImageViewInfo> imgs;
//    public static List<ImageViewInfo> videos;
//
//    public static List<List<NineGridInfo>> sNineGridPics;
//    public static List<List<NineGridInfo>> sNineGridVideos;
//
//
//    static {
//        imgs = new ArrayList<>();
//        List<String> list = getUrls();
//        for (int i = 0; i < list.size(); i++) {
//            imgs.add(new ImageViewInfo(list.get(i)));
//        }
//
//        videos = getVideos();
//
//        sPics = split(imgs, 10);
//        sVideos = split(videos, 10);
//
//        sNineGridPics = split(getMediaDemos(40, 0), 10);
//        sNineGridVideos = split(getMediaDemos(20, 1), 10);
//
//    }
//
//    private static List<NineGridInfo> getMediaDemos(int length, int type) {
//        List<NineGridInfo> list = new ArrayList<>();
//        NineGridInfo info;
//        for (int i = 0; i < length; i++) {
//            info = new NineGridInfo("我是一只喵，快乐的星猫～～～", getRandomMedias((int) (Math.random() * 10 + 0.5), type))
//                .setShowType(NineGridImageView.STYLE_FILL);
//            list.add(info);
//        }
//        return list;
//    }
//
//    private static List<ImageViewInfo> getRandomMedias(int length, int type) {
//        List<ImageViewInfo> list = new ArrayList<>();
//        for (int i = 0; i < length; i++) {
//            if (type == 0) {
//                list.add(imgs.get(i));
//            } else {
//                list.add(videos.get(i));
//            }
//        }
//        return list;
//    }
//
//
//    private static List<ImageViewInfo> getVideos() {
//        List<ImageViewInfo> videos = new ArrayList<>();
//        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
//                "http://pic.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
//        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
//                "http://pic.vjshi.com/2017-05-25/b146e104069c2bd0590bb919269193c4/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
//        videos.add(new ImageViewInfo("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg"));
//        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-05-07/d0bbfc4ac4dd173cc93873ed4eb0be53.mp4",
//                "http://pic.vjshi.com/2017-05-07/d0bbfc4ac4dd173cc93873ed4eb0be53/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
//
//        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-07-18/80d08ce1a84adfbaed5c7067b73d19ed.mp4",
//                "http://pic.vjshi.com/2017-07-18/80d08ce1a84adfbaed5c7067b73d19ed/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
//        videos.add(new ImageViewInfo("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg"));
//        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
//                "http://pic.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
//        videos.add(new ImageViewInfo("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg"));
//        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2018-06-07/cf673556cce54ab9cf4633fd7d9d0d46.mp4",
//                "http://pic.vjshi.com/2018-06-06/caa296729c8e6e41e6aff2aadf4feff3/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
//        videos.add(new ImageViewInfo("http://img44.photophoto.cn/20170730/0018090594006661_s.jpg"));
//        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a.mp4",
//                "http://pic.vjshi.com/2017-09-13/f55a900d89679ac1c9837d5b5aaf632a/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
//        videos.add(new ImageViewInfo("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg"));
//        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2018-01-27/5169bb7bdd7386ce7bd4ce1739229424.mp4",
//                "http://pic.vjshi.com/2018-01-27/5169bb7bdd7386ce7bd4ce1739229424/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
//        videos.add(new ImageViewInfo("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png"));
//        videos.add(new ImageViewInfo("http://lmp4.vjshi.com/2017-09-27/9a6e69f7c257ff7b7832e8bac6fddf82.mp4",
//                "http://pic.vjshi.com/2017-09-27/9a6e69f7c257ff7b7832e8bac6fddf82/online/puzzle.jpg?x-oss-process=style/resize_w_285_crop_h_428"));
//        videos.add(new ImageViewInfo("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png"));
//        return videos;
//    }
//
//    private static List<String> getUrls() {
//        List<String> urls = new ArrayList<>();
//        urls.add("http://img4.duitang.com/uploads/item/201307/02/20130702113059_UEGL2.jpeg");
//        urls.add("http://img0.imgtn.bdimg.com/it/u=985035006,79865976&fm=21&gp=0.jpg");
//        urls.add("http://img5.imgtn.bdimg.com/it/u=1774291582,2563335167&fm=21&gp=0.jpg");
//        urls.add("http://img5.imgtn.bdimg.com/it/u=1511364704,3337189105&fm=21&gp=0.jpg");
//        urls.add("http://pic.qiantucdn.com/58pic/11/90/83/96a58PICrRx.jpg");
//        urls.add("http://pic.qiantucdn.com/58pic/13/09/97/26W58PICKNk_1024.jpg");
//        urls.add("http://img1.imgtn.bdimg.com/it/u=3272030875,860665188&fm=21&gp=0.jpg");
//        urls.add("http://img1.imgtn.bdimg.com/it/u=2237658959,3726297486&fm=21&gp=0.jpg");
//        urls.add("http://img1.imgtn.bdimg.com/it/u=3016675040,1510439865&fm=21&gp=0.jpg");
//        urls.add("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png");
//
//        urls.add("http://img0.imgtn.bdimg.com/it/u=556618733,1205300389&fm=21&gp=0.jpg");
//        urls.add("http://img1.imgtn.bdimg.com/it/u=3272030875,860665188&fm=21&gp=0.jpg");
//        urls.add("http://img1.imgtn.bdimg.com/it/u=2237658959,3726297486&fm=21&gp=0.jpg");
//        urls.add("http://img1.imgtn.bdimg.com/it/u=3016675040,1510439865&fm=21&gp=0.jpg");
//        urls.add("http://photocdn.sohu.com/20160307/mp62252655_1457334772519_2.png");
//        urls.add("http://d040779c2cd49.scdn.itc.cn/s_big/pic/20161213/184474627873966848.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/36f0523ee1888a57.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/07915a0154ac4a64.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/9ec4bc44bfaf07ed.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/fa85037f97e8191f.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/de13315600ba1cff.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/9ec4bc44bfaf07ed.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/fa85037f97e8191f.jpg");
//        urls.add("ttp://ac-QYgvX1CC.clouddn.com/de13315600ba1cff.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/ad99de83e1e3f7d4.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/15c5c50e941ba6b0.jpg");
//        urls.add("http://ac-QYgvX1CC.clouddn.com/eaf1c9d55c5f9afd.jpg");
//        urls.add("http://pic44.photophoto.cn/20170802/0017030376585114_b.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0847085702814554_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170802/0017030319134956_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170731/0838084023987260_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170731/0838084009134421_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170731/0838084002855326_s.jpg");
//
//        urls.add("http://img44.photophoto.cn/20170731/0847085207211178_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0017030319740534_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170731/0838084002855326_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085969586424_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0014105802293676_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0847085242661101_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0886088744582079_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170801/0017029514287804_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170730/0018090594006661_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0847085848134910_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0847085581124963_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg");
//
//        urls.add("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085200668628_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085246003750_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085012707934_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0005018303330857_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0847085231427344_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0847085236829578_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085729490157_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0847085751995287_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085729043617_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0847085786392651_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085761440022_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0847085275244570_s.jpg");
//
//
//        urls.add("http://img44.photophoto.cn/20170722/0847085858434984_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085781987193_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085707961800_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170720/0847085716198074_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170720/0847085769259426_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085717385169_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085789079771_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085265005650_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170730/0008118269110532_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170731/0008118203762697_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0008118269666722_s.jpg");
//
//        urls.add("http://img44.photophoto.cn/20170722/0847085858434984_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085781987193_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085707961800_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170720/0847085716198074_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170720/0847085769259426_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085717385169_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085789079771_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085265005650_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170730/0008118269110532_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170731/0008118203762697_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0008118269666722_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085858434984_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085781987193_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085707961800_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170720/0847085716198074_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170720/0847085769259426_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085717385169_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085789079771_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170722/0847085229451104_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170721/0847085757949071_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085265005650_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170730/0008118269110532_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170731/0008118203762697_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0008118269666722_s.jpg");
//
//        urls.add("http://img44.photophoto.cn/20170731/0847085207211178_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0017030319740534_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170731/0838084002855326_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170728/0847085969586424_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0014105802293676_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0847085242661101_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170727/0886088744582079_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170801/0017029514287804_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170730/0018090594006661_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0847085848134910_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0847085581124963_s.jpg");
//        urls.add("http://img44.photophoto.cn/20170729/0847085226124343_s.jpg");
//
//        return urls;
//    }
//
//
//    public static List<ImageViewInfo> getGifUrls() {
//        List<ImageViewInfo> userViewInfos = new ArrayList<>();
//        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/8Q8Vy8jh6wEYCT4bYiEAOZdmzIf7GrLQ.gif_s400x0"));
//        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/yCPIVl3icfbIhZ1rjKKU6Kl6lCKkC8Wq.gif_s400x0"));
//        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/mQK3vlOYVOIpnhNYKg6XuWqpc3yAg9hR.gif_s400x0"));
//        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/mESQBeZn5V8Xzke0XPsnEEXUF9MaU3sA.gif_s400x0"));
//        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/HFuVvydFj7dgIEcbEBMA9ccGcGOFdEsx.gif_s400x0"));
//        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/SH0FB6FnTNgoCsVtxcAMtSNfV7XxXmo8.gif"));
//        userViewInfos.add(new ImageViewInfo("http://img.soogif.com/KkB9WARG3PFrz9EEX4DJdiy6Vyg95fGl.gif"));
//        return userViewInfos;
//    }
//
//    /**
//     * 拆分集合
//     *
//     * @param <T>
//     * @param resList 要拆分的集合
//     * @param count   每个集合的元素个数
//     * @return 返回拆分后的各个集合
//     */
//    public static <T> List<List<T>> split(List<T> resList, int count) {
//        if (resList == null || count < 1)
//            return null;
//        List<List<T>> ret = new ArrayList<>();
//        int size = resList.size();
//        if (size <= count) { //数据量不足count指定的大小
//            ret.add(resList);
//        } else {
//            int pre = size / count;
//            int last = size % count;
//            //前面pre个集合，每个大小都是count个元素
//            for (int i = 0; i < pre; i++) {
//                List<T> itemList = new ArrayList<>();
//                for (int j = 0; j < count; j++) {
//                    itemList.add(resList.get(i * count + j));
//                }
//                ret.add(itemList);
//            }
//            //last的进行处理
//            if (last > 0) {
//                List<T> itemList = new ArrayList<>();
//                for (int i = 0; i < last; i++) {
//                    itemList.add(resList.get(pre * count + i));
//                }
//                ret.add(itemList);
//            }
//        }
//        return ret;
//    }
//
//
//    @MemoryCache
//    public static Collection<String> getDemoData() {
//        return Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
//    }
//
//    @MemoryCache
//    public static Collection<String> getDemoData1() {
//        return Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18");
//    }
//
//    /**
//     * 用于占位的空信息
//     *
//     * @return
//     */
//    @MemoryCache
//    public static List<NewInfo> getEmptyNewInfo() {
//        List<NewInfo> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add(new NewInfo());
//        }
//        return list;
//    }
//
//    /**
//     * 用于占位的空信息
//     *
//     * @return
//     */
//    @MemoryCache
//    public static List<NewInfo> getDemoNewInfos() {
//        List<NewInfo> list = new ArrayList<>();
//        list.add(new NewInfo("源码", "Android源码分析--Android系统启动")
//                .setSummary("其实Android系统的启动最主要的内容无非是init、Zygote、SystemServer这三个进程的启动，他们一起构成的铁三角是Android系统的基础。")
//                .setDetailUrl("https://juejin.im/post/5c6fc0cdf265da2dda694f05")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2019/2/22/16914891cd8a950a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//
//        list.add(new NewInfo("Android UI", "XUI 一个简洁而优雅的Android原生UI框架，解放你的双手")
//                .setSummary("涵盖绝大部分的UI组件：TextView、Button、EditText、ImageView、Spinner、Picker、Dialog、PopupWindow、ProgressBar、LoadingView、StateLayout、FlowLayout、Switch、Actionbar、TabBar、Banner、GuideView、BadgeView、MarqueeView、WebView、SearchView等一系列的组件和丰富多彩的样式主题。\n")
//                .setDetailUrl("https://juejin.im/post/5c3ed1dae51d4543805ea48d")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2019/1/16/1685563ae5456408?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//
//        list.add(new NewInfo("面试", "写给即将面试的你")
//                .setSummary("最近由于公司业务发展，需要招聘技术方面的人才，由于我在技术方面比较熟悉，技术面的任务就交给我了。今天我要分享的就和面试有关，主要包含技术面的流程、经验和建议，避免大家在今后的面试过程中走一些弯路，帮助即将需要跳槽面试的人。")
//                .setDetailUrl("https://juejin.im/post/5ca4df966fb9a05e4e58320c")
//                .setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554629219186&di=6cdab5cfceaae1f7e6d78dbe79104c9f&imgtype=0&src=http%3A%2F%2Fimg.qinxue365.com%2Fuploads%2Fallimg%2F1902%2F4158-1Z22FZ64E00.jpg"));
//
//        list.add(new NewInfo("Android", "XUpdate 一个轻量级、高可用性的Android版本更新框架")
//                .setSummary("XUpdate 一个轻量级、高可用性的Android版本更新框架。本框架借鉴了AppUpdate中的部分思想和UI界面，将版本更新中的各部分环节抽离出来，形成了如下几个部分：")
//                .setDetailUrl("https://juejin.im/post/5b480b79e51d45190905ef44")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2018/7/13/16492d9b7877dc21?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//
//
//        list.add(new NewInfo("Android/HTTP", "XHttp2 一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp进行组装")
//                .setSummary("一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。还不赶紧点击使用说明文档，体验一下吧！")
//                .setDetailUrl("https://juejin.im/post/5b6b9b49e51d4576b828978d")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2018/8/9/1651c568a7e30e02?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//        return list;
//    }

}

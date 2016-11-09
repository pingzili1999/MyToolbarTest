package com.clock.systemui.activity.translucent;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.clock.systemui.R;
import com.clock.systemui.activity.base.BaseAllActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/10/20.
 */

public class AllTranslucentActivity extends BaseAllActivity{
    ConvenientBanner convenientBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        convenientBanner = (ConvenientBanner) findViewById(R.id.frm_home_Banner);
        List<Integer>localImages = new ArrayList<>();
        localImages.add(R.mipmap.ic_car_benz_iv);
        localImages.add(R.mipmap.ic_car_iv);
        localImages.add(R.mipmap.ic_product_banner);
        Toolbar toolbar = getToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("haha");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_bannners_point, R.mipmap.ic_banners_point_on})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }
    //bannner
//    public class LocalImageHolderView implements Holder<BannercaterecommendBean.BannerBean> {
//        private ImageView imageView;
//
//        @Override
//        public View createView(Context context) {
//            imageView = new ImageView(context);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            return imageView;
//        }
//
//        @Override
//        public void UpdateUI(Context context, int position, BannercaterecommendBean.BannerBean bannerBean) {
//            ImageLoader.getInstance().displayImage(MyUtils.ImageURL.concat(bannerBean.getBanner()), imageView, CommonUtil.displayImageOptions);
//        }
//    }

    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }

    // 开始自动翻页
    @Override
    public void onResume() {
        super.onResume();
        convenientBanner.startTurning(2000);


    }


    // 停止自动翻页
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }
}

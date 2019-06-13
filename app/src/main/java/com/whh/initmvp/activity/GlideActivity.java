package com.whh.initmvp.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.whh.baselib.activity.BaseActivity;
import com.whh.initmvp.R;

/**
 * Created by wuhuihui on 2019/5/17.
 * Glide图片加载
 * 参考文章：https://www.jianshu.com/p/7ce7b02988a4
 * https://www.jianshu.com/p/791ee473a89b
 * 可以显示gif图，也能显示视频，但只是本地视频
 */

public class GlideActivity extends BaseActivity {

    private LinearLayout images_layout;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("Glide加载图片", R.layout.activity_glide);

        images_layout = (LinearLayout) findViewById(R.id.images_layout);
        for (int i = 0; i < 5; i++) {
            loadImage();
        }

    }

    private void loadImage() {
        View glide_image = LayoutInflater.from(activity).inflate(R.layout.layout_glide_image, null);
        imageView = (ImageView) glide_image.findViewById(R.id.imageView);
        String url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.picloading) //加载过程中显示的图片
                .error(R.drawable.picerror) //加载失败后显示的图片
                .thumbnail(0.5f) //设置缩略图：加载原图的0.5倍
                .override(400, 240) //裁剪图片的大小，单位px
                .skipMemoryCache(true) //支持内存缓存（默认打开）

                .diskCacheStrategy(DiskCacheStrategy.ALL)
     /* DiskCacheStrategy.NONE 什么都不缓存
        DiskCacheStrategy.SOURCE 只缓存全尺寸图
        DiskCacheStrategy.RESULT 只缓存最终的加载图
        DiskCacheStrategy.ALL 缓存所有版本图（默认行为）*/
                .priority(Priority.HIGH) //设置优先级

                .into(imageView);
        images_layout.addView(glide_image);
    }


}

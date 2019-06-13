package com.whh.baselib.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;

/**
 * Created by wuhuihui on 2019/6/12.
 */

public class ToosUtils {


    public class BaseDao<T> {

        private Class<T> clazz;

        // 使用反射技术得到T的真实类型
        public Class getRealType() {
            // 获取当前new的对象的泛型的父类类型
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            // 获取第一个类型参数的真实类型
            this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
            return clazz;
        }

    }

    class ClassB extends BaseDao<String> {

        public void main(String[] args) {
            ClassB classB = new ClassB();
            Class realType = classB.getRealType();
            System.out.println(realType.getName());
        }
    }


    /**
     * 从assets中读取图片
     */
    private Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}

package com.jialin.modularization.IInterceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

@Interceptor(priority = 1)
public class IInterceptor_test_1 implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if(postcard.getPath().equals("/model_one/main/mainActivity")){ // 添加路由用于拦截
            Log.e("IInterceptor_test_1----","我拦住了!");
        }
//        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {

    }
}

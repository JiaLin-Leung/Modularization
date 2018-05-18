package com.jialin.modularization.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jialin.modularization.R;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    /**
     * 生命周期第一步
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
    }

    @Override
    /**
     * 生命周期第二步,实现数据获取
     */
    protected void onResume() {
        super.onResume();
        initData();
    }

    /**
     * 输入信息的非空判断
     * @param str_1 参数1
     * @param str_2 参数2
     */
    public int Judge_empty(Context context, String str_1, String str_2, String str_1_name, String str_2_name){

        if(str_1.equals("")){
            showToask(context,str_1_name+"不能为空哦!",3);
            return -1;
        }else if(str_2.equals("")){
            showToask(context,str_2_name+"不能为空哦!",3);
            return -1;
        }else{
            return 1;
        }
    }

    /**
     * Toask 公共Toask方法
     * @param context 上下文
     * @param message 需要展示的信息
     * @param during 展示时间
     */
    public void showToask(Context context,String message,int during){
        Toast.makeText(context,message,during).show();
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    private static long lastClick = 0;
    /**
     * [防止快速点击]
     *
     * @return
     */
    public boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 1000) {
            Toast.makeText(this,"手太快",Toast.LENGTH_SHORT).show();
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     * 全局封装返回键(返回时候无需保存的情况)
     */
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.all_title_img:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 抛出返回按钮,如果点击返回需要保存东西,以便于返回的时候页面提示保存
     * @return 返回键控件
     */
    public ImageView getBaseBack() {
        return (ImageView) findViewById(R.id.all_title_img);
    }

    /**
     * 抛出帮助按钮,页面处理帮助逻辑
     * @return 帮助键控件
     */
    public ImageView getHelpImg(){
        return findViewById(R.id.all_title_img_help);
    }

    /**
     * 设置标题
     * @param title 标题的文本
     */
    public void setTitle(String title) {
        TextView baseTitle = (TextView) findViewById(R.id.all_title_text);
        baseTitle.setText(title);
    }

    /**
     * 初始化控件方法
     */
    public abstract void initView();

    /**
     * 初始化数据方法
     */
    public abstract void initData();

    /**
     * 布局文件引入方法
     * @return
     */
    public abstract int getLayout();
}

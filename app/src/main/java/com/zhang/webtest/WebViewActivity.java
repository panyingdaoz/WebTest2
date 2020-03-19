package com.zhang.webtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * class
 *
 * @author panyingdao
 * @date 2020/3/15.
 */
public class WebViewActivity extends AppCompatActivity {
    private TextView webviewTitle;
    private TinyWebView progressWebview;
    private String title;
    private String webViewUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        getData();
//        initViews();
        loadData();
    }

    /**
     * 方法描述：接收数据
     */
    private void getData() {
        webViewUrl = getIntent().getStringExtra("webview_url");
        title = getIntent().getStringExtra("webview_title");
    }

    /**
     * 方法描述：初始化WebView
     */
    private void initViews() {
//        progressWebview = (TinyWebView) findViewById(R.id.web_details);
//        webviewTitle = (TextView) findViewById(R.id.textView);
        //web资源
        progressWebview.loadUrl(webViewUrl);
    }

    /**
     * 方法描述：加载数据
     */
    private void loadData() {
        if (!TextUtils.isEmpty(title))
            webviewTitle.setText(title);

        if (TextUtils.isEmpty(webViewUrl))
            progressWebview.loadUrl(webViewUrl);
    }

    /**
     * 方法描述：改写物理按键——返回的逻辑
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (progressWebview.canGoBack()) {
                progressWebview.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 方法描述：
     *
     * @param activity     发起跳转的Activity
     * @param webviewUrl   WebView的url
     * @param webviewTitle WebView页面的标题
     */
    public static void skip(Activity activity, String webviewUrl, String webviewTitle) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("webview_url", webviewUrl);
        intent.putExtra("webview_title", webviewTitle);
        activity.startActivity(intent);
    }
}
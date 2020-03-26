package com.zhang.webtest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.socks.library.KLog;
import com.zhang.webtest.utils.WebTools;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    //    private WebView mWebView;
    private CustomWebView mWebView;
    //判断储存权限是否获取
    private int writeflag = 0;
    private String webUrl = "https://noone.work:442";
    private String webTeacher = "https://noone.work:442/teacher.html";
    private String webstu = "https://noone.work:442/stu.html";
    private String baidu = "http://www.baidu.com";
    private String webTeacher2 = "https://noone.work:442/teacher.html?roomId=123";
    private String webTeacher3 = "https://noone.work:442/teacher.html";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "Need permissions for camera & microphone", 0, perms);
        }else {
            init5();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init5();
                    KLog.e("开始观看");
                } else {
                    Toast.makeText(this, "没有相机权限！\n请接收权限申请或前往设置添加权限！", Toast.LENGTH_SHORT).show();
                }
                break;

            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    writeflag = 1;
                } else {
                    Toast.makeText(this, "没有存储权限！\n请接收权限申请或前往设置添加权限！", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
        }
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init5() {
        mWebView = findViewById(R.id.web);
        WebSettings settings = mWebView.getSettings();
        // 保存表单数据
        settings.setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        // 启动应用缓存
        settings.setAppCacheEnabled(true);
        // 设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // setDefaultZoom  api19被弃用
        // 网页内容的宽度自适应屏幕
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        // 网页缩放至100，一般的网页达到屏幕宽度效果，个别除外
//        webView.setInitialScale(100);
        // 关掉下滑弧形阴影
//        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        // 告诉WebView启用JavaScript执行。默认的是false。
        settings.setJavaScriptEnabled(true);
        //  页面加载好以后，再放开图片
        settings.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        settings.setDomStorageEnabled(true);
        // 排版适应屏幕
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        // WebView是否新窗口打开(加了后可能打不开网页)
//         settings.setSupportMultipleWindows(true);

        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        /** 设置字体默认缩放大小(改变网页字体大小,setTextSize  api14被弃用)*/
        settings.setTextZoom(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mWebView.setScrollBarSize(WebTools.dp2px(this, 3));
        }

//        mWebView.evaluateJavascript("getUserId()", new ValueCallback() {
//            @Override
//            public void onReceiveValue(Object o) {
//                String value = o.toString();
//                SharedPreferences preferences = getSharedPreferences("SYYJID", 0);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("USERID", value);
//                editor.commit();
//
//                KLog.e("getUserId value=" + value);
//            }
//        });
//        mWebView.loadUrl(webUrl);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                KLog.e("加载失败原因：" + error.toString());
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                KLog.e("加载完成：" + url);
                super.onPageFinished(view, url);
            }
        });

        //判断页面加载的过程
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    KLog.e("加载完成...", "success");
                } else {
                    // 加载中
                    KLog.e("加载中...", +newProgress + "");
                }
            }

            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                KLog.e("权限" + request.toString());
                MainActivity.this.runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        KLog.e("得到权限");
                        request.grant(request.getResources());
//                        if (request.getOrigin().toString().equals(webTeacher)) {
//                            request.grant(request.getResources());
//                        } else {
//                            request.deny();
//                        }

                    }// run
                });// MainActivity
            }// onPermissionRequest

        });
        mWebView.loadUrl(webUrl);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup)
                    mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

}
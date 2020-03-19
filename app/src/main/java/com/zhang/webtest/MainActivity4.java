//package com.zhang.webtest;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.content.pm.ActivityInfo;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.PixelFormat;
//import android.net.http.SslError;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.PermissionRequest;
//import android.webkit.SslErrorHandler;
//import android.webkit.WebSettings;
//import android.widget.FrameLayout;
//import android.widget.Toast;
//
//import com.socks.library.KLog;
//import com.tencent.smtt.sdk.WebView;
//
//import java.util.Objects;
//
//import androidx.appcompat.app.AppCompatActivity;
//import pub.devrel.easypermissions.EasyPermissions;
//
//public class MainActivity4 extends AppCompatActivity {
//
//    private WebView mWebView;
//    //判断储存权限是否获取
//    private int writeflag = 0;
//    private String webUrl = "https://noone.work:442";
//    private String webTeacher = "https://noone.work:442/teacher.html";
//    private String webstu = "https://noone.work:442/stu.html";
//    private String baidu = "http://www.baidu.com";
//    // 显示000000表示加载的是系统内核，显示大于零的数字表示加载了x5内核（该数字是x5内核版本号）
//    private String x5Url="http://soft.imtt.qq.com/browser/tes/feedback.html";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //去除标题栏
//        Objects.requireNonNull(getSupportActionBar()).hide();
//        setContentView(R.layout.activity_main4);
//
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
//
//        init5();
//
////        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
////        } else {
////            writeflag = 1;
////        }
////
////        if (writeflag == 0) {
////            Toast.makeText(this, "没有存储权限！\n请接收权限申请或前往设置添加权限！", Toast.LENGTH_SHORT).show();
////            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
////        } else {
////            //进行相机权限的检测，如果没有授权，申请权限。
////            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
////                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
////            } else {
//////                startCamera();
////
////                KLog.e("获取到相机权限");
////            }
////        }
//
//
//        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
//
//        if (!EasyPermissions.hasPermissions(this, perms)) {
//            EasyPermissions.requestPermissions(this, "Need permissions for camera & microphone", 0, perms);
//        }
//    }
//
////    @Override
////    public void onRequestPermissionsResult(int requestCode,
////                                           String[] permissions,
////                                           int[] grantResults) {
////
////        super.onRequestPermissionsResult(requestCode,
////                permissions,
////                grantResults);
////
////        EasyPermissions.onRequestPermissionsResult(requestCode,
////                permissions,
////                grantResults,
////                this);
////    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                    startCamera();
//                    KLog.e("开始观看");
//                } else {
//                    Toast.makeText(this, "没有相机权限！\n请接收权限申请或前往设置添加权限！", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            case 2:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    writeflag = 1;
//                } else {
//                    Toast.makeText(this, "没有存储权限！\n请接收权限申请或前往设置添加权限！", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            default:
//        }
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private void init() {
//        mWebView = (WebView) findViewById(R.id.web);
//        //WebView加载web资源
////        mWebView.loadUrl("http://baidu.com");
//
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
////        mWebView.setWebViewClient(new WebViewClient() {
////            @Override
////            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                // TODO Auto-generated method stub
////                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
////                view.loadUrl(url);
////                KLog.e("url 路径：" + url);
////                return true;
////            }
////        });
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onPermissionRequest(PermissionRequest request) {
//                super.onPermissionRequest(request);
//                KLog.e("请求结果：" + request);
//            }
//
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                // TODO Auto-generated method stub
//                if (newProgress == 100) {
//                    // 网页加载完成
//                    KLog.e("网页加载完成");
//                } else {
//                    // 加载中
//                    KLog.e("网页加载中。。。");
//                }
//            }
//
//        });
//
//        mWebView.loadUrl("https://noone.work:442/");
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private void init2() {
//        mWebView = findViewById(R.id.web);
//        WebSettings settings = mWebView.getSettings();
//
//        //webView  加载视频，下面五行必须
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
////        }
//        settings.setJavaScriptEnabled(true);//支持js
//        settings.setPluginState(WebSettings.PluginState.ON);// 支持插件
//        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片
//
//        //将图片调整到适合webview的大小  无效
//        settings.setUseWideViewPort(true);
//        // 缩放至屏幕的大小
//        settings.setLoadWithOverviewMode(true);
//
////        mWebView.loadData(Html.fromHtml(webTeacher).toString(), "text/html", "UTF-8");
//
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                // TODO Auto-generated method stub
//                if (newProgress == 100) {
//                    // 网页加载完成
//                    KLog.e("网页加载完成");
//                } else {
//                    // 加载中
//                    KLog.e("网页加载中。。。");
//
//                }
//
//            }
//        });
//        mWebView.loadUrl(webUrl);// 加载链接
//
//    }
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private void init5() {
//        mWebView = findViewById(R.id.forum_context4);
//        KLog.e("获取x5情况（null为已加载x5）：" + mWebView.getX5WebViewExtension());
//        WebSettings settings = mWebView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        mWebView.loadUrl(webUrl);
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                KLog.e("加载失败原因：" + error.toString());
//                handler.proceed();
//            }
//
//            public void onPageFinished(WebView view, String url) {
//                KLog.e("加载完成：" + url);
//                super.onPageFinished(view, url);
//            }
//        });
//
//        //判断页面加载的过程
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                // TODO Auto-generated method stub
//                if (newProgress == 100) {
//                    // 网页加载完成
//                    KLog.e("加载完成...", "success");
//                } else {
//                    // 加载中
//                    KLog.e("加载中...", +newProgress + "");
//                }
//            }
//
//            @Override
//            public void onPermissionRequest(final PermissionRequest request) {
//                KLog.e("权限" + request.toString());
//                MainActivity4.this.runOnUiThread(new Runnable() {
//                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//                    @Override
//                    public void run() {
//                        KLog.e("得到权限");
//                        request.grant(request.getResources());
//                    }// run
//                });// MainActivity
//            }// onPermissionRequest
//
//        });
//
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mWebView.canGoBack()) {
//                mWebView.goBack();//返回上一页面
//                return true;
//            } else {
//                System.exit(0);//退出程序
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//
//    @SuppressLint("SetJavaScriptEnabled")
//    private void init4() {
//        mWebView = findViewById(R.id.web);
//        WebSettings webSettings = mWebView.getSettings();
//        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
//        webSettings.setJavaScriptEnabled(true);
//
//        //支持插件
////        webSettings.setPluginsEnabled(true);
//
//        //设置自适应屏幕，两者合用
//        //将图片调整到适合webview的大小
//        webSettings.setUseWideViewPort(true);
//        // 缩放至屏幕的大小
//        webSettings.setLoadWithOverviewMode(true);
//
//        //缩放操作
//        //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setSupportZoom(true);
//        //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setBuiltInZoomControls(true);
//        //隐藏原生的缩放控件
//        webSettings.setDisplayZoomControls(false);
//
//        //其他细节操作
//        //关闭webview中缓存
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        //设置可以访问文件
//        webSettings.setAllowFileAccess(true);
//        //支持通过JS打开新窗口
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        //支持自动加载图片
//        webSettings.setLoadsImagesAutomatically(true);
//        //设置编码格式
//        webSettings.setDefaultTextEncodingName("utf-8");
//
//    }
//
//    WebView mWebDetails;
//    FrameLayout videoview;  // 全屏时视频加载view
//    private View xCustomView;
//    private xWebChromeClient xwebchromeclient;
//    private WebChromeClient.CustomViewCallback xCustomViewCallback;
//
//    private void init3() {
//        xwebchromeclient = new xWebChromeClient();
//        mWebDetails.setWebChromeClient(xwebchromeclient);
////        mWebView = findViewById(R.id.web_details);
////        videoview = findViewById(R.id.video_view);
//
//        mWebView.setWebChromeClient(new xWebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                // TODO Auto-generated method stub
//                if (newProgress == 100) {
//                    // 网页加载完成
//                    KLog.e("网页加载完成");
//                } else {
//                    // 加载中
//                    KLog.e("网页加载中。。。");
//
//                }
//
//            }
//        });
//        mWebView.loadUrl(webUrl);// 加载链接
//    }
//
//
//    /**
//     * 处理Javascript的对话框、网站图标、网站标题以及网页加载进度等
//     *
//     * @author
//     */
//    public class xWebChromeClient extends WebChromeClient {
//
//        private Bitmap xdefaltvideo;
//        private View xprogressvideo;
//
//        @SuppressLint("SourceLockedOrientationActivity")
//        @Override
//        // 播放网络视频时全屏会被调用的方法
//        public void onShowCustomView(View view, CustomViewCallback callback) {
//            //Only fullscreen activities can request orientation
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            mWebDetails.setVisibility(View.GONE);
//            // 如果一个视图已经存在，那么立刻终止并新建一个
//            if (xCustomView != null) {
//                callback.onCustomViewHidden();
//                return;
//            }
//            videoview.addView(view);
//            xCustomView = view;
//            xCustomViewCallback = callback;
//            videoview.setVisibility(View.VISIBLE);
//        }
//
//        @SuppressLint("SourceLockedOrientationActivity")
//        @Override
//        // 视频播放退出全屏会被调用的
//        public void onHideCustomView() {
//            // 不是全屏播放状态
//            if (xCustomView == null) {
//                return;
//            }
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            xCustomView.setVisibility(View.GONE);
//            videoview.removeView(xCustomView);
//            xCustomView = null;
//            videoview.setVisibility(View.GONE);
//            xCustomViewCallback.onCustomViewHidden();
//            mWebDetails.setVisibility(View.VISIBLE);
//        }
//
//        // 视频加载添加默认图标
//        @Override
//        public Bitmap getDefaultVideoPoster() {
//            if (xdefaltvideo == null) {
//                xdefaltvideo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//            }
//            return xdefaltvideo;
//        }
//
//        // 视频加载时进程loading
//        @Override
//        public View getVideoLoadingProgressView() {
//            if (xprogressvideo == null) {
////                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
////                xprogressvideo = inflater.inflate(R.layout.video_loading_progress, null);
//            }
//            return xprogressvideo;
//        }
//
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        if (mWebView != null) {
//            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//            mWebView.clearHistory();
//            ((ViewGroup)
//                    mWebView.getParent()).removeView(mWebView);
//            mWebView.destroy();
//            mWebView = null;
//        }
//        super.onDestroy();
//    }
//
//}
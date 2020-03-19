//package com.zhang.webtest;
//
//
//import android.os.Bundle;
//
//import java.util.Objects;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//public class MainActivity2 extends AppCompatActivity {
//
//    private String webUrl = "https://noone.work:442";
//    private String webTeacher = "https://noone.work:442/teacher.html";
//    private String webstu = "https://noone.work:442/stu.html";
//    private String baidu = "http://www.baidu.com";
//    FragmentManager fragmentManager;
//    FragmentTransaction transaction;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //去除标题栏
//        Objects.requireNonNull(getSupportActionBar()).hide();
//        setContentView(R.layout.activity_main2);
//
//        replaceFragment(new AgentWebFragment());
//
//
////        WebViewActivity.skip(MainActivity2.this, webUrl, "百度首页");
//    }
//
//    //Fragment启动方法：
//
//    private void replaceFragment(Fragment fragment) {
//        // 1.获取FragmentManager，在活动中可以直接通过调用getFragmentManager()方法得到
//        fragmentManager = getSupportFragmentManager();
//        // 2.开启一个事务，通过调用beginTransaction()方法开启
//        transaction = fragmentManager.beginTransaction();
//        // 3.向容器内添加或替换碎片，一般使用replace()方法实现，需要传入容器的id和待添加的碎片实例
//        transaction.replace(R.id.video_view, fragment);  //fr_container不能为fragment布局，可使用线性布局相对布局等。
//        // 4.使用addToBackStack()方法，将事务添加到返回栈中，填入的是用于描述返回栈的一个名字
//        transaction.addToBackStack(null);
//        // 5.提交事物,调用commit()方法来完成
//        transaction.commit();
//    }
//
////    /**
////     * 方法描述：初始化WebView
////     */
////    private void initViews() {
////        mWebView=findViewById(R.id.web_details);
////        webviewTitle = (TextView) findViewById(R.id.textView);
////        //web资源
////        mWebView.loadUrl(webViewUrl);
////    }
////
////    @Override
////    public boolean onKeyDown(int keyCode, KeyEvent event) {
////        // TODO Auto-generated method stub
////        if (keyCode == KeyEvent.KEYCODE_BACK) {
////            if (mWebView.canGoBack()) {
////                mWebView.goBack();//返回上一页面
////                return true;
////            } else {
////                System.exit(0);//退出程序
////            }
////        }
////        return super.onKeyDown(keyCode, event);
////    }
////
////    @Override
////    protected void onDestroy() {
////        if (mWebView != null) {
////            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
////            mWebView.clearHistory();
////            ((ViewGroup)
////                    mWebView.getParent()).removeView(mWebView);
////            mWebView.destroy();
////            mWebView = null;
////        }
////        super.onDestroy();
////    }
//
//}
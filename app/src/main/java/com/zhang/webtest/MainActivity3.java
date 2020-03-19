package com.zhang.webtest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.socks.library.KLog;

import org.xwalk.core.XWalkView;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity3 extends AppCompatActivity {

    private XWalkView mWebView;
    //    private XWalkView mXWalkView;
    //判断储存权限是否获取
    private int writeflag = 0;
    private String webUrl = "https://noone.work:442";
    private String webTeacher = "https://noone.work:442/teacher.html";
    private String webstu = "https://noone.work:442/stu.html";
    private String baidu = "http://www.baidu.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main2);

        init5();

        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "Need permissions for camera & microphone", 0, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    startCamera();
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
        mWebView = findViewById(R.id.XWalkView);
//        WebSettings settings = mWebView.getSettings();
//        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(webUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (mWebView.canGoBack()) {
//                mWebView.goBack();//返回上一页面
//                return true;
//            } else {
//                System.exit(0);//退出程序
//            }
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
//        if (mWebView != null) {
//            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//            mWebView.clearHistory();
//            ((ViewGroup)
//                    mWebView.getParent()).removeView(mWebView);
//            mWebView.destroy();
//            mWebView = null;
//        }
        super.onDestroy();
    }

}
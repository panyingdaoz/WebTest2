package com.zhang.webtest;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.webkit.PermissionRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.socks.library.KLog;

/**
 * class
 *
 * @author panyingdao
 * @date 2020/3/15.
 */
public class TinyWebView extends WebView {
    private ProgressBar progressbar;

    public TinyWebView(Context context) {
        super(context);
    }

    public TinyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TinyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initProgressBar(context);
        openJavaScript();
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());
    }

    private void initProgressBar(Context context) {
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, dp2px(context, 3), 0, 0));
        //改变progressbar默认进度条的颜色（深红色）为Color.GREEN
        progressbar.setProgressDrawable(new ClipDrawable(new ColorDrawable(Color.GREEN), Gravity.LEFT, ClipDrawable.HORIZONTAL));
        addView(progressbar);
    }

    /**
     * 方法描述：启用支持javascript
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void openJavaScript() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**
     * 方法描述：根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 类描述：显示WebView加载的进度情况
     */
    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);

                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
            KLog.e("结果：" + request.toString());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //直接同意即可     deny是拒绝
                request.grant(request.getResources());
            }
        }
    }
}

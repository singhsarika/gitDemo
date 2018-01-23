package codepath.demos.helloworlddemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HelloWorldActivity extends Activity {

    private WebView webView;
    final Activity activity = this;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello_world);

		webView = (WebView) findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        /*settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);*/

        webView.setWebChromeClient(new WebClient());
        webView.setWebViewClient(new CustomWebViewClient());
//        webView.loadUrl("http://www.lynda.com/Photoshop-tutorials/279-0.html");
        webView.loadUrl("file:///android_asset/index.html");
//        webView.loadUrl("https://www.scrumalliance.org/");
//        webView.loadUrl("http://youtube.com");
//        webView.loadUrl("http://talent.capgemini.com/in/");
//        webView.loadUrl("https://myapps.te.com");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hello_world, menu);
		return true;
	}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (request.getUrl().toString().contains("te360-my.sharepoint.com")){
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString()));
            startActivity(i);
        }else{
            webView.loadUrl(request.getUrl().toString());
        }
            return true;
    }
    }

    private class WebClient extends WebChromeClient {

       /* @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
            Log.d("test close method", "test close");
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }*/

        public void onProgressChanged(WebView view, int progress)
        {
            activity.setTitle("Loading...");
            activity.setProgress(progress * 100);

            if(progress == 100)
                activity.setTitle(R.string.app_name);
        }

    }
}

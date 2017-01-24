package ua.kharkov.bud.apps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by alexsoft on 22.08.16.
 */
public class SimpleWebViewClient extends WebViewClient {

    Activity activity;
    String domen;

    public SimpleWebViewClient(Activity activity, String domen) {
        this.activity = activity;
        this.domen = domen;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {


        if (!url.contains("mailto:") && url.contains(domen)) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }
}

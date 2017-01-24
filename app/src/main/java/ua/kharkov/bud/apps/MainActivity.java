package ua.kharkov.bud.apps;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Потоки
    Context context;

    // Обьекты классов
    WebView webView;
    SimpleWebViewClient simpleWebViewClient;

    // Доп переменные
    private static long back_pressed;
    String domen;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получаем контекст
        context = getApplicationContext();

        // Формируем ссылку на сайт
        domen = getResources().getString(R.string.domen);
        url = "http://"+domen;

        // Объекты UI
        webView = (WebView) findViewById(R.id.webView);

        // Настройки работы внутреннего браузера
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Проверка домена с ссылкой клика
        simpleWebViewClient = new SimpleWebViewClient(this, domen);
        webView.setWebViewClient(simpleWebViewClient);

        // Загрузчик страницы
        webView.loadUrl(url);

    }

    @Override
    public void onBackPressed() {
        // Обработка нажатия кнопки назад
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            if (back_pressed + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), getResources().getString(R.string.textExit), Toast.LENGTH_SHORT).show();
                back_pressed = System.currentTimeMillis();
            }
        }

    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}

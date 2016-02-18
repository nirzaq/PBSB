package id.arief.pbsb;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import id.arief.pbsb.model.News;

public class DetailActivity extends AppCompatActivity {
    private ImageView mPhoto;
    private TextView mTitle, mTimestamp;
    private WebView mWebView;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        configViews();
        ProcessContent processContent = new ProcessContent();
        processContent.execute();

        intent = getIntent();
        News news = (News) intent.getSerializableExtra("DetailNewsData");

        Picasso.with(getApplicationContext()).load(news.getImageFull()).into(mPhoto);

        mTitle.setText(news.getTitle());
        mTimestamp.setText(news.getDateId());
    }

    private void configViews() {
        mPhoto = (ImageView) findViewById(R.id.news_image);
        mTitle = (TextView) findViewById(R.id.title_detail);
        mTimestamp = (TextView) findViewById(R.id.timestamp_detail);
        mWebView = (WebView) findViewById(R.id.webView_act_viewer);
    }


        public class ProcessContent extends AsyncTask<Void, Void, Void> {
        Elements elements;
        String html;
        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("DetailNewsData");

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect(news.getLink()).get();
                elements = doc.select("div.entry-content");
                html = elements.toString();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Detail", "Error di do in back");
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            mWebView.loadDataWithBaseURL("http://stmikbjb-nirzaq.rhcloud.com",html,"text/html","UTF-8","");
        }
    }
}

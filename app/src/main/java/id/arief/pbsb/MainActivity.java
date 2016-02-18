package id.arief.pbsb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import id.arief.pbsb.api.ApiInterface;
import id.arief.pbsb.model.News;
import id.arief.pbsb.recycleview.DividerItemDecoration;
import id.arief.pbsb.recycleview.NewsAdapter;
import id.arief.pbsb.util.Constants;
import id.arief.pbsb.util.NetworkUtil;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsClickListener {

    private static final String TAG = "MainActivity" ;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private static CircularProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        configViews();
        loadNewsFeed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
    }

    private void configViews() {
        mProgressBar = (CircularProgressBar) findViewById(R.id.probar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mNewsAdapter = new NewsAdapter(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mNewsAdapter);
    }

    public void loadNewsFeed() {
        if (getNetworkAvailablility() == false) {
            //If not connected show no connection dialog
        } else {
            //Show Progress bar
            showProgress();
            getRecent();
        }

    }

    private void getRecent() {

        ApiInterface.Factory.getInstance().getLatestPost().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Response<List<News>> response) {
                //If Response Success
                if (response.isSuccess()) {
                    //Reset Adapter to clear old item
                    mNewsAdapter.reset();
                    List<News> newsList = response.body();
                    //Add All Post
                    mNewsAdapter.addAll(newsList);

                } else {
                    //If Response Failed
                    Log.v(TAG, "Response Gagal");
                }
                hideProgress();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.v(TAG, "Response Gagal", t);
            }
        });
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);

    }
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    public boolean getNetworkAvailablility() {
        return NetworkUtil.isNetworkAvailable(this);
    }

    @Override
    public void onClick(int position) {
        News selectedNews = mNewsAdapter.getSelectedNews(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("DetailNewsData", selectedNews);
        startActivity(intent);
    }
}

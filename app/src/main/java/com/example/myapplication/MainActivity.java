package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.CurrencyAdapter;
import com.example.myapplication.Adapter.SlidingImage_Adapter;
import com.example.myapplication.Model.CurrencyModel;
import com.example.myapplication.Model.ImageModel;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
//    private SwipeRefreshLayout swipeRefreshLayout;
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    private TextView date_txt;

    private int[] myImageList = new int[]{R.drawable.image, R.drawable.images,
            R.drawable.imagesa, R.drawable.imagesb
            , R.drawable.good, R.drawable.capture};

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String currentDateandTime = sdf.format(new Date());

        init();

        date_txt.setText(Utilities.getCurrentShamsidate() + " : " + Utilities.strWeekDay);

//        swipeRefreshLayout = findViewById(R.id.swiperefresh);
//        textView = findViewById(R.id.text);
//        findViewById();
//
//        swipeRefresh();
//        createRecycler();
//
//        String content;
//        CurrencySub currencySub = new CurrencySub();

//        try {
//            content = currencySub.execute("http://data.fixer.io/api/latest?access_key=8cd0d9aefc6a480ee7f4b0e60cb9897c").get();
//
//            JSONObject jsonObject = new JSONObject(content);
//            String successFlag = jsonObject.getString("success");
//            String timestamp = jsonObject.getString("timestamp");
//            String baseCurrency = jsonObject.getString("base");
//            String date = jsonObject.getString("date");
//            String rates = jsonObject.getString("rates");
//
//            JSONObject ratesObject = new JSONObject(rates);
//
//            Log.i(TAG, "onCreateJSONOBJECT: " + ratesObject);

//            textView.setText(
//                    "successFlag: " + successFlag +
//                            "\ntimestamp: " + timestamp +
//                            "\nbaseCurrency: " + baseCurrency +
//                            "\ndate: " + date +
//                            "\nrate: " + rates +
//                            "\nrateObject: " + ratesObject);
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String dataUrl = "http://data.fixer.io/api/latest?access_key=8cd0d9aefc6a480ee7f4b0e60cb9897c";
    }

    public void currencyClick(View v){
        startActivity(new Intent(getApplicationContext(), CurrencyList.class));
    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 6; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        date_txt = findViewById(R.id.main_persiandate_txt);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }
}

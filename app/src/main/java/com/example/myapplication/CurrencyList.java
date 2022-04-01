package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.myapplication.Adapter.CurrencyAdapter;
import com.example.myapplication.Model.CurrencyModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CurrencyList extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private CurrencyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
//        textView = findViewById(R.id.text);
        findViewById();

        swipeRefresh();
        createRecycler();

        String content;
        CurrencySub currencySub = new CurrencySub();

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

//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        String dataUrl = "http://data.fixer.io/api/latest?access_key=8cd0d9aefc6a480ee7f4b0e60cb9897c";
    }

    class CurrencySub extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... currency) {
            try {
                java.net.URL url = new URL(currency[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int data = isr.read();
                String content = "";
                char ch;
                while (data != -1) {
                    ch = (char) data;
                    content = content + ch;
                    data = isr.read();
                }
                return content;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private void swipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_LONG).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000);
            }
        });
    }

    private void findViewById() {
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
//        textView = findViewById(R.id.text);
        mRecyclerView = findViewById(R.id.main_recyclerview);
    }

    private void createRecycler() {
        final ArrayList<CurrencyModel> arrayList = new ArrayList<>();
        arrayList.add(new CurrencyModel(
                R.drawable.afghanistan, "دالر آمریکایی", "افغانی",
                "تغیرات اخیر: بدون تغیر", 99));
        arrayList.add(new CurrencyModel(
                R.drawable.afghanistan, "دالر استرالیایی", "افغانی",
                "تغیرات اخیر: بدون تغیر", 99));
        arrayList.add(new CurrencyModel(
                R.drawable.afghanistan, "دالر انگلیسی", "افغانی",
                "تغیرات اخیر: بدون تغیر", 99));
        arrayList.add(new CurrencyModel(
                R.drawable.afghanistan, "تومان", "افغانی",
                "تغیرات اخیر: بدون تغیر", 99));

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CurrencyAdapter(arrayList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CurrencyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.currency_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}

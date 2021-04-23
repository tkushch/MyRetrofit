package com.example.myretrofit;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.w3c.dom.Text;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        1
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://finnhub.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        2
        FinhubService service = retrofit.create(FinhubService.class);
//        3
        Call<List<Symbol>> symbolsListCall = service.listSymbols(getString(R.string.token), "US");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(new ArrayList<Symbol>());
        recyclerView.setAdapter(myAdapter);


//        4   (используем полученный с помощью Retrofit-а "Call", но в другом потоке)
        symbolsListCall.enqueue(new Callback<List<Symbol>>() {
            @Override
            public void onResponse(Call<List<Symbol>> call, Response<List<Symbol>> response) {
                    List<Symbol> symbols = response.body();
                    myAdapter.updateStocks(symbols);

            }

            @Override
            public void onFailure(Call<List<Symbol>> call, Throwable t) {
                ((TextView)findViewById(R.id.textView4)).setText("ERROR");
            }
        });
    }
}

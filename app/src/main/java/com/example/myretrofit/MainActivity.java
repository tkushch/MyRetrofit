package com.example.myretrofit;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

//        4   (используем полученный с помощью Retrofit-а "Call", но в другом потоке)
        symbolsListCall.enqueue(new Callback<List<Symbol>>() {
            @Override
            public void onResponse(Call<List<Symbol>> call, Response<List<Symbol>> response) {
                    List<Symbol> symbols = response.body();
                    ((TextView) findViewById(R.id.textView1)).setText(symbols.toString());
            }

            @Override
            public void onFailure(Call<List<Symbol>> call, Throwable t) {
                ((TextView)findViewById(R.id.textView1)).setText(t.getLocalizedMessage());
            }
        });
    }
}

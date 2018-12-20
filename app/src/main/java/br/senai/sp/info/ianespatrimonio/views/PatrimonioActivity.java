package br.senai.sp.info.ianespatrimonio.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.config.RetrofitConfig;
import br.senai.sp.info.ianespatrimonio.model.Patrimonio;
import br.senai.sp.info.ianespatrimonio.utils.AppUtils;
import br.senai.sp.info.ianespatrimonio.views.adapter.PatrimonioAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatrimonioActivity extends AppCompatActivity {

    RecyclerView rvPatrimonio;
    private List<Patrimonio> listPatrimonio = new ArrayList<>();
    private PatrimonioAdapter adapterPatrimonio;
    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrimonio);

        rvPatrimonio = findViewById(R.id.rvPatrimonio);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPatrimonio.setLayoutManager(linearLayoutManager);
        adapterPatrimonio = new PatrimonioAdapter(listPatrimonio, getApplicationContext());
        rvPatrimonio.setAdapter(adapterPatrimonio);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.sharedPref, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        Call<List<Patrimonio>> callPatrimonios = new RetrofitConfig(token).getPatrimonioRestInterface().listarPatrimonios();
        callPatrimonios.enqueue(new Callback<List<Patrimonio>>() {
            @Override
            public void onResponse(Call<List<Patrimonio>> call, Response<List<Patrimonio>> response) {
                if(response.isSuccessful()){
                    listPatrimonio = response.body();

                    if(listPatrimonio != null){
                        Log.d("status", "lisgta nao nula");
                        adapterPatrimonio.setItensDaLista(listPatrimonio);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Patrimonio>> call, Throwable t) {
                Log.d("status", "nao pegou");
                Toast.makeText(getApplicationContext(), "Erro ao carregar itens", Toast.LENGTH_LONG).show();
            }
        });
    }
}

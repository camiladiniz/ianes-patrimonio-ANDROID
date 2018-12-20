package br.senai.sp.info.ianespatrimonio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.info.ianespatrimonio.config.RetrofitConfig;
import br.senai.sp.info.ianespatrimonio.model.Ambiente;
import br.senai.sp.info.ianespatrimonio.model.ItemPatrimonio;
import br.senai.sp.info.ianespatrimonio.model.Movimentacao;
import br.senai.sp.info.ianespatrimonio.utils.AppUtils;
import br.senai.sp.info.ianespatrimonio.views.ItensActivity;
import br.senai.sp.info.ianespatrimonio.views.LoginActivity;
import br.senai.sp.info.ianespatrimonio.views.MovimentacaoActivity;
import br.senai.sp.info.ianespatrimonio.views.PatrimonioActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String token = null;
    private Spinner spinnerAmbientes;
    private Spinner spinnerItens;
    private Button btnMovimentar;
    private Ambiente ambienteSelecionado;
    private ItemPatrimonio itemSelecionado;
    private List<Ambiente> ambientesConsultados;
    private List<ItemPatrimonio> itensConsultados;
    public DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inicio das configs menu
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //fim configs menu

        spinnerAmbientes = findViewById(R.id.spAmbientes);
        spinnerItens = findViewById(R.id.spItens);
        btnMovimentar = findViewById(R.id.btnMovimentar);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.sharedPref, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        carregarAmbientes();
        carregarItens();

        spinnerAmbientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ambienteSelecionado = (Ambiente) spinnerAmbientes.getItemAtPosition(spinnerAmbientes.getSelectedItemPosition());
                Log.d("ambienteId", String.valueOf(ambienteSelecionado.getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerItens.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelecionado = (ItemPatrimonio) spinnerItens.getItemAtPosition(spinnerItens.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnMovimentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movimentacao movimentacao = new Movimentacao(ambienteSelecionado, itemSelecionado);


                Call<Movimentacao> callMov = new RetrofitConfig(token).getMovimentacaoRestInterface().salvarMovimentacao(movimentacao, itemSelecionado.getId());
                callMov.enqueue(new Callback<Movimentacao>() {
                    @Override
                    public void onResponse(Call<Movimentacao> call, Response<Movimentacao> response) {
                        if(response.isSuccessful()){
                            if (response.code() == 404) {
                                Toast.makeText(getApplicationContext(), "Item não pode ser movimentado para o mesmo ambiente.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Movimentação realizada com sucesso!!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, ItensActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Movimentacao> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "erroooou!!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



    }

    private void carregarAmbientes(){
        Call<List<Ambiente>> chamadaAmbientes = new RetrofitConfig(token).getAmbienteRestInterface().listarAmbientes();
        chamadaAmbientes.enqueue(new Callback<List<Ambiente>>() {
            @Override
            public void onResponse(Call<List<Ambiente>> call, Response<List<Ambiente>> response) {
                if(response.isSuccessful()){

//                    if (response.code() == 404) {
//
//                    }
                    ambientesConsultados = response.body();
                    Log.d("ambientes: ",ambientesConsultados.toString());
                    if(ambientesConsultados != null){
                        //Adapter spinner
                        ArrayAdapter<Ambiente> ambienteAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, ambientesConsultados);
                        Log.d("adapter", ambienteAdapter.toString());
                        spinnerAmbientes.setAdapter(ambienteAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Ambiente>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.d("ambientes", "Erro ao carregar os ambientes");
            }
        });
    }


    private void carregarItens(){
        Call<List<ItemPatrimonio>> chamadaAmbientes = new RetrofitConfig(token).getItensPatrimonioRestInterface().listarItens();
        chamadaAmbientes.enqueue(new Callback<List<ItemPatrimonio>>() {
            @Override
            public void onResponse(Call<List<ItemPatrimonio>> call, Response<List<ItemPatrimonio>> response) {
                if(response.isSuccessful()){
                    itensConsultados = response.body();
                    Log.d("it: ",itensConsultados.toString());
                    if(itensConsultados != null){
                        //Adapter spinner
                        ArrayAdapter<ItemPatrimonio> itemAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, itensConsultados);
                        Log.d("adapter", itemAdapter.toString());
                        spinnerItens.setAdapter(itemAdapter);
                    }
                }
            }

            @Override///caso a requisicao falhe
            public void onFailure(Call<List<ItemPatrimonio>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("item", t.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ver_itens) {
            Intent intent = new Intent(this.getApplicationContext(), ItensActivity.class);
            startActivity(intent);
        }else if(id == R.id.ver_patrimonios){
            Intent intent = new Intent(this.getApplicationContext(), PatrimonioActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            //excluindo o token do sharedPreferences
            final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.sharedPref, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token", null);
            editor.apply();
            token = null;
            finish();
            Intent intent = new Intent(this.getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

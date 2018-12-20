package br.senai.sp.info.ianespatrimonio.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.senai.sp.info.ianespatrimonio.MainActivity;
import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.config.RetrofitConfig;
import br.senai.sp.info.ianespatrimonio.model.Usuario;
import br.senai.sp.info.ianespatrimonio.utils.AppUtils;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilEmailLogin;
    private TextInputLayout tilSenhaLogin;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.sharedPref, Context.MODE_PRIVATE);

        tilEmailLogin = findViewById(R.id.email);
        tilSenhaLogin = findViewById(R.id.senha);
        btnLogin = findViewById(R.id.btnLogar);

        tilEmailLogin.getEditText().setText("admin@email.com");
        tilSenhaLogin.getEditText().setText("admin");

        //Ao clicar no botão, chamar o endpoint e receber uma resposta
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuario = new Usuario(tilEmailLogin.getEditText().getText().toString(), tilSenhaLogin.getEditText().getText().toString());

                retrofit2.Call<ResponseBody> chamadaLogin = new RetrofitConfig().getRestInterface().realizarLogin(usuario);
                chamadaLogin.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()) {


                            try {
                                JSONObject objeto = new JSONObject(response.body().string());
                                String token = objeto.getString("token");

                                //Salvando o token no Shared Preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", "Bearer "+token);
                                editor.apply();

                                //Indo para a próxima tela
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                               e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Erro ao fazer login", Toast.LENGTH_SHORT).show();
                        Log.d("Login Error: ", t.getMessage());
                    }
                });
            }
        });
    }
}

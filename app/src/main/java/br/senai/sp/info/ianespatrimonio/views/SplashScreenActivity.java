package br.senai.sp.info.ianespatrimonio.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import br.senai.sp.info.ianespatrimonio.MainActivity;
import br.senai.sp.info.ianespatrimonio.R;
import br.senai.sp.info.ianespatrimonio.utils.AppUtils;

public class SplashScreenActivity extends AppCompatActivity {

    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final SharedPreferences sharedPreferences = getSharedPreferences(AppUtils.sharedPref, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        //Removendo notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("token", token);
                if(token != null){
                    irActivityMovimentacao();
                }else{
                    mostrarLogin();
                }
            }
        }, 2000);


    }

    private void mostrarLogin(){
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void irActivityMovimentacao(){
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

package br.com.jotaceassis.demointent;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.jotaceassis.demointent.utils.Constants;

public class ValidaLoginActivity extends AppCompatActivity {

    private final String LOGIN = "android";
    private final String SENHA = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valida_login);

        if (getIntent() != null) {
            validaLogin(
                    getIntent().getStringExtra(Constants.KEY_LOGIN),
                    getIntent().getStringExtra(Constants.KEY_SENHA));
        }
    }

    private void validaLogin (final String login, final String senha) {
        Handler valida = new Handler();
        valida.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent resultado = new Intent();
                if (login.equals(LOGIN) && senha.equals(SENHA)) {
                    resultado.putExtra(Constants.KEY_RESULT_LOGIN, true);
                }
                else {
                    resultado.putExtra(Constants.KEY_RESULT_LOGIN, false);
                }
                setResult(RESULT_OK, resultado);
                finish();
            }

        }, 2000);
    }
}

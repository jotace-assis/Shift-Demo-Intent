package br.com.jotaceassis.demointent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.jotaceassis.demointent.utils.Constants;

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
    }

    public void conectar (View v) {
        Intent validaLogin = new Intent(this, ValidaLoginActivity.class);

        validaLogin.putExtra(Constants.KEY_LOGIN, etLogin.getText().toString());
        validaLogin.putExtra(Constants.KEY_SENHA, etSenha.getText().toString());

        startActivityForResult(validaLogin, Constants.REQUEST_CODE_VALIDA_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQUEST_CODE_VALIDA_LOGIN:
                switch (resultCode) {
                    case RESULT_OK:
                        boolean isLoginValido = data.getBooleanExtra(Constants.KEY_RESULT_LOGIN, false);
                        if (isLoginValido) Toast.makeText(this, "Login Valido", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(this, "Login Invalido", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
            default:
        }

    }
}

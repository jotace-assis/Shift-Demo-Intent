package br.com.jotaceassis.demointent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.jotaceassis.demointent.broadcastreceiver.AlarmeReceiver;
import br.com.jotaceassis.demointent.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private TextView tvLogin;
    private TextView tvPlacarHome;
    private TextView tvPlacarVisitante;

    private int placarHome = 0;
    private int placarVisitante = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLogin = (TextView) findViewById(R.id.tvlogin);
        tvPlacarHome = (TextView) findViewById(R.id.tvPlacarHome);
        tvPlacarVisitante = (TextView) findViewById(R.id.tvPlacarVisitante);

        if (savedInstanceState != null) {
            placarHome = savedInstanceState.getInt(Constants.KEY_PLACAR_CASA);
            placarVisitante = savedInstanceState.getInt(Constants.KEY_PLACAR_VISITANTE);
        }
        setPlacar(tvPlacarHome, placarHome);
        setPlacar(tvPlacarVisitante, placarVisitante);

        if (getIntent() != null) {
            iniciarActivity();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.KEY_PLACAR_CASA, placarHome);
        outState.putInt(Constants.KEY_PLACAR_VISITANTE, placarVisitante);
    }

    private void iniciarActivity() {
        String login = getIntent().getStringExtra(Constants.KEY_LOGIN);
        tvLogin.setText(login);
    }

    public void golCasa(View v) {
        placarHome = incrementaPlacar(tvPlacarHome, placarHome);
    }

    public void golVisitante(View v) {
        placarVisitante = incrementaPlacar(tvPlacarVisitante, placarVisitante);
    }

    private int incrementaPlacar(TextView tvPlacar, int placar) {
        placar++;
        setPlacar(tvPlacar, placar);
        return placar;
    }

    private void setPlacar(TextView tvPlacar, int intPlacar) {
        tvPlacar.setText(String.valueOf(intPlacar));
    }

    public void programarAlarme(View v) {
        EditText text = (EditText) findViewById(R.id.tvTempoJogo);
        int i = Integer.parseInt(text.getText().toString());

        Intent intent = new Intent(this, AlarmeReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + (i + 1000),
                pendingIntent);

        Toast.makeText(this, "Alarm set in " + i + "segundos", Toast.LENGTH_SHORT).show();
    }
}

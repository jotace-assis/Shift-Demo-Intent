package br.com.jotaceassis.demointent.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.widget.Toast;

import br.com.jotaceassis.demointent.R;

public class AlarmeReceiver extends BroadcastReceiver {

    private MediaPlayer mp = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.despertar);
        mp.start();

        Toast.makeText(context, "Alarme...", Toast.LENGTH_LONG).show();
    }
}

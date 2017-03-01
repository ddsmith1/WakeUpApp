package wakeup.wakeupapp;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class AlarmSet extends AppCompatActivity {

    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_set);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void OnToggleClicked(View view) {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(AlarmSet.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());

            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
            
            if(System.currentTimeMillis()>time) {
                if (calendar.AM_PM == 0) {
                    time = time + (1000 * 60 * 60 * 12);
                }
                else {
                    time = time + (1000 * 60 * 60 * 24);
                }
            }
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        }
        else {
            alarmManager.cancel(pendingIntent);
            AlarmReceiver.stopMedia();
            Toast.makeText(AlarmSet.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }
}

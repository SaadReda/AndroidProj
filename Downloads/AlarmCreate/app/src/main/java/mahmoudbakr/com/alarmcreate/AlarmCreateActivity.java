package mahmoudbakr.com.alarmcreate;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmCreateActivity extends Activity {

    private AlarmManager mAlarmManager;
    private Intent mNotificationReceiverIntent, mLoggerReceiverIntent;
    private PendingIntent mNotificationReceiverPendingIntent,
            mLoggerReceiverPendingIntent;


    EditText editText ;
    TimePicker timePicker ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        editText = (EditText) findViewById(R.id.editTextSubject);
        SharedPreferences sh = getSharedPreferences("save", 0);
        SharedPreferences.Editor e = sh.edit();
        e.putString("Subject", editText.getText().toString());
        e.putBoolean("state",true);
        e.commit();

        final Calendar c = Calendar.getInstance();
        c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(), timePicker.getCurrentMinute());

        // Get the AlarmManager Service
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Create an Intent to broadcast to the AlarmNotificationReceiver
        mNotificationReceiverIntent = new Intent(AlarmCreateActivity.this,
                AlarmNotificationReceiver.class);

        // Create an PendingIntent that holds the NotificationReceiverIntent
        mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(
                AlarmCreateActivity.this, 0, mNotificationReceiverIntent, 0);

        // Create an Intent to broadcast to the AlarmLoggerReceiver
        mLoggerReceiverIntent = new Intent(AlarmCreateActivity.this,
                AlarmLoggerReceiver.class);

        // Create PendingIntent that holds the mLoggerReceiverPendingIntent
        mLoggerReceiverPendingIntent = PendingIntent.getBroadcast(
                AlarmCreateActivity.this, 0, mLoggerReceiverIntent, 0);

        // Set up single alarm Button
        final Button singleAlarmButton = (Button) findViewById(R.id.single_alarm_button);
        singleAlarmButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set single alarm
                mAlarmManager.set(AlarmManager.RTC_WAKEUP , c.getTimeInMillis() , mNotificationReceiverPendingIntent);


                // Show Toast message
                Toast.makeText(getApplicationContext(), "Single Alarm Set",
                        Toast.LENGTH_LONG).show();
            }
        });




    }
}
package mad.easychinese;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class reminder extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    TextView txtDate, txtTime;
    Switch switchSet;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

        switchSet = (Switch)findViewById(R.id.switchSet);
        btnDatePicker=(Button)findViewById(R.id.buttonDate);
        btnTimePicker=(Button)findViewById(R.id.buttonTime);
        txtDate=(TextView)findViewById(R.id.textViewDate);
        txtTime=(TextView)findViewById(R.id.textViewTime);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        switchSet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if((txtDate.getText().equals("") || txtTime.getText().equals(""))) {
                        Toast.makeText(getApplicationContext(), "Please select date and time", Toast.LENGTH_LONG).show();
                        txtDate.setText("");
                        txtTime.setText("");
                        switchSet.setChecked(false);
                    }
                    else{
                        SetupAlarm();
                    }
                }else{
                    CancelAlarm();
                    txtDate.setText("");
                    txtTime.setText("");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            SimpleDateFormat month_date = new SimpleDateFormat("MMM");
                            txtDate.setText(dayOfMonth + " " + month_date.format(monthOfYear + 1));
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            txtTime.setText(hourOfDay + ":" + minute);
                            mHour = hourOfDay;
                            mMinute = minute;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public void SetupAlarm(){
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH, mMonth);
        calendar.set(Calendar.YEAR, mYear);
        calendar.set(Calendar.DAY_OF_MONTH, mDay);

        calendar.set(Calendar.HOUR_OF_DAY, mHour);
        calendar.set(Calendar.MINUTE, mMinute);
        calendar.set(Calendar.SECOND, 0);

        Intent myIntent = new Intent(reminder.this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(reminder.this, 0, myIntent,0);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        Toast.makeText(getApplicationContext(), "Alarm created", Toast.LENGTH_LONG).show();
    }

    public void CancelAlarm() {
        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.cancel(sender);
    }
}

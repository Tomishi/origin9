package com.lifeistech.android.origin9;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
     public void button(View v) {
         //カレンダー型変数を取得
          calendar = Calendar.getInstance();
         //時間の設定と入力
         int hour = calendar.get(Calendar.HOUR_OF_DAY);
         int minute = calendar.get(Calendar.MINUTE);

         //textの定義
         final TextView text;
         text = (TextView) findViewById(R.id.textView);
         //TimePicker
         TimePickerDialog dialog = new TimePickerDialog(
                 this,
                 new TimePickerDialog.OnTimeSetListener(){
                     @Override
                     public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                         Log.d("test", String.format(" % 02d:%02d", hourOfDay, minute));
                         text.setText(String.format(" % 02d:%02d", hourOfDay, minute));
                         calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                         calendar.set(Calendar.MINUTE, minute);
                         calendar.set(Calendar.SECOND,0);
                     }
                 },
                 hour, minute, true);
         dialog.show();
     }
    //ＯＫボタンを押したときの処理
    public void ok(View v){
        //設定した日時で発行するintentを生成
        Intent intent = new Intent(MainActivity.this,Notifier.class);
        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);;
        EditText edit = (EditText)findViewById(R.id.editText);
        //日時と発行するintentをAlarmmanagerにセット
        AlarmManager alarmmanager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmmanager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),sender);
        Intent intent2 = new Intent(MainActivity.this,Notifier.class);
        intent2.putExtra("Task",edit.getText().toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

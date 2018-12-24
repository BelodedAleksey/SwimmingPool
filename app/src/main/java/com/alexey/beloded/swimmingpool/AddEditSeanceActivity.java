package com.alexey.beloded.swimmingpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddEditSeanceActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "beloded.alexey.com.swimmingpool.EXTRA_ID";
    public static final String EXTRA_DAY =
            "beloded.alexey.com.swimmingpool.EXTRA_DAY";
    public static final String EXTRA_TIME =
            "beloded.alexey.com.swimmingpool.EXTRA_TIME";
    public static final String EXTRA_SEANCE_ID =
            "beloded.alexey.com.swimmingpool.EXTRA_SEANCE_ID";
    public static final String EXTRA_ROAD1 =
            "beloded.alexey.com.swimmingpool.EXTRA_ROAD1";
    public static final String EXTRA_ROAD2=
            "beloded.alexey.com.swimmingpool.EXTRA_ROAD2";
    public static final String EXTRA_ROAD3 =
            "beloded.alexey.com.swimmingpool.EXTRA_ROAD3";
    public static final String EXTRA_ROAD4 =
            "beloded.alexey.com.swimmingpool.EXTRA_ROAD4";
    public static final String EXTRA_ROAD5 =
            "beloded.alexey.com.swimmingpool.EXTRA_ROAD5";
    public static final String EXTRA_ROAD6 =
            "beloded.alexey.com.swimmingpool.EXTRA_ROAD6";


    private EditText editTextRoad1, editTextRoad2, editTextRoad3, editTextRoad4, editTextRoad5, editTextRoad6;
    private NumberPicker numberPickerHour, numberPickerMinute;
    private TextView textViewEditSeanceId;
    private int day, seance_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seance);

        editTextRoad1 = findViewById(R.id.edit_text_road1);
        editTextRoad2 = findViewById(R.id.edit_text_road2);
        editTextRoad3 = findViewById(R.id.edit_text_road3);
        editTextRoad4 = findViewById(R.id.edit_text_road4);
        editTextRoad5 = findViewById(R.id.edit_text_road5);
        editTextRoad6 = findViewById(R.id.edit_text_road6);
        textViewEditSeanceId = findViewById(R.id.text_view_edit_seance_id);
        numberPickerHour = findViewById(R.id.number_picker_hour);
        numberPickerMinute = findViewById(R.id.number_picker_minute);

        numberPickerHour.setMinValue(0);
        numberPickerHour.setMaxValue(23);

        numberPickerMinute.setMinValue(0);
        numberPickerMinute.setMaxValue(59);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            numberPickerHour.setValue(intent.getIntExtra(EXTRA_TIME, 0)/100);
            numberPickerMinute.setValue(intent.getIntExtra(EXTRA_TIME,0) % 100);
            textViewEditSeanceId.setText(String.valueOf(intent.getIntExtra(EXTRA_SEANCE_ID,1)));
            editTextRoad1.setText(intent.getStringExtra(EXTRA_ROAD1));
            editTextRoad2.setText(intent.getStringExtra(EXTRA_ROAD2));
            editTextRoad3.setText(intent.getStringExtra(EXTRA_ROAD3));
            editTextRoad4.setText(intent.getStringExtra(EXTRA_ROAD4));
            editTextRoad5.setText(intent.getStringExtra(EXTRA_ROAD5));
            editTextRoad6.setText(intent.getStringExtra(EXTRA_ROAD6));
        } else {

            setTitle("Add Note");
        }
        seance_id = intent.getIntExtra(EXTRA_SEANCE_ID,1);
        day = intent.getIntExtra(EXTRA_DAY, 1);
    }

    private void saveSeance(){
        String road1 = editTextRoad1.getText().toString();
        String road2 = editTextRoad2.getText().toString();
        String road3 = editTextRoad3.getText().toString();
        String road4 = editTextRoad4.getText().toString();
        String road5 = editTextRoad5.getText().toString();
        String road6 = editTextRoad6.getText().toString();

       // int seance_id = Integer.parseInt(editTextSeanceId.getText().toString());
        int hour = numberPickerHour.getValue();
        int minute = numberPickerMinute.getValue();
        int time = hour * 100 + minute;

        if(false){
            Toast.makeText(this, "Please insert a time and road", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TIME, time);
        data.putExtra(EXTRA_ROAD1, road1);
        data.putExtra(EXTRA_ROAD2, road2);
        data.putExtra(EXTRA_ROAD3, road3);
        data.putExtra(EXTRA_ROAD4, road4);
        data.putExtra(EXTRA_ROAD5, road5);
        data.putExtra(EXTRA_ROAD6, road6);
        data.putExtra(EXTRA_SEANCE_ID, seance_id);
        data.putExtra(EXTRA_DAY, day);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_seance_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveSeance();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

  package com.example.osu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.hardware.Sensor.TYPE_LIGHT;

  public class MainActivity extends AppCompatActivity {

    private Button btnUsuario;
    private Button btnLoc;

    // variables Luminosity Detector
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightEventListener;
    private View root;
    private float maxValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instânciado as variaveis com os objetos do xml
        btnUsuario = findViewById(R.id.btnUsuario);
        btnLoc = findViewById(R.id.btnLoc);


        root = findViewById(R.id.mainactivity);
        // instanciando a variavel com o serviço de sensor do celular
        sensorManager =(SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(TYPE_LIGHT);

        if(lightSensor == null){
            Toast.makeText(this, "The device has not ligth sensor" , Toast.LENGTH_SHORT).show();
            finish();
        }

        maxValue = lightSensor.getMaximumRange();

        lightEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float value = sensorEvent.values[0];
                float SysBackLightValue = 255f;
                getSupportActionBar().setTitle("Luminosity :" + value + "lx");
                int newValue = (int) ((SysBackLightValue * value)/ maxValue);
                root.setBackgroundColor(Color.rgb(newValue, newValue, newValue));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OsuUsuario.class);
                startActivity(intent);
            }
        });
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Localizacao.class);
                startActivity(intent);
            }
        });

    }
    public void onResume(){
        super.onResume();
        sensorManager.registerListener(lightEventListener,lightSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(lightEventListener);
    }
}
package jp.hackathony.shaka2;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jp.hackathony.shaka2.AcceleratorClass;
import jp.hackathony.shaka2.GetAcceleratorClass;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager manager;
	GetAcceleratorClass getAcc=new GetAcceleratorClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        getAcc.Create(manager);
        Log.d("kanakanakana","waaaa");
    }

    @Override
    protected void onResume() {
    	super.onResume();
    	getAcc.Resume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

package jp.hackathony.shaka2;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jp.hackathony.shaka2.AcceleratorClass;

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
	public Timer timer = null; 
	int tickcount;
	double x,y,z;
	AcceleratorClass AccObj=new AcceleratorClass();
	public void _Timer(){
		tickcount=0;
		if(timer!=null){
			timer.cancel();
			timer=null;
		}
		timer = new Timer(true);
		timer.schedule(			
				new TimerTask() {
					@Override
					public void run() {
						AccObj.x.add(x);
						AccObj.y.add(y);
						AccObj.z.add(z);
						tickcount++;
						if(tickcount>AccObj.KANKAKUCOUNT){
							SendAccelerator();
							timer.cancel();
							
						}
						System.out.println("x="+x+",y="+y+",z="+z);
					}
				}, 10, 10

		);
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        this._Timer();
    }

    @Override
    protected void onResume() {
    	super.onResume();
    	List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    	if(sensors.size() > 0) {
    		Sensor s = sensors.get(0);
    		manager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
    	}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    
    void SendAccelerator(){
    	//ここに入れる。
    	System.out.println("kanachanha"+forKana(AccObj));
    }


	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			x=(double) event.values[0];
			y=(double) event.values[1];
			z=(double) event.values[2];
		}
	}
	public int forKana(AcceleratorClass kana){
		int i;
		double sum_x=0.0, sum_y=0.0, sum_z=0.0;
		for(i=0;i<kana.x.size()-1;i++){
			sum_x += Math.pow(kana.x.get(i+1)-kana.x.get(i),2);
		}
		for(i=0;i<kana.x.size()-1;i++){
			sum_y += Math.pow(kana.y.get(i+1)-kana.y.get(i),2);
		}
		for(i=0;i<kana.x.size()-1;i++){
			sum_z += Math.pow(kana.z.get(i+1)-kana.z.get(i),2);
		}
		Log.d("kanakanakana",sum_x + ","+ sum_y +","+ sum_z);
		if(sum_x>sum_y){
			if(sum_x>sum_z){
				return 1;
			}else{
				return 3;
			}
		}else{
			if(sum_y>sum_z){
				return  2;
			}else{
				return 3;
			}
		}
	}
}

package jp.hackathony.shaka2;

import java.util.ArrayList;

public class AcceleratorClass {
  public ArrayList<Double> x;
	public ArrayList<Double> y;
	public ArrayList<Double> z;
	final float KANKAKU=0.01f;
	final float KANKAKUCOUNT=500;
	
	
	AcceleratorClass(){
		x = new ArrayList<Double>();
		y = new ArrayList<Double>();
		z = new ArrayList<Double>();
	}
}

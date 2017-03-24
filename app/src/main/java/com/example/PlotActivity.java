package com.example;

import com.example.DrawLine;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlotActivity extends Activity {
	public Handler drawlineHandler;
	TextView X,Y,Z ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plot_activity);


		Intent intent = getIntent();
		//String jiasudu = String.format("%.2f",intent.getStringExtra(DataMonitor.EXTRA_MESSAGE));
		float[] jiasudu=intent.getFloatArrayExtra(DataMonitor.EXTRA_MESSAGE);
		/******************************************************************
		 * 注意：次Handler实体要在语句DrawLine myRender = new DrawLine(drawlineHandler);
		 * 的上面
		 * zjk2014/03/04注
		 */
		drawlineHandler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				
				if(msg.what==0x123){
					String[] xyz=new String[3];
					xyz=msg.getData().getStringArray("xyz");
					X.setText(xyz[0]);
					Y.setText(xyz[1]);
					Z.setText(xyz[2]);
				}
			}
		};
		// 创建一个GLSurfaceView，用于显示OpenGL绘制的图形
		GLSurfaceView glView = new GLSurfaceView(this);
		
		/*****************************************************************
		 * 注意:下面调用DrawLine的构造函数的时候一定要保证次语句在参数drawlineHandler的实体下面
		 * 因为不然的话在DrawLine.java中调用handler2.sendMessage(msg);虽然这时候已经把
		 * 次函数中的handler通过构造函数传递过去了，但是实际上只是传递过去 了个声明，并没有实体，所以会
		 * 在handler2.sendMessage(msg)处报错发现空指针NULLpoitexception
		 * zjk2014/03/04注
		 */
		// 创建GLSurfaceView的内容绘制器
		DrawLine myRender = new DrawLine(drawlineHandler);
		myRender.getXYZ(jiasudu);
		// 为GLSurfaceView设置绘制器
		glView.setRenderer(myRender);
		LinearLayout zjkLayout = (LinearLayout) findViewById(R.id.z);//R.id.z绘制区
		zjkLayout.addView(glView);
		//setContentView(glView);
		 X = (TextView) findViewById(R.id.X);     //显示X，Y，Z的值
		 Y = (TextView) findViewById(R.id.Y);
		 Z = (TextView) findViewById(R.id.Z);
		
		
		
	}

	

}

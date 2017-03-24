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
		 * ע�⣺��Handlerʵ��Ҫ�����DrawLine myRender = new DrawLine(drawlineHandler);
		 * ������
		 * zjk2014/03/04ע
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
		// ����һ��GLSurfaceView��������ʾOpenGL���Ƶ�ͼ��
		GLSurfaceView glView = new GLSurfaceView(this);
		
		/*****************************************************************
		 * ע��:�������DrawLine�Ĺ��캯����ʱ��һ��Ҫ��֤������ڲ���drawlineHandler��ʵ������
		 * ��Ϊ��Ȼ�Ļ���DrawLine.java�е���handler2.sendMessage(msg);��Ȼ��ʱ���Ѿ���
		 * �κ����е�handlerͨ�����캯�����ݹ�ȥ�ˣ�����ʵ����ֻ�Ǵ��ݹ�ȥ �˸���������û��ʵ�壬���Ի�
		 * ��handler2.sendMessage(msg)�������ֿ�ָ��NULLpoitexception
		 * zjk2014/03/04ע
		 */
		// ����GLSurfaceView�����ݻ�����
		DrawLine myRender = new DrawLine(drawlineHandler);
		myRender.getXYZ(jiasudu);
		// ΪGLSurfaceView���û�����
		glView.setRenderer(myRender);
		LinearLayout zjkLayout = (LinearLayout) findViewById(R.id.z);//R.id.z������
		zjkLayout.addView(glView);
		//setContentView(glView);
		 X = (TextView) findViewById(R.id.X);     //��ʾX��Y��Z��ֵ
		 Y = (TextView) findViewById(R.id.Y);
		 Z = (TextView) findViewById(R.id.Z);
		
		
		
	}

	

}

package com.example.ttt;

import java.io.IOException;
import java.util.Map;

import com.example.ttt.Base.LoginService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//�����Ƿ���ʾtitle
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//�����Ƿ�ȫ����
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);//����view��ͼ����ʾ����
		
		//���ú���/������ʾ����һ��
		//���ú���
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//��������
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		//Toast.makeText(getApplicationContext(), "!!!", Toast.LENGTH_SHORT).show();
		////////////////////////////////////////////////////////////////////////////
		final Context context=getApplicationContext();
		//1����ȡ�û�����������еĶ���
		final EditText name=(EditText)findViewById(R.id.editText1);
		final EditText pwd=(EditText)findViewById(R.id.editText2);
		final CheckBox ch=(CheckBox)findViewById(R.id.checkBox1);
		name.setText("admin");
		pwd.setText("123456");
		////////////////////////////////////////////////////////////////////////////
		//�ж��Ƿ��ס���û��������룬������
		try {
			Map<String,String> map = LoginService.getSaveUserInfo(context);
			if(map!=null)
			{		
				name.setText(map.get("username"));
				pwd.setText(map.get("userpwd"));
				ch.setChecked(true);//����ѡ�м�ס���븴ѡ��
			}else{
				ch.setChecked(false);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		////////////////////////////////////////////////////////////////////////////
		//�����¼��ť����ת
		//1.ʶ�𵽵�¼��ť
		Button btnlogin=(Button)findViewById(R.id.button1);
		btnlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//�����ťʱ�������¼�
				//2����ȡ�û�����������е�����
				String uname=name.getText().toString();
				String upwd=pwd.getText()+"";
				Toast.makeText(MainActivity.this, uname+","+upwd, 4000).show();
				if(uname.equals("admin") && upwd.equals("123456")){
					if(ch.isChecked()){
						//��ѡ�˼�ס����
						try {
							LoginService.saveInfo(context, uname, upwd);//��ס����
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						//û�й�ѡ��ס����
						try {
							LoginService.saveInfo(context, "", "");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Intent i=new Intent(MainActivity.this,MenuActivity.class);
					startActivity(i);
				}else{
					Toast.makeText(MainActivity.this, "�ϱ�������������д�", 4000).show();
				}
			}
		});
		
		////////////////////////////////////////////////////////////////////////////
		//��������
		TextView textView_ForgotPwd=(TextView)findViewById(R.id.textView_ForgotPwd);//
		textView_ForgotPwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "����ϵϵͳ����Ա��", 3000).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

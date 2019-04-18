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
		//设置是否显示title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置是否全屏。
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);//加载view视图，显示内容
		
		//设置横屏/竖屏显示方法一：
		//设置横屏
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		//设置竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		//Toast.makeText(getApplicationContext(), "!!!", Toast.LENGTH_SHORT).show();
		////////////////////////////////////////////////////////////////////////////
		final Context context=getApplicationContext();
		//1。获取用户名和密码框中的对象
		final EditText name=(EditText)findViewById(R.id.editText1);
		final EditText pwd=(EditText)findViewById(R.id.editText2);
		final CheckBox ch=(CheckBox)findViewById(R.id.checkBox1);
		name.setText("admin");
		pwd.setText("123456");
		////////////////////////////////////////////////////////////////////////////
		//判断是否记住了用户名和密码，是则处理
		try {
			Map<String,String> map = LoginService.getSaveUserInfo(context);
			if(map!=null)
			{		
				name.setText(map.get("username"));
				pwd.setText(map.get("userpwd"));
				ch.setChecked(true);//设置选中记住密码复选框
			}else{
				ch.setChecked(false);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		////////////////////////////////////////////////////////////////////////////
		//点击登录按钮，跳转
		//1.识别到登录按钮
		Button btnlogin=(Button)findViewById(R.id.button1);
		btnlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//点击按钮时触发的事件
				//2。获取用户名和密码框中的内容
				String uname=name.getText().toString();
				String upwd=pwd.getText()+"";
				Toast.makeText(MainActivity.this, uname+","+upwd, 4000).show();
				if(uname.equals("admin") && upwd.equals("123456")){
					if(ch.isChecked()){
						//勾选了记住密码
						try {
							LoginService.saveInfo(context, uname, upwd);//记住密码
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						//没有勾选记住密码
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
					Toast.makeText(MainActivity.this, "老表，你输入的内容有错！", 4000).show();
				}
			}
		});
		
		////////////////////////////////////////////////////////////////////////////
		//忘记密码
		TextView textView_ForgotPwd=(TextView)findViewById(R.id.textView_ForgotPwd);//
		textView_ForgotPwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "请联系系统管理员！", 3000).show();
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

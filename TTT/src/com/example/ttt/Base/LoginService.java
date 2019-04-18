package com.example.ttt.Base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class LoginService {

	/*
	 * 保存用户名和密码，Context：上下文；String Name：用户名；String pwd：密码
	 * */
	public static boolean saveInfo(Context context,String name,String pwd) throws IOException
	{
		boolean flag=false;
		//File file=new File("/data/data/com.example.TTT/cache/info.txt");
		//context.getFilesDir()指向地址：/data/data/com.example.TTT/files/
		File file=new File(context.getFilesDir(),"info.txt");
		try{
			FileOutputStream fos=new FileOutputStream(file);
			try {
				if(name.equals("") || pwd.equals("")){
					fos.write(("").getBytes());
				}else{
					fos.write((name+","+pwd).getBytes());
				}
				flag=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}
			fos.close();
		}
		catch(FileNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();	
			flag = false;
		}
		return flag;
	}
	
	/*
	 * 获取存储记住的用户名和密码，Context context：上下文，username，value
	 * */
	public static Map<String,String>getSaveUserInfo(Context context){
		Map<String,String> map = new HashMap<String,String>();
		File file=new File(context.getFilesDir(),"info.txt");
		FileInputStream fis=null;
		
		try {
			fis=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(fis));
			String str=br.readLine();//获取文件流中的数据
			if(str.equals("") || str.equals(null)){
				map=null;
			}else{
				String[] info=str.split(",");
				map.put("username",info[0]);
				map.put("userpwd", info[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}

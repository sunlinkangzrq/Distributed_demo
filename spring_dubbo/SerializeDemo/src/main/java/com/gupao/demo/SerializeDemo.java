package com.gupao.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeDemo {
	
	public static void main(String[] args) {
//		serializeUser();
		User user=deSerializeUser();
		System.out.println(user.toString());
	}
	public static void  serializeUser(){
//		ObjectInputStream ois=new ObjectInputStream(user)
		try {
			User user=new User("jack", 18, "washenton");
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("user.txt"));
			oos.writeObject(user);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static User  deSerializeUser(){
		try {
			ObjectInputStream ois =new ObjectInputStream(new FileInputStream("user.txt"));
			User user=(User) ois.readObject();
			ois.close();
			return user;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}

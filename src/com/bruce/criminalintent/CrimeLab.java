package com.bruce.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {
	//单例模式第一步:缓存对象变量
	private static CrimeLab sCrimeLab;
	private Context mappContext;
	private ArrayList<Crime> mCrimes;
	//单例模式第二步:私有构造器
	private CrimeLab(Context appcontext){
		mappContext = appcontext;
		mCrimes = new ArrayList<Crime>();
		for(int i = 0;i<100;i++){
			Crime c = new Crime();
			c.setTitle("Crime#"+i);
			c.setSolved(i%2==0);
			mCrimes.add(c);
		}
	} 
	//单例模式第三步:提供一个公开获取对象的方法
	public static CrimeLab get(Context context){
		
		if(sCrimeLab==null){
			sCrimeLab = new CrimeLab(context.getApplicationContext());
		}
		return sCrimeLab;
		
		
	}
	
	public ArrayList<Crime> getCrimes(){
		return mCrimes;
		
	}
	
	public Crime getCrime(UUID id){
		for(Crime c :mCrimes){
			if(c.getId().equals(id)){
				return c;
			}
		}
		return null;
		
	}
}

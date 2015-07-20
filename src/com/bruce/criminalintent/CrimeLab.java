package com.bruce.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {
	//����ģʽ��һ��:����������
	private static CrimeLab sCrimeLab;
	private Context mappContext;
	private ArrayList<Crime> mCrimes;
	//����ģʽ�ڶ���:˽�й�����
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
	//����ģʽ������:�ṩһ��������ȡ����ķ���
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

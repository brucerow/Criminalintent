package com.bruce.criminalintent;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Crime {
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mSolved;
	public Crime(){
		mId = UUID.randomUUID();
		mDate = new Date();
	}
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	public UUID getId() {
		return mId;
	}
	/*DateFormat.LONG����ʾ��ʽΪ2015.7.13
	 * DateFormat.SHORT����ʾ��ʽΪ15/7/13
	 * DateFormat.FULL����ʾ��ʽΪ2015��7��13������һ
	 */
	public Date getDate() {
		DateFormat date = DateFormat.getDateInstance(DateFormat.FULL);
		return mDate;
	}
	public void setDate(Date date) {
		mDate = date;
	}
	public boolean isSolved() {
		return mSolved;
	}
	public void setSolved(boolean solved) {
		mSolved = solved;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mTitle;
	}
	
	
	
	
	
}

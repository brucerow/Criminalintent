package com.bruce.criminalintent;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CrimeListFragment extends ListFragment {
	private ArrayList<Crime> mCrimes;
	private static final String TAG = "CrimeListFragment" ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.crime_title);
		mCrimes = CrimeLab.get(getActivity()).getCrimes();
		//采用系统自带的格式
		//ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(getActivity(),android.R.layout.simple_list_item_1,mCrimes);
		CrimeAdapter crimeAdapter = new CrimeAdapter(mCrimes);
		setListAdapter(crimeAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//Crime c = (Crime) (getListAdapter()).getItem(position);
		Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);//不明白
		Toast.makeText(getActivity(), c.getTitle()+" was onclick", Toast.LENGTH_SHORT).show();
		//start activity
		Intent i = new Intent(getActivity(),CrimeActivity.class);
		i.putExtra(CrimeFargment.EXTRA_CRIME_ID, c.getId());
		startActivity(i);
	}
	
	private class CrimeAdapter extends ArrayAdapter<Crime>{

		public CrimeAdapter(ArrayList<Crime>Crimes) {
			super(getActivity(),0,Crimes);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
			}
			Crime c = getItem(position);
			TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
			titleTextView.setText(c.getTitle());
			TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_dateTextview);
			dateTextView.setText(c.getDate());
			CheckBox solvedCheckBox =(CheckBox) convertView.findViewById(R.id.crime_list_item_solveCheckBox);
			solvedCheckBox.setChecked(c.isSolved());
			
			return convertView;
		}
		
		
	}
	
	
}

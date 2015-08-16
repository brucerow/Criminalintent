package com.bruce.criminalintent;

import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class CrimeFargment extends Fragment {
	public static final String EXTRA_CRIME_ID="com.bruce.crimeinalintent.crime_id";
	private static final String DIALOG_DATE="date";
	private static final int REQUEST_DATE =0;
	private Crime mCrime;
	private EditText mTitleFieId;
	private Button mDateButton;
	private CheckBox mSolvedCheckBox ;
	
	//创建fargment的bundle
	public static CrimeFargment newInstance(UUID crimeId){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID,crimeId);
		CrimeFargment fargment = new CrimeFargment();
		fargment.setArguments(args);
		return fargment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
		UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
		this.setHasOptionsMenu(true);
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode !=Activity.RESULT_OK)return;
		if(requestCode ==REQUEST_DATE ){
			Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mCrime.setDate(date);
			mDateButton.setText(mCrime.getDate().toString());
		}
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_crime, container, false);
		if(NavUtils.getParentActivityName(getActivity())!=null){
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);	
		}
		
		mTitleFieId = (EditText) v.findViewById(R.id.crime_title);
		mTitleFieId.setText(mCrime.getTitle());
		mTitleFieId.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				mCrime.setTitle(s.toString());
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}});
		//将DateButton设置为当前日期，并置灰
		mDateButton = (Button) v.findViewById(R.id.crime_date);
		mDateButton.setText(mCrime.getDate().toString());
		//mDateButton.setEnabled(false);
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm =getActivity().getSupportFragmentManager();
				//DatePickerFragment dialog = new DatePickerFragment();
				DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
				dialog.setTargetFragment(CrimeFargment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
		});
		//配置SolvedCheckBox按钮
		mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mCrime.setSolved(isChecked);
				
			}
			
		});
		return v;
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			if(NavUtils.getParentActivityName(getActivity())!=null){
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	

	
}

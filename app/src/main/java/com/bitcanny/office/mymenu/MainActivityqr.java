package com.bitcanny.office.mymenu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivityqr extends ActionBarActivity {

	 int onStartCount = 0;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        onStartCount = 1;
	        if (savedInstanceState == null) // 1st time
	        {
	            this.overridePendingTransition(R.anim.abc_fade_in,
	                    R.anim.abc_fade_out);
	        } else // already created so reverse animation
	        { 
	            onStartCount = 2;
	        }
	    }

	    @Override
	    protected void onStart() {
	        // TODO Auto-generated method stub
	        super.onStart();
	        if (onStartCount > 1) {
	            this.overridePendingTransition(R.anim.abc_fade_in,
	                    R.anim.abc_fade_out);

	        } else if (onStartCount == 1) {
	            onStartCount++;
	        }

	    }
}

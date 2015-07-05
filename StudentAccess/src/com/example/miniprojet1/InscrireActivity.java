package com.example.miniprojet1;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class InscrireActivity extends Activity {
	
	EditText txtprenom, txtnom, txtlogininscr, txtpasswordinscr, txtformation;
	RadioButton rbfeminin ;
	CheckBox cbBac,cbLicence, cbMaster;
	ImageButton btninscrire;
	String prenom, nom, genre, diplome, logininscr, passwordinscr, formation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscrire);
		
		txtnom=(EditText)findViewById(R.id.txtnom);	
		txtprenom=(EditText)findViewById(R.id.txtprenom);
		txtlogininscr=(EditText)findViewById(R.id.txtloginInscr);	
		txtpasswordinscr=(EditText)findViewById(R.id.txtpasswordInscr);
		txtformation=(EditText)findViewById(R.id.txtformation);

		rbfeminin=(RadioButton)findViewById(R.id.rbfeminin);	
		cbBac=(CheckBox)findViewById(R.id.cbbac);	
		cbLicence=(CheckBox)findViewById(R.id.cblicence);	
		cbMaster=(CheckBox)findViewById(R.id.cbmaster);	

		btninscrire=(ImageButton)findViewById(R.id.btninscrire);	
		
		btninscrire.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
		prenom=txtprenom.getText().toString();		
		nom=txtnom.getText().toString();
		logininscr=txtlogininscr.getText().toString();
		passwordinscr=txtpasswordinscr.getText().toString();
		formation=txtformation.getText().toString();

		if(rbfeminin.isChecked())
			genre="FEMME";
		else
			genre="HOMME";

		if(cbBac.isChecked())
			diplome="BAC";
		if(cbLicence.isChecked())
			diplome=diplome+" LICENCE";
		if(cbMaster.isChecked())
			diplome=diplome+" MASTER";

		prenom=Uri.encode(prenom, "utf-8");
		nom=Uri.encode(nom, "utf-8");
		genre=Uri.encode(genre, "utf-8");
		diplome=Uri.encode(diplome, "utf-8");
		formation=Uri.encode(formation, "utf-8");
		logininscr=Uri.encode(logininscr, "utf-8");
		passwordinscr=Uri.encode(passwordinscr, "utf-8");

		String url="http://10.0.2.2/miniprojet/inscription.php?prenom="+prenom+"&nom="+nom+"&genre="+genre+"&diplome" +
				"="+diplome+"&formation="+formation+"&logininscr="+logininscr+"&passwordinscr="+passwordinscr;

		Downloader d=new Downloader();
		d.execute(url);
			}
		});
		
	}
	
	protected class Downloader extends AsyncTask<String, Void, String>

	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(String... url) {
			 
			try {
	HttpClient client=new DefaultHttpClient();
	HttpGet get=new HttpGet(url[0]);
	ResponseHandler<String> tunnel=new BasicResponseHandler();
	String result=client.execute(get, tunnel);

	return result;
				
			} catch (Exception e) {
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			 
	 Toast.makeText(InscrireActivity.this, result, Toast.LENGTH_LONG
			 ).show();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inscrire, menu);
		return true;
	}

}

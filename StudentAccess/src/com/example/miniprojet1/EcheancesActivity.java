package com.example.miniprojet1;

import java.util.StringTokenizer;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class EcheancesActivity extends Activity {
	
	Button btnlp1, btnlp2, btnlp3, btnmp1, btnmp2, btnms;
	String titre;
	ImageButton btnretour;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_echeances);
		
		btnlp1 = (Button) findViewById(R.id.btnlp1);
		btnlp2 =(Button) findViewById(R.id.btnlp2);
		btnlp3 =(Button) findViewById(R.id.btnlp3);
		btnmp1 =(Button) findViewById(R.id.btnmp1);
		btnmp2 =(Button) findViewById(R.id.btnmp2);
		btnms =(Button) findViewById(R.id.btnms);
		btnretour =(ImageButton) findViewById(R.id.btnretour);
		
		btnretour.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				Intent intent=new Intent(EcheancesActivity
						.this, EspaceActivity.class);
				startActivity(intent);
			}
		});
		
		btnlp1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			titre="Dates utiles LP1";
			Downloader d=new Downloader();
			 d.execute("http://10.0.2.2/miniprojet/echeancesLP1.php?matricule="+AuthentificationActivity.matricule);
		}
	});
		
		btnlp2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				titre="Dates utiles LP2";
				Downloader d=new Downloader();
				 d.execute("http://10.0.2.2/miniprojet/echeancesLP2.php?matricule="+AuthentificationActivity.matricule);
			}
		});
		
		btnlp3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				titre="Dates utiles LP3";
				Downloader d=new Downloader();
				 d.execute("http://10.0.2.2/miniprojet/echeancesLP3.php?matricule="+AuthentificationActivity.matricule);
			}
		});
		
		btnmp1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				titre="Dates utiles MP1";
				Downloader d=new Downloader();
				 d.execute("http://10.0.2.2/miniprojet/echeancesMP1.php?matricule="+AuthentificationActivity.matricule);
			}
		});
		
		btnmp2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				titre="Dates utiles MP2";
				Downloader d=new Downloader();
				 d.execute("http://10.0.2.2/miniprojet/echeancesMP2.php?matricule="+AuthentificationActivity.matricule);
			}
		});
		
		btnms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				titre="Dates utiles MS";
				Downloader d=new Downloader();
				 d.execute("http://10.0.2.2/miniprojet/echeancesMS.php?matricule="+AuthentificationActivity.matricule);
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
		 
		result=result.substring(0,result.indexOf("!"));
		
	String tab[]=split(result, "#");
	String chaine="";
	for (int i = 0; i < tab.length; i++) {
		chaine=chaine+tab[i]+"\n\n";
	}

		
				AlertDialog dialog=new 
						AlertDialog.Builder(EcheancesActivity.this).create();

									dialog.setTitle(titre);
						 			dialog.setMessage(chaine);
									dialog.setIcon(R.drawable.infos);
									dialog.setButton("OK", new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface arg0, int arg1) {
										
											
										}
									});
									dialog.show();	
			}
			
		}	
		 
		public static String[] split(String text, String sep) {

			if (text == null || text.trim().equals("")) {
				return null;
			}
			text = text.trim();

			StringTokenizer st = new StringTokenizer(text.trim(), sep);
			int nbtoken = st.countTokens();
			if (nbtoken != 0) {
				String[] tmp = new String[nbtoken];
				int i = 0;
				while (st.hasMoreTokens()) {
					tmp[i] = st.nextToken();
					i++;
				}
				return tmp;
			}

			return null;
		}	
 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.echeances, menu);
		return true;
	}

}

package com.example.miniprojet1;

import java.util.StringTokenizer;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AuthentificationActivity extends Activity {
	
	EditText txtlogin, txtpassword;
	ImageButton btnquitter, btnconnexion;
	
	public static String matricule="";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_authentification);
		
		txtlogin = (EditText) findViewById(R.id.txtlogin);
		txtpassword = (EditText) findViewById(R.id.txtpassword);
		btnconnexion = (ImageButton) findViewById(R.id.btnconnexion);
		btnquitter = (ImageButton) findViewById(R.id.btnquitter);
		
		
btnquitter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(AuthentificationActivity
						.this, HomeActivity.class);
				startActivity(intent);
			}
		});
		
		btnconnexion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String login = txtlogin.getText().toString();
				final String password = txtpassword.getText().toString();

								if (login.equals("") || password.equals("")) {
					Toast.makeText(AuthentificationActivity.this,
									"Veuillez renseigner tous les champs",
											Toast.LENGTH_LONG).show();

								}
								
								else{
				String url="http://10.0.2.2/miniprojet/connexion.php?login="+login+"&password="+password;
				Downloader d=new Downloader();
				d.execute(url);

				
			}
			}
		});
		}
	
	protected class Downloader extends AsyncTask<String, Void, String>{
		
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
			
			result=result.substring(0,result.indexOf("#")-1); 
			String tab[]=split(result, "!");
			matricule=tab[1];
			Toast.makeText(AuthentificationActivity.this,
					"ID= "+matricule,
							Toast.LENGTH_LONG).show();
			
			if(result.contains("OK!")){
				Intent intent=new Intent(AuthentificationActivity.this, EspaceActivity.class
						);
				startActivity(intent);

						}
						else {
							Toast.makeText(AuthentificationActivity.this,
									"¨Paramétres incorrects ",
											Toast.LENGTH_LONG).show();


						}
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
	
}

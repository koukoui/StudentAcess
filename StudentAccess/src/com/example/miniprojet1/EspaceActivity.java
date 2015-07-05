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

public class EspaceActivity extends Activity {
	
	Button btnnote, btnecheances, btnassiduites, btnedt;
	ImageButton btnhome;
	public String titre;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_espace);
		
		btnnote = (Button) findViewById(R.id.btnnote);
		btnassiduites = (Button) findViewById(R.id.btnassiduites);
		btnecheances = (Button) findViewById(R.id.btnecheances);
		btnedt =(Button) findViewById(R.id.btnedt);
		btnhome = (ImageButton) findViewById(R.id.btnhome);
	
btnhome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(EspaceActivity
						.this, HomeActivity.class);
				startActivity(intent);
					}
		});

btnecheances.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(EspaceActivity
						.this, EcheancesActivity.class);
				startActivity(intent);
					}
		});
		
btnnote.setOnClickListener(new OnClickListener() {
			
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				titre = "NOTES";
				Downloader d=new Downloader();
				 d.execute("http://10.0.2.2/miniprojet/notes.php?matricule="+AuthentificationActivity.matricule);
			}
		});
		
btnassiduites.setOnClickListener(new OnClickListener() {
			
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				titre = "ASSIDUITES";
				Downloader d=new Downloader();
				 d.execute("http://10.0.2.2/miniprojet/assiduitee.php?matricule="+AuthentificationActivity.matricule);
			}
		});

/*btnecheances.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		
		Downloader d=new Downloader();
		 d.execute("http://10.0.2.2/miniprojet/echeances.php?matricule="+AuthentificationActivity.matricule);
	}
});*/
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
					AlertDialog.Builder(EspaceActivity.this).create();

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
		getMenuInflater().inflate(R.menu.espace, menu);
		return true;
	}

}

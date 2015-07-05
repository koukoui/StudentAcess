package com.example.miniprojet1;



import android.R.anim;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class HomeActivity extends TabActivity {

	TabHost tabs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Resources res = getResources();
		
		tabs=(TabHost)findViewById(android.R.id.tabhost);
		tabs.setup();
		
		TabSpec spec1=tabs.newTabSpec("Tab1");
		spec1.setContent(new Intent(HomeActivity.this, PresentActivity.class));
		spec1.setIndicator("HOME", res.getDrawable(android.R.drawable.ic_dialog_info));
		tabs.addTab(spec1);
		
		TabSpec spec2=tabs.newTabSpec("Tab2");
		spec2.setContent(new Intent(HomeActivity.this, InscrireActivity.class));
		spec2.setIndicator("Inscription", res.getDrawable(android.R.drawable.ic_input_add));
		tabs.addTab(spec2);
		
		TabSpec spec3=tabs.newTabSpec("Tab3");
		spec3.setContent(new Intent(HomeActivity.this, AuthentificationActivity.class));
		spec3.setIndicator("ETUDIANT", res.getDrawable(android.R.drawable.ic_lock_power_off));
		tabs.addTab(spec3);
		
		TabSpec spec4=tabs.newTabSpec("Tab4");
		spec4.setContent(new Intent(HomeActivity.this, HelpActivity.class));
		spec4.setIndicator("SEARCH", res.getDrawable(android.R.drawable.ic_dialog_info));
		tabs.addTab(spec4);
        
		tabs.setCurrentTab(0);
       
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		if (item.getItemId() == R.id.menuapropos) {
			AlertDialog dialog=new 
			AlertDialog.Builder(HomeActivity.this).create();

						dialog.setTitle("A propos");
			 			dialog.setMessage("L'Application Student Access.v1.0.0 est sous la proprieté de APOLLINAIRE et SY, Copyrigth Tout droit reservé @ Juin 2015. Contact 775662745. Email: appobako@gmail.com");
						dialog.setIcon(R.drawable.infos);
						dialog.setButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
							
								
							}
						});
						dialog.show();
						
					}
		else if (item.getItemId() == R.id.menucontacts) {
			AlertDialog dialog=new 
			AlertDialog.Builder(HomeActivity.this).create();

						dialog.setTitle("A propos");
			 			dialog.setMessage("Contact: +221766347086. Email: appobako@gmail.com");
						dialog.setIcon(R.drawable.infos);
						dialog.setButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
							
								
							}
						});
						dialog.show();
						
					}
		else if (item.getItemId() == R.id.menuhelp) {
			AlertDialog dialog=new 
			AlertDialog.Builder(HomeActivity.this).create();

						dialog.setTitle("Aide");
			 			dialog.setMessage("Cette application est une interface d’accès qui permet aux étudiants d'avoir une présentation de l'institut et permettre l'interaction entre le téléphone et la base de données du système d'information de l'établissement. Le dispositif mis en place permet les accès suivant: Une Présentation de l'établissement, Une rubrique d'aide, Une Page d'authentification, Une Page pour inscrire un étudiant dans le système, Une Page pour consulter les emplois du temps, Une Page pour consulter ses notes, Une Page pour consulter ses assiduités, Une page pour consulter les écheances projets, devoirs et examens.");
						dialog.setIcon(R.drawable.infos);
						dialog.setButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
							
								
							}
						});
						dialog.show();
						
					}
		return super.onOptionsItemSelected(item);
	}
}

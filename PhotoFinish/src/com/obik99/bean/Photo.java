package com.obik99.bean;

import android.os.Bundle;
import android.app.Activity;


public class Photo extends Activity {
	String nombreEvento;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		
		//Obtenemos parámetros y valores del Activity anterior e inicializamos
		nombreEvento = getIntent().getStringExtra("semilla");
	}
	
}

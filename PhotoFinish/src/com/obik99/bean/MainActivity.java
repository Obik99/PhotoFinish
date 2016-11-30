package com.obik99.bean;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;


public class MainActivity extends ActionBarActivity {
	//Variables Globales
	SharedPreferences preferencias;
	private EditText edtNombreEvento;
	private CheckBox cbxConfDefault;
	private RadioButton rbtnIzq;   //En SharedPreferences: Activo 1
	private RadioButton rbtnDer;   //En SharedPreferences: Activo 0
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Enlace con el Layout "activity_main.xml"
        edtNombreEvento = (EditText) findViewById(R.id.edtNombreEvento);
        cbxConfDefault = (CheckBox) findViewById(R.id.cbxValoresDefault);
        rbtnIzq = (RadioButton) findViewById(R.id.rbtnIzq);
        rbtnDer = (RadioButton) findViewById(R.id.rbtnDer);
        
        //Preferencias guardadas al Iniciar la Pantalla Principal
        preferencias = getSharedPreferences("Orientacion_Imagen", Context.MODE_PRIVATE);
        
        if(preferencias.getString("1", "").equals("")){
        	rbtnIzq.setChecked(true);
        	rbtnDer.setChecked(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void iniciarCompetencia(View view){ 
    	Intent intent = new Intent(this, Photo.class);
		intent.putExtra("NombreEvento", edtNombreEvento.getText().toString());
		startActivity(intent);
    }
    
    
  
}

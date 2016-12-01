package com.obik99.bean;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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
        
        //-------- VALORES INICIALES ---------
        //RadioButton desactivados, hasta que se dé click en CheckBox
        rbtnIzq.setEnabled(false);
        rbtnDer.setEnabled(false);
        
        //Preferencias guardadas al Iniciar la Pantalla Principal
        preferencias = getSharedPreferences("Orientacion_Imagen", Context.MODE_PRIVATE);
        
        //Condición para ver si la Orientación
        if(preferencias.getString("orientacionImg", "rbtnIzq").equals("rbtnIzq")){
        	rbtnIzq.setChecked(true);
        	rbtnDer.setChecked(false);
        }else if(preferencias.getString("orientacionImg", "rbtnIzq").equals("rbtnDer")){
        	rbtnIzq.setChecked(false);
        	rbtnDer.setChecked(true);
        }

        
        //Click en CheckBox del activity_main.xml
        cbxConfDefault.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(cbxConfDefault.isChecked()){
					rbtnDer.setEnabled(false);
					rbtnIzq.setEnabled(false);
				}
				else{
					rbtnDer.setEnabled(true);
					rbtnIzq.setEnabled(true);
				}
			}
		});
        
 
        rbtnIzq.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String dato = "rbtnIzq";
				SharedPreferences preferencias=getSharedPreferences("orientacionImg",Context.MODE_PRIVATE);
		        Editor editor=preferencias.edit();
		        editor.putString("orientacionImg", dato);
		        editor.commit();
			}
		});
        
        rbtnDer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String dato = "rbtnDer";
				SharedPreferences preferencias=getSharedPreferences("orientacionImg",Context.MODE_PRIVATE);
		        Editor editor=preferencias.edit();
		        editor.putString("orientacionImg", dato);
		        editor.commit();
			}
		});
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
    
    //Pasar al Acitivity "photo.xml"
    public void iniciarCompetencia(View view){ 
    	Intent intent = new Intent(this, Photo.class);
    	String orientacion = ""+((rbtnIzq.isChecked()) ? "izq":"der");
    	
    	//Enviar datos al siguiente Activity
		intent.putExtra("nombreEvento", edtNombreEvento.getText().toString());
		intent.putExtra("orientacionImagen", orientacion);
		
        //Iniciar Activity
		startActivity(intent);
    }
    
    //Cambiar SharedPreferences dependiendo de la Orientación de la Imagen
    public void clickRbtnIzq(){
		String dato = "rbtnIzq";
		SharedPreferences preferencias=getSharedPreferences("orientacionImg",Context.MODE_PRIVATE);
        Editor editor=preferencias.edit();
        editor.putString("orientacionImg", dato);
        editor.commit();
    }
    
    public void clickRbtnDer(){
		String dato = "rbtnDer";
		SharedPreferences preferencias=getSharedPreferences("orientacionImg",Context.MODE_PRIVATE);
        Editor editor=preferencias.edit();
        editor.putString("orientacionImg", dato);
        editor.commit();
    }
  
}

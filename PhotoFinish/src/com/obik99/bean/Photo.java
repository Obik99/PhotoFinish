package com.obik99.bean;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;


public class Photo extends Activity implements SurfaceHolder.Callback{
	
	//Variables Globales
	private String nombreEvento;
	private String orientacionImg;
	
	private MediaRecorder mediaRecorder = null;
	private MediaPlayer   mediaPlayer = null;
	private String        videoPhotoFinish = null;
	private boolean       grabando = false;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		
		//Valores del Activity anterior
		orientacionImg = getIntent().getStringExtra("orientacionImagen");
		nombreEvento = getIntent().getStringExtra("nombreEvento");
		
		//Inicialización de Parámetros
		videoPhotoFinish = Environment.getExternalStorageDirectory() + "/test.mp4";

		
	}
	
	//Método para configurar los atributos de la grabación
	public void prepareRecorder(){
	    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
	    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);     
	    mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
	}
	
	//Para inicializar la grabación
	@Override
	public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2, int arg3) {
		//Verificamos primero que sea nulo el valor de mediaRecorder
		if (mediaRecorder == null) {
	        mediaRecorder = new MediaRecorder();
	        mediaRecorder.setPreviewDisplay(holder.getSurface());
	    }
		
		//Verificamos primero que sea nulo el valor de mediaPlayer
		if (mediaPlayer == null) {
	        mediaPlayer = new MediaPlayer();
	        mediaPlayer.setDisplay(holder);
	    }
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
	}
	
	//Sirve para liberar los recursos
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		mediaRecorder.release();
		mediaPlayer.release();
	}
	
}

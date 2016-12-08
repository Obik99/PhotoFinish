package com.obik99.bean;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;


public class Photo extends Activity implements SurfaceHolder.Callback{
	
	//Variables Globales
	private String nombreEvento;
	private String orientacionImg;
	
	private Button btnGrabar;
	private Button btnDetener;
	private Button btnReproducir;
	
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
        btnGrabar =  (Button)findViewById(R.id.btnGrabar);
		btnDetener = (Button)findViewById(R.id.btnDetener);
		btnReproducir = (Button)findViewById(R.id.btnReproducir);
		
		
		videoPhotoFinish = Environment.getExternalStorageDirectory() + "/test.mp4";
		SurfaceView surface = (SurfaceView)findViewById(R.id.layoutVideo);
		SurfaceHolder holder = surface.getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		
		//Acciones de los botones
		btnGrabar.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v) {
		            btnGrabar.setEnabled(false);
		            btnDetener.setEnabled(true);
		            btnReproducir.setEnabled(false);
		            prepareRecorder();
		            mediaRecorder.setOutputFile(fileName);
		            try {
		                mediaRecorder.prepare();
		            } catch (IllegalStateException e) {
		            } catch (IOException e) {
		            }
		 
		            mediaRecorder.start();
		            recording = true;
		    }
		});
		Botón detener: habilitam
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

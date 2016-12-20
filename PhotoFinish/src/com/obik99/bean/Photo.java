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
    /** Called when the activity is first created. */
	
	private boolean recording = false; // Indica si se está grabando	
	
	private MediaRecorder mediaRecorder; // Permite la grabación
	private MediaPlayer mediaPlayer; // Permite la reproducción
	private String path; // Ruta de almacenamiento del archivo
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        
        path = Environment.getExternalStorageDirectory() + "/myvideo.mp4";
        
        SurfaceView surface = (SurfaceView) findViewById(R.id.surfaceView);
        surface.getHolder().addCallback(this);
        surface.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        
        final Button recordButton = (Button) findViewById(R.id.btnGrabar);
        final Button stopButton = (Button) findViewById(R.id.btnParar);
        final Button playButton = (Button) findViewById(R.id.btnRepr);
        
        recordButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				recordButton.setEnabled(false);
				stopButton.setEnabled(true);
				playButton.setEnabled(false);
				recording = true;
				
				// Se establecen las opciones de audio y de vídeo para la grabación

		    	mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		    	mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		    	mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

		    	// Ruta de grabación
		    	mediaRecorder.setOutputFile(path);
		    	
		    	try {
					mediaRecorder.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				mediaRecorder.start();
			}
		});
        
        stopButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				recordButton.setEnabled(true);
				stopButton.setEnabled(false);
				playButton.setEnabled(true);
				
				if (recording){
					mediaRecorder.stop();
					mediaRecorder.reset();
					recording = false;
				}else{
					mediaPlayer.stop();
					mediaPlayer.reset();
					playButton.setText("Play");
				}
			}
		});
        
        playButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				recordButton.setEnabled(false);
				stopButton.setEnabled(true);
				playButton.setEnabled(true);
				
				if (!mediaPlayer.isPlaying()){
					
					playButton.setText("Pause");
					
					mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
						
						public void onCompletion(MediaPlayer mp) {
							recordButton.setEnabled(true);
							stopButton.setEnabled(false);
							playButton.setEnabled(true);
							playButton.setText("Play");
						}
					});
					
					if (mediaPlayer.getCurrentPosition() == 0){
						try{						
							mediaPlayer.setDataSource(path);
							mediaPlayer.prepare();
						} catch(IllegalArgumentException e) {
						} catch (IOException e) {
						}
					}
					
					mediaPlayer.start();
				}else{
					mediaPlayer.pause();
					playButton.setText("Play");
				}
			}
		});
    }

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		
		// Inicialización del reproductor y grabador si son nulos
		if (mediaRecorder == null){
			mediaRecorder = new MediaRecorder();
			mediaRecorder.setPreviewDisplay(holder.getSurface());
		}
		
		if (mediaPlayer == null){
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setDisplay(holder);
		}		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// Se liberan los recursos asociados con estos objetos
		mediaRecorder.release();
		mediaPlayer.release();		
	}
}
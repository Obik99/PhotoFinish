package com.obik99.bean;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Photo extends Activity {
	 private Camera mCamera;
	    private CameraPreview mPreview;
	    private ImageView imv;
	    private Button b;

	    private Camera.PictureCallback mPicture = new Camera.PictureCallback(){

	        @Override
	        public void onPictureTaken(byte[] bytes, Camera camera) {
	            Bitmap b = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
	            imv.setImageBitmap(b);
	        }
	    };

	    @Override
	    public void onCreate(Bundle savedInstaceState){
	        super.onCreate(savedInstaceState);
	        setContentView(R.layout.activity_main);
	        mCamera = getCameraInstance();
	        mPreview = new CameraPreview(this,mCamera);
	        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
	        imv = (ImageView)findViewById(R.id.imageview1);
	        b = (Button)findViewById(R.id.button_capture);
	        b.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                mCamera.takePicture(null,null,mPicture);
	            }
	        });
	        preview.addView(mPreview);
	    }

	    @Override
	    public void onPause(){
	        super.onPause();
	        if(mCamera != null){
	            mCamera.release();
	            mCamera = null;
	        }
	    }

	    private boolean checkCameraHardware(Context context) {
	        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public static Camera getCameraInstance() {
	        Camera c = null;
	        try {
	            c = Camera.open();
	        } catch (Exception e){

	        }
	        return c;
	    }
	    
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{

	        private Camera mCamera;
	        private SurfaceHolder mHolder;

	        public CameraPreview(Context context, Camera camera){
	            super(context);
	            mCamera = camera;
	            mHolder = getHolder();
	            mHolder.addCallback(this);
	            mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	        }

	        @Override
	        public void surfaceCreated(SurfaceHolder surfaceHolder) {
	            try {
	                mCamera.setPreviewDisplay(surfaceHolder);
	                mCamera.startPreview();
	            } catch (IOException e){
	                Log.d("Error",e.toString());
	            }
	        }

	        @Override
	        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
	            if(mHolder.getSurface() == null){
	                return;
	            }

	            try {
	                mCamera.stopPreview();
	            } catch (Exception e){

	            }
	            try {
	                mCamera.setPreviewDisplay(surfaceHolder);
	                mCamera.startPreview();
	            } catch (Exception e){
	                Log.d("ERROR",e.toString());
	            }

	        }

	        @Override
	        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

	        }
	    }
}
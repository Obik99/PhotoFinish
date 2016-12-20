package com.obik99.controlador;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Imagen implements Parcelable {
	
	public Uri uri;
	public int orientation;
	public double segundo;

	//-----------------------------GETTERS AND SETTERS-----------------------------
	public Uri getUri() {
		return uri;
	}

	public void setUri(Uri uri) {
		this.uri = uri;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public double getSegundo() {
		return segundo;
	}

	public void setSegundo(double segundo) {
		this.segundo = segundo;
	}

	//-----------------------------    CONSTRUCTORES   -----------------------------
	public Imagen(Uri mUri, int mOrientation, double mSegundo) {
	    uri = mUri;
	    orientation = mOrientation;
	    segundo = mSegundo;
	}
	
    //---------------------   IMPLEMENTAR FUNCIONES PARCELABLES   -------------------
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(uri.toString());
		dest.writeInt(orientation);
		dest.writeDouble(segundo);		
	}

}

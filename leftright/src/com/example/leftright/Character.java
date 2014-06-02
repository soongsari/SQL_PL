package com.example.leftright;

import com.jaehyeon.genlib.basic.JHLoader_bitmap;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class Character {
	
	
	float hp;
	
	int cha_sizeX=55;
	int cha_sizeY=113;
	
	int x;
	int y;
	
	int mX=5;
	
	boolean die;
	Bitmap chracter;
	
	public Character(JHLoader_bitmap bitmapLoader, String type){
		hp=100;
		x=200;
		y=540;
		
		if(type.equals("girl"))
			chracter = bitmapLoader.load("girl.png");
		else{
			chracter = bitmapLoader.load("boy.png");
		}
		
		die = false;
	}
	
	public void MoveLeft(){
		if(x>=0)
			x-=mX;
	}
	public void MoveRight(){
		if(x<=425)
			x+=mX;
	}

	public void Die(){
		die=true;
	}
	
	public boolean CheckLive(){
		if(die) return false;
		else return true;
	}
	
	public void Draw(Canvas canvas){
		
		canvas.drawBitmap(chracter, new Rect(0,0,cha_sizeX,cha_sizeY), new Rect(0+x,y,cha_sizeX+x,y+cha_sizeY), null);
	}
}

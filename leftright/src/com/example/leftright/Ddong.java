package com.example.leftright;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.jaehyeon.genlib.basic.JHLoader_bitmap;

public class Ddong {
	
	int ddong_sizeX=20;  //20
	int ddong_sizeY=25;   //25
	int mX;
	int mY=-5000;
	int ddong_speed;
	
	Bitmap ddong;
	
	public Ddong(JHLoader_bitmap bitmapLoader){
		Random random = new Random();
		mX=random.nextInt(430);
		mY+=random.nextInt(5000);
		ddong = bitmapLoader.load("F.png");
		ddong_speed=random.nextInt(6)+5;
	}
	
	public void MoveDown(){
	
		mY+=ddong_speed;
		if(mY>800)
			mY=-5000;
		
	}
	
	public void Draw(Canvas canvas){
		
		canvas.drawBitmap(ddong, new Rect(0,0,ddong_sizeX,ddong_sizeY), new Rect(0+mX,mY,ddong_sizeX+mX,mY+ddong_sizeY), null);
	}
	
}

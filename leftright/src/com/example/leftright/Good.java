package com.example.leftright;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.jaehyeon.genlib.basic.JHLoader_bitmap;

public class Good {
	
	int good_sizeX=30;  
	int good_sizeY=26;   
	int mX;
	int mY=-5000;
	int good_speed;
	
	Bitmap good;
	
	public Good(JHLoader_bitmap bitmapLoader){
		Random random = new Random();
		mX=random.nextInt(430);
		mY+=random.nextInt(5000);
		good = bitmapLoader.load("A.png");
		good_speed=random.nextInt(4)+5;
	}
	
	public void MoveDown(){
	
		mY+=good_speed;
		if(mY>800)
			mY=-5000;
		
	}
	
	public void Draw(Canvas canvas){
		
		canvas.drawBitmap(good, new Rect(0,0,good_sizeX,good_sizeY), new Rect(0+mX,mY,good_sizeX+mX,mY+good_sizeY), null);
	}
	
}

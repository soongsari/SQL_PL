package com.example.leftright;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.jaehyeon.gengamelib.work.GScene;
import com.jaehyeon.genlib.basic.JHLoader_bitmap;
import com.jaehyeon.genlib.basic.JHTouch;
import com.jaehyeon.genlib.basic.JHTouch_single.Info;

public class GameOver extends SceneCover implements GScene{


	Bitmap gameover;
	
	@Override
	public void fCreate(JHLoader_bitmap bitmapLoader) {
		// TODO Auto-generated method stub
		gameover = bitmapLoader.load("gameover.png");
	}

	@Override
	public int fRender(Info touchInfo) {
		// TODO Auto-generated method stub

		
		if(touchInfo!=null){
			
			int x=(int) touchInfo.X;
			int y=(int) touchInfo.Y;
			
			if(touchInfo.ACTION==JHTouch.ACTION_DOWN)
			{
			
			}
			
			else if(touchInfo.ACTION==JHTouch.ACTION_PUSH)
			{
				
				if((0<=x&&x<=240)&&(400<=y&&y<=500))
					return 2;
				else if((240<x&&x<=480)&&(400<=y&&y<=500)){
					return 0;
				}
				
			}
			else if(touchInfo.ACTION==JHTouch.ACTION_UP)
			{
				
			}
		}
		
		return 3;
	}

	@Override
	public void fDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawColor(Color.WHITE);
		
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setTextSize(25);
		canvas.drawBitmap(gameover, new Rect(0,0,480,689), new Rect(0,0,480,680), null);
	
		canvas.drawText("score="+super.to_time, 200, 100, paint);  //time Ç¥½ÃÃ¢
	}

	@Override
	public void fDestroy() {
		// TODO Auto-generated method stub
	
	}

}

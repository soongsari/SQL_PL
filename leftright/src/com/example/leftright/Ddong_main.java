package com.example.leftright;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.jaehyeon.gengamelib.work.GScene;
import com.jaehyeon.genlib.basic.JHLoader_bitmap;
import com.jaehyeon.genlib.basic.JHTouch;
import com.jaehyeon.genlib.basic.JHTouch_single.Info;

public class Ddong_main implements GScene{
	Bitmap D_main;
	
	@Override
	public void fCreate(JHLoader_bitmap bitmapLoader) {
		// TODO Auto-generated method stub
		D_main = bitmapLoader.load("main.png");
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
				
				if((65<=x&&x<=400)&&(430<=y&&y<=530))
					return 1;
				
			}
			else if(touchInfo.ACTION==JHTouch.ACTION_UP)
			{
				
			}
		}
		
		return 0;
	}

	@Override
	public void fDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(D_main, new Rect(0,0,480,680), new Rect(0,0,480,680), null);
	}

	@Override
	public void fDestroy() {
		// TODO Auto-generated method stub
		
	}

}

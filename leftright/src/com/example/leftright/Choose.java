package com.example.leftright;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.jaehyeon.gengamelib.work.GScene;
import com.jaehyeon.genlib.basic.JHLoader_bitmap;
import com.jaehyeon.genlib.basic.JHTouch;
import com.jaehyeon.genlib.basic.JHTouch_single.Info;

public class Choose implements GScene{
	
	public String chr_type;
	Bitmap ChooseView;
	
	@Override
	public void fCreate(JHLoader_bitmap bitmapLoader) {
		// TODO Auto-generated method stub
		ChooseView = bitmapLoader.load("choose.png");
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
				
				if((80<=x&&x<=180)&&(310<=y&&y<=460)){
					chr_type="girl";
					return 2; //여자
				}
				
				else if((320<=x&&x<=400)&&(310<=y&&y<=460)){
					chr_type="boy";
					return 2; //남자
				}
			}
			else if(touchInfo.ACTION==JHTouch.ACTION_UP)
			{
				
			}
		}
		

		return 1;
	}

	@Override
	public void fDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(ChooseView, new Rect(0,0,480,680), new Rect(0,0,480,680), null);
		
	}

	@Override
	public void fDestroy() {
		// TODO Auto-generated method stub
		
	}

}

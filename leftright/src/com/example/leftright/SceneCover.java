package com.example.leftright;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.jaehyeon.gengamelib.work.GScene;
import com.jaehyeon.genlib.basic.JHLoader_bitmap;
import com.jaehyeon.genlib.basic.JHTouch;
import com.jaehyeon.genlib.basic.JHTouch_single.Info;

public class SceneCover extends Choose implements GScene {

	//480x680 화면크기
	
	float hp;
	int time;
	int to_time;  //시간(1초씩 증가)
	
	Bitmap back;

	Character chr;

	Drop_ddong d_drop;
	Drop_good g_drop;
	

	@Override
	public void fCreate(JHLoader_bitmap bitmapLoader) {
		// TODO Auto-generated method stub
		
		
		d_drop = new Drop_ddong(bitmapLoader);  //똥 내리는것 관련 클래스
		g_drop = new Drop_good(bitmapLoader);
		
		time=0;
		to_time=0;
		
//		Log.i(JHSystem.MSG_INFO, "SceneCover.fCreate() - in");
	
		if(chr_type!=null)
			chr = new Character(bitmapLoader,chr_type);
		else
			chr = new Character(bitmapLoader,"girl");
	
		back=bitmapLoader.load("background.png");  //배경

	}

	void time_past(){
		
		chr.hp-=0.1;  //hp감소
		time+=1;
		to_time=time/60;  //1초씩 증가｡
	}
	
	@Override
	public int fRender(Info touchInfo) {
		
		// TODO Auto-generated method stub
		int mX=0;
	
		time_past();  // 1초씩 증가, hp감소

		
		if(!(chr.CheckLive())){  //똥에 맞았을 때
			return 3;
		}

		
		d_drop.drop(chr); //똥 내리기 관련
		d_drop.increse(to_time); //시간 당 똥 갯수 증가
		g_drop.drop(chr); //a+ 내리기
		
		
		
		/////////////////////////////////////////////////////////////터치 관련
		if(touchInfo!=null){
			if(touchInfo.ACTION==JHTouch.ACTION_DOWN)
			{

			}
			else if(touchInfo.ACTION==JHTouch.ACTION_PUSH)
			{
				mX=(int) touchInfo.X;
				if(mX<215)
				{
					chr.MoveLeft();
				}
				else
				{
					chr.MoveRight();
				}
			}
			else if(touchInfo.ACTION==JHTouch.ACTION_UP)
			{
				
			}
		}
	
		return 2;
	}

	@Override
	public void fDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawColor(Color.WHITE);

		canvas.drawBitmap(back, new Rect(0,0,480,680), new Rect(0,0,480,680), null);
		chr.Draw(canvas);
		
		d_drop.draw(canvas);
		g_drop.draw(canvas);
		

		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setTextSize(25);
		canvas.drawText("hp="+(int)chr.hp, 20, 20, paint);   //hp 글씨
		canvas.drawText("time="+to_time, 370, 20, paint);  //time 글씨
		
	}

	@Override
	public void fDestroy() {
		// TODO Auto-generated method stub
		
	}

}

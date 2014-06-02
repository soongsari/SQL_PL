package com.example.leftright;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.jaehyeon.genlib.basic.JHLoader_bitmap;

public class Drop_ddong {

	int ddong_Mnum;   //똥 최대 갯수
	int first_ddong_num; //똥 초기 갯수
	int ddong_num=first_ddong_num;       //똥 현재 갯수
	
	Ddong ddong;
	
	ArrayList<Ddong> list = new ArrayList<Ddong>();
	
	public Drop_ddong(JHLoader_bitmap bitmapLoader){
		
		list.clear();
		ddong_Mnum=90;   //똥 최대 갯수
		first_ddong_num=30; //똥 초기 갯수
		ddong_num=first_ddong_num;       //똥 현재 갯수
		
		made(bitmapLoader);
		
	
	}
	
	void made(JHLoader_bitmap bitmapLoader){
		
		//똥과 A+을 list에 저장 똥의 갯수는 최대의 갯수로 저장 해놓고 나중에 점점 더 많이 그릴 것임
		for(int i=0;i<ddong_Mnum;i++){
			ddong = new Ddong(bitmapLoader);
			list.add(ddong);
		}
	}
	
	void drop(Character chr){
		for(int i=0;i<ddong_num;i++){
			ddong = list.get(i);
			ddong.MoveDown();
			
			attack(chr,ddong);
			
		}
		
	}
	
	private void attack(Character chr,Ddong ddong){
		
		//똥에 맞으면
		if((chr.x<=ddong.mX)&&(ddong.mX<=chr.x+chr.cha_sizeX)||(chr.x<=ddong.mX+ddong.ddong_sizeX)&&(ddong.mX+ddong.ddong_sizeX<=chr.x+chr.cha_sizeX)){
			if((chr.y<=ddong.mY)&&(ddong.mY<=chr.y+chr.cha_sizeY)||(chr.y<=ddong.mY+ddong.ddong_sizeY)&&(ddong.mY+ddong.ddong_sizeY<=chr.y+chr.cha_sizeY)){
			
				chr.hp-=200;   //hp 200 감소
				ddong.mY=-5000;  //위쪽으로 사라짐(먹었으니까)
				if(chr.hp<0)  //죽음
					chr.Die();
				
			}
		}
	
	}
	
	void increse(int to_time){
		
	if(ddong_num<ddong_Mnum){  //ddong의 갯수가 Max의 갯수를 넘지 않을때 까지
			
			ddong_num=to_time/6+first_ddong_num;   //현재 똥의 갯수 정하기 (시간이 10초 증가할 때마다 똥 1개 증가)
		}
	}
	
	void draw(Canvas canvas){
		//똥과 a+을 그림
		for(int i=0;i<ddong_num;i++){
			list.get(i).Draw(canvas);
		}
	}
}

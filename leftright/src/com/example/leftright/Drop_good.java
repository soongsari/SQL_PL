package com.example.leftright;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.jaehyeon.genlib.basic.JHLoader_bitmap;

public class Drop_good {
	
	int good_num;   //A+ °¹¼ö
	
	Good good;
	
	ArrayList<Good> g_list = new ArrayList<Good>();
	
	public Drop_good(JHLoader_bitmap bitmapLoader){
		
		g_list.clear();
		good_num=15;
		made(bitmapLoader);
	
	}
	
	void made(JHLoader_bitmap bitmapLoader){
		
		for(int i=0;i<good_num;i++){
			good = new Good(bitmapLoader);
			g_list.add(good);
		}
	}

	void drop(Character chr){
		
		for(int i=0;i<g_list.size();i++){
			good = g_list.get(i);
			good.MoveDown();
			
			attack(chr);
		}
	}
	
	private void attack(Character chr){

		if((chr.x<=good.mX)&&(good.mX<=chr.x+chr.cha_sizeX)||(chr.x<=good.mX+good.good_sizeX)&&(good.mX+good.good_sizeX<=chr.x+chr.cha_sizeX)){
			if((chr.y<=good.mY)&&(good.mY<=chr.y+chr.cha_sizeY)||(chr.y<=good.mY+good.good_sizeY)&&(good.mY+good.good_sizeY<=chr.y+chr.cha_sizeY)){
				chr.hp+=50;  //¸Ô¾úÀ»¶§ hp 50Áõ°¡
				good.mY=-5000;
			}
				
		}
	}
	
	void draw(Canvas canvas){
		
		for(int i=0;i<g_list.size();i++){
			g_list.get(i).Draw(canvas);
		}
	}
}

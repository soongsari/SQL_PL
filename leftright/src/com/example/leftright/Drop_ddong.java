package com.example.leftright;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.jaehyeon.genlib.basic.JHLoader_bitmap;

public class Drop_ddong {

	int ddong_Mnum;   //�� �ִ� ����
	int first_ddong_num; //�� �ʱ� ����
	int ddong_num=first_ddong_num;       //�� ���� ����
	
	Ddong ddong;
	
	ArrayList<Ddong> list = new ArrayList<Ddong>();
	
	public Drop_ddong(JHLoader_bitmap bitmapLoader){
		
		list.clear();
		ddong_Mnum=90;   //�� �ִ� ����
		first_ddong_num=30; //�� �ʱ� ����
		ddong_num=first_ddong_num;       //�� ���� ����
		
		made(bitmapLoader);
		
	
	}
	
	void made(JHLoader_bitmap bitmapLoader){
		
		//�˰� A+�� list�� ���� ���� ������ �ִ��� ������ ���� �س��� ���߿� ���� �� ���� �׸� ����
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
		
		//�˿� ������
		if((chr.x<=ddong.mX)&&(ddong.mX<=chr.x+chr.cha_sizeX)||(chr.x<=ddong.mX+ddong.ddong_sizeX)&&(ddong.mX+ddong.ddong_sizeX<=chr.x+chr.cha_sizeX)){
			if((chr.y<=ddong.mY)&&(ddong.mY<=chr.y+chr.cha_sizeY)||(chr.y<=ddong.mY+ddong.ddong_sizeY)&&(ddong.mY+ddong.ddong_sizeY<=chr.y+chr.cha_sizeY)){
			
				chr.hp-=200;   //hp 200 ����
				ddong.mY=-5000;  //�������� �����(�Ծ����ϱ�)
				if(chr.hp<0)  //����
					chr.Die();
				
			}
		}
	
	}
	
	void increse(int to_time){
		
	if(ddong_num<ddong_Mnum){  //ddong�� ������ Max�� ������ ���� ������ ����
			
			ddong_num=to_time/6+first_ddong_num;   //���� ���� ���� ���ϱ� (�ð��� 10�� ������ ������ �� 1�� ����)
		}
	}
	
	void draw(Canvas canvas){
		//�˰� a+�� �׸�
		for(int i=0;i<ddong_num;i++){
			list.get(i).Draw(canvas);
		}
	}
}

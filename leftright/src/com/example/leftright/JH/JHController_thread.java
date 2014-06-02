package com.example.leftright.JH;

import android.util.Log;

public class JHController_thread
{
	private String name;
	private JHThread thread;
	
	// 생성자(쓰레드 컨트롤러 이름을 넣어주면 됨)
	public JHController_thread(String name)
	{
		this.name = name;
	}
	
	// 기본 쓰레드를 실행시켜줌(실행 될 쓰레드의 이름, 쓰레드에서 돌릴 클래스)
	public void play(String threadName, JHThread.FuncClass funcClass)
	{
		Log.i(JHSystem.MSG_INFO, "JHController_thread.play() - in");
		
		if(this.thread == null)
		{
			this.thread = new JHThread(threadName, funcClass);
			this.thread.begin();
			
			Log.i(JHSystem.MSG_INFO, "JHController_thread.play() - out");
		}
		else
		{
			throw new RuntimeException("JHController_thread.play() - already have thread(" + this.thread.getName() + ")");
		}
		
		Log.i(JHSystem.MSG_INFO, "JHController_thread.play() - out");
	}
	
	// 루프 쓰레드를 실행시켜 줌(실행 될 쓰레드 이름, 쓰레드에서 돌릴 클래스, 쓰레드 돌아가는 속도-1이 가장빠름)
	public void play(String threadName, JHThread.FuncClass funcClass, int delayTime)
	{
		Log.i(JHSystem.MSG_INFO, "JHController_thread.play() - in");
		
		if(this.thread == null)
		{
			this.thread = new JHThread_loop(threadName, funcClass, delayTime);
			this.thread.begin();
			
			Log.i(JHSystem.MSG_INFO, "JHController_thread.play() - out");
		}
		else
		{
			throw new RuntimeException("JHController_thread.play() - already have thread(" + this.thread.getName() + ")");
		}
	}
	
	// 실행 된 쓰레드가 종료 됨
	public void done()
	{
		Log.i(JHSystem.MSG_INFO, "JHController_thread.done() - in");
		
		if(this.thread != null)
		{
			boolean complete = false;
			
			if(this.thread.checkUsed())
			{
				while(!complete)
				{
					try
					{
						this.thread.join();
						complete = true;
					} 
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				
				this.thread = null;
			}
			
			Log.i(JHSystem.MSG_INFO, "JHController_thread.done() - out(" + complete + ")");
		}
		else
		{
			throw new RuntimeException("JHController_thread.done() - don't have thread(" + this.name + ")");
		}
	}
}

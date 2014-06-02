package com.example.leftright.JH;

import android.util.Log;

public class JHThread_loop extends JHThread
{
	private boolean life;
	protected int delayTime;
	
	public JHThread_loop(String name, FuncClass funcClass, int delayTime)
	{
		super(name, funcClass);
		
		this.life = false;
		this.delayTime = delayTime;
	}
	
	@Override
	public void begin()
	{
		Log.i(JHSystem.MSG_INFO, "JHThread_loop.begin() - in - " + super.getName());
		
		super.begin();
		
		this.life = true;
		
		Log.i(JHSystem.MSG_INFO, "JHThread_loop.begin() - out - " + super.getName());
	}
	
	@Override
	public boolean checkUsed()
	{
		Log.i(JHSystem.MSG_INFO, "JHThread_loop.pleaseJoin() - in - " + super.getName());
		
		this.life = !super.checkUsed();
		
		Log.i(JHSystem.MSG_INFO, "JHThread_loop.pleaseJoin() - out(" + !this.life + ") - " + super.getName());
		
		return !this.life;
	}
	
	@Override
	public void run()
	{
		Log.i(JHSystem.MSG_INFO, "JHThread_loop.run() - in - " + super.getName());
		
		// Wait begin.
		while(!this.life)
		{
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// go loop.
		while(true)
		{
			if(this.life)
			{
				this.funcClass.Render();
			}
			else
			{
				break;
			}
			
			try
			{
				Thread.sleep(this.delayTime);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		Log.i(JHSystem.MSG_INFO, "JHThread_loop.run() - out - " + super.getName());
	}
}

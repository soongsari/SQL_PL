package com.example.leftright.JH;

import android.util.Log;

public class JHThread extends Thread
{
	public static interface FuncClass
	{
		public void Render();
	}
	
	private boolean isUsed;
	protected FuncClass funcClass;
	
	public JHThread(String name, FuncClass funcClass)
	{
		super.setName(name);
		
		this.isUsed = false;
		this.funcClass = funcClass;
	}
	
	public void begin()
	{
		Log.i(JHSystem.MSG_INFO, "JHThread.begin() - in - " + super.getName());
		
		try
		{
			super.start();
			this.isUsed = true;
		}
		catch(IllegalThreadStateException e)
		{
			throw new RuntimeException("JHThread.begin() - already have thread(" + super.getName() + ")");
		}
		
		Log.i(JHSystem.MSG_INFO, "JHThread.begin() - out - " + super.getName());
	}
	
	public boolean checkUsed()
	{
		Log.i(JHSystem.MSG_INFO, "JHThread.pleaseJoin() - in - " + super.getName());
		
		Log.i(JHSystem.MSG_INFO, "JHThread.pleaseJoin() - out(" + this.isUsed + ") - " + super.getName());
		
		return this.isUsed;
	}
	
	@Override
	public void run()
	{
		Log.i(JHSystem.MSG_INFO, "JHThread.run() - in - " + super.getName());
		
		// Wait begin.
		while(!this.isUsed)
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
		
		// go.
		this.funcClass.Render();
		
		Log.i(JHSystem.MSG_INFO, "JHThread.run() - out - " + super.getName());
	}
}

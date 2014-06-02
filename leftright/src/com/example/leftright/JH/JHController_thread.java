package com.example.leftright.JH;

import android.util.Log;

public class JHController_thread
{
	private String name;
	private JHThread thread;
	
	// ������(������ ��Ʈ�ѷ� �̸��� �־��ָ� ��)
	public JHController_thread(String name)
	{
		this.name = name;
	}
	
	// �⺻ �����带 ���������(���� �� �������� �̸�, �����忡�� ���� Ŭ����)
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
	
	// ���� �����带 ������� ��(���� �� ������ �̸�, �����忡�� ���� Ŭ����, ������ ���ư��� �ӵ�-1�� �������)
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
	
	// ���� �� �����尡 ���� ��
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

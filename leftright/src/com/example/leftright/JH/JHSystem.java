package com.example.leftright.JH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.Window;
import android.view.WindowManager;

public class JHSystem
{
	public static final String MSG_INFO = "msg_info";
	public static final String MSG_ERROR = "msg_error";
	
	private JHSystem()
	{	
	}
	
	public static void fullScreen(Activity activity)
	{
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	public static InputStream readFile(String path) throws IOException
	{
		return new FileInputStream(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator + path);
	}
	
	public static OutputStream writeFile(String path) throws IOException
    {
        return new FileOutputStream(Environment.getExternalStorageDirectory()
        		.getAbsolutePath() + File.separator + path);
    }
	
	public static Bitmap loadBitmap(AssetManager assets, String fileName)
	{
		InputStream in = null;
		
		try
		{
			in = assets.open(fileName);
			return BitmapFactory.decodeStream(in);
		}
		catch(IOException e)
		{
			throw new RuntimeException("Couldn't load bitmap '" + fileName + "'", e);
		}
		finally
		{
			if(in != null)
			{
				try
				{
					in.close();
				}
				catch(IOException e)
				{
					
				}
			}
		}
	}
}

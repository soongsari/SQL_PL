package com.example.leftright;

import com.jaehyeon.gengamelib.work.GActivity;
import com.jaehyeon.gengamelib.work.GFrame;


public class MainActivity extends GActivity {

	@Override
	protected void setting(GFrame frame)
	{
		frame.allocScene(4);
		frame.setScene(new Ddong_main());   // 메인 화면
		frame.setScene(new Choose());
		frame.setScene(new SceneCover());	// 게임화면
		frame.setScene(new GameOver());

		
		//
//		JHController_thread threadController;
//		threadController = new JHController_thread("쓰레드1");
//		threadController.play("쓰레드1", new threadClass());
//		threadController.play("쓰레드1", new threadClass(), 1);
//		threadController.done();
	}
}

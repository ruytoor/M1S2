package main;
import java.util.Random;

import javazoom.jl.decoder.Equalizer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.LillePlayer;


public class MyPlayer {

	private LillePlayer player;
	private static final String listMusique[]={"Wana.mp3","Garymedband.mp3","Impro-Anne-Allez.mp3"}; 

	private int currentLecture=0;
	private int state;  //0:stop, 1:load, 2:play
	private float volume = 0.5f;
	private int position = 0;

	public MyPlayer(){
		player = null;
		state = 0;
	}

	public void Load(int i){
		if(state != 0)
			Stop();
		if(i<0)
			currentLecture = new Random().nextInt(listMusique.length);
		try{
			player = new LillePlayer(listMusique[currentLecture]);
			player.setVolume(volume);
			Equalizer eq = new Equalizer();
			eq.getBand(0);
			state = 1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void PlayPause(){
		if(state == 0){
			Load(currentLecture);
			PlayPause();
		}else if(state == 1){
			LaunchListenThread llt = new LaunchListenThread(player);
			llt.start();
			state = 2;
		}else if(state == 2){
			player.pause();
		}
	}

	public void Stop(){			
		if(state == 1 || state == 2){
			player.close();
			state = 0;
		}
	}

	public float getVolume(){
		return volume;
	}

	public void setVolume(float level){
		volume = level;
		player.setVolume(level);
	}

	public int getDuration(){
		if(player == null)
			return 0;
		return player.getDuration();
	}

	public LillePlayer getMediaPlayer(){
		return player;
	}

	public int getPosition(){
		return position;
	}

	public void setPosition(int pos){
		player.setPosition(pos);
		position = pos;
	}

	class LaunchListenThread extends Thread{
		private LillePlayer playerInterne;
		public LaunchListenThread(LillePlayer p){
			playerInterne = p;
		}
		public void run(){
			try{			
			//	System.out.println("LaunchEvent");
				PlayThread pt = new PlayThread();
				pt.start();

				while(!playerInterne.isComplete()){
					position = playerInterne.getPosition();
					if(player == playerInterne)
						//System.out.println("PositionEvent");
					try{
						Thread.sleep(200);
					}catch(Exception e){ e.printStackTrace(); }
				}

			//	if(player == playerInterne)
			//		System.out.println("EndEvent");
			}catch(Exception e){ e.printStackTrace(); }
		}

		class PlayThread extends Thread{
			public void run(){
				try {
					playerInterne.play();
				}catch(JavaLayerException e){ e.printStackTrace(); }
			}
		}
	}
/*
	public static void main( String[] args )
	{
		MyPlayer myPlayer = new MyPlayer();
		myPlayer.Load(-1);
		myPlayer.PlayPause();
	}	*/
}
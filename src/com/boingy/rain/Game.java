package com.boingy.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.boingy.rain.entity.mob.Player;
import com.boingy.rain.graphics.Screen;
import com.boingy.rain.graphics.SpriteSheet;
import com.boingy.rain.input.Keyboard;
import com.boingy.rain.input.Mouse;
import com.boingy.rain.level.Level;
import com.boingy.rain.level.TileCoord;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private boolean running = false;
	private String title = "Rain";
	private Level level;
	private Player player;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game()
	{
		Dimension size  = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Screen(width,height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		TileCoord playerSpawn = new TileCoord(25, 43); 
		player = new Player(playerSpawn.x, playerSpawn.y, key);
		level.add(player);
		
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	

	
	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWidnowHeight() {
		return height * scale;
	}
	
	public synchronized void start()
	{
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
				
	}
	
	public synchronized void stop()
	{
		running = false;
		try{
		thread.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta  = 0; 
		int frames = 0;
		int updates = 0;
		
		requestFocus();
		while(running)
		{
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while(delta >=1)
			{
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				frame.setTitle(this.title + "    |     "+ updates + "ups, fps:" + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public void update()
	{
		key.update();
		level.update();
 	}
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		int xScroll = player.getX() - screen.width / 2;
		int yScroll = player.getY() - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		//screen.renderSheet(40, 40, SpriteSheet.player_down, false);
		
		for(int i = 0; i < pixels.length; i++)
		{
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image , 0, 0,getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana",0, 50));
		//g.drawString("X: " + player.x + "Y: " + player.y, 350, 400);
		g.dispose();
		bs.show();
	}
	
public static void main(String[] args)
	{
	Game game = new Game();
	game.frame.setResizable(false);
	game.frame.setTitle(game.title);
	game.frame.add(game);
	game.frame.pack();
	game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	game.frame.setLocationRelativeTo(null);
	game.frame.setVisible(true);
	
	game.start();
	}
}
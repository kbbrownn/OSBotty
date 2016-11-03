package defaultPackage;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class main {
	
//	private static final class Lock { }
//	private final static Object lock = new Lock();
	
	static int counter = 5;
	static int minimizeDelay = 1;
	static int loopCount = 2;
	static javax.swing.Timer timer = null;
	static javax.swing.Timer botTimer = null;
	static javax.swing.Timer guiTimer = null;
	static int width = 0;
	static int height = 0;
	static boolean done = false;

	public static void main(String[] args) throws AWTException, IOException, InterruptedException {
		
		Robot mouse = new Robot();
		final JFrame frame = new JFrame("OSBotty");
		JLabel emptyLabel = new JLabel("");
	//	final JLabel firstLabel = new JLabel("<html>" + "Place OSBotty on the same screen as RuneScape and click next." + "</html>");
		final JLabel firstLabel = new JLabel ("Placeholder");
		URL url = new URL("http://static-cdn.jtvnw.net/jtv_user_pictures/chansub-global-emoticon-60aa1af305e32d49-23x30.png");
		BufferedImage icon = ImageIO.read(url);
		final JPanel pnlCheck = new JPanel();
		JButton btnCheck = new JButton("Next");
		JFrame mainFrame = new JFrame();
		final JLabel counterLabel = new JLabel("");
		final JPanel pnlCounter = new JPanel();
		
		// setup gui mainframe
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstLabel.setFont(firstLabel.getFont().deriveFont(20.0f));
		frame.getContentPane().add(firstLabel, BorderLayout.NORTH);
		frame.pack();
		frame.setIconImage(icon);
		frame.setSize(400, 400);
		
		// setup gui screen check
		pnlCheck.setLayout(new BorderLayout());
		pnlCheck.add(btnCheck, BorderLayout.SOUTH);
		frame.add(pnlCheck);
		btnCheck.setLocation(350, 350);
		btnCheck.setPreferredSize(new Dimension(50, 50));
		btnCheck.addActionListener(new ActionListener() {
			// user has clicked next signaling the bot to begin
			public void actionPerformed(ActionEvent e) {
				try {
					beginCountdown(frame, pnlCounter, counterLabel, pnlCheck, firstLabel);
					MyRunnable myRunnable = new MyRunnable();
					final Thread bt = new Thread(myRunnable);
					botTimer = new javax.swing.Timer(1000, new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			            	System.out.println("should be in bot");
			            } 
			        });
					botTimer.setInitialDelay(7500);
			        botTimer.start(); // begin bot

					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		pnlCheck.setLocation(350, 350);
		pnlCheck.setPreferredSize(new Dimension(50, 50));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// get current screen properties
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		width = gd.getDisplayMode().getWidth();
		height = gd.getDisplayMode().getHeight();	
	}

	
	/**
	 * Method to display simple gui countdown until bot begins
	 * @param frame - Mainframe with which to display to
	 * @param pnlCounter - Panel holding countdown
	 * @param counterLabel - Label that changes to display each second
	 * @param pnlCheck - Panel to check if user is ready to begin
	 * @param firstLabel - First label giving user instructions to begin
	 * @throws InterruptedException - Throw exception if process is interrupted mid execution
	 */
	public static void beginCountdown(final JFrame frame, JPanel pnlCounter, final JLabel counterLabel, JPanel pnlCheck, JLabel firstLabel) throws InterruptedException {
		
		//lock.wait();

		final Toolkit toolkit;
		
		pnlCounter.setLayout(new BorderLayout());
		counterLabel.setText("Bot is about to begin...");
		counterLabel.setFont(counterLabel.getFont().deriveFont(30.0f));
		counterLabel.setHorizontalAlignment(JLabel.CENTER);
		counterLabel.setVerticalAlignment(JLabel.CENTER);
		pnlCounter.add(counterLabel, BorderLayout.CENTER);
		frame.remove(pnlCheck);
		frame.getContentPane().remove(firstLabel);
		frame.add(pnlCounter);
		frame.revalidate();
		frame.repaint();
		
		toolkit = Toolkit.getDefaultToolkit();
	
		// actually perform gui change of text for each second
		timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(counter > -1) {
            		counterLabel.setText("" + counter);
            		counter--;
            	}
				else {
                	((Timer) e.getSource()).stop();
                	timer.stop();
            	}
            }
            
        });
        timer.start(); // begin countdown
        timer = new javax.swing.Timer(1000, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(minimizeDelay > -1) {
        			minimizeDelay--;
        		}
        		if(minimizeDelay == 0) {}
        		else {
        			frame.setState(Frame.ICONIFIED);
        			toolkit.beep();
        	        counterLabel.setText("Bot is currently running....");
        			frame.revalidate();
        			((Timer) e.getSource()).stop();
                	timer.stop();
        		}
        	}
        });
        timer.setInitialDelay(6000);
        timer.start();
		frame.revalidate();
		
		
		
		
        guiTimer = new javax.swing.Timer(1000, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(counter < Integer.MAX_VALUE) {
        			toolkit.beep();
        			System.out.println("" + counter);
        			guiLooper(counterLabel);
        			counter++;
        		}
        		else
        			counter = Integer.MIN_VALUE;
        	}
        });
        guiTimer.setInitialDelay(7000);
		guiTimer.start();
	
		
	}


	/**
	 * Method to loop the gui during bot execution to signal that it is still running
	 * @param counterLabel - Current JLabel being used for the gui during bot execution
	 */
	public static void guiLooper(final JLabel counterLabel) {
        switch(loopCount) {
           	case 2:
          		counterLabel.setText("Bot is currently running...");
           		loopCount--;
           		break;
          	case 1:
           		counterLabel.setText("Bot is currently running..");
           		loopCount--;
           		break;
           	case 0:
           		counterLabel.setText("Bot is currently running...");
           		loopCount--;
           		break;
           	case -1:
           		counterLabel.setText("Bot is currently running....");
           		loopCount--;
           		break;
           	default:
           		loopCount = 2;
           		break;
        }
           	
	}
	
	public static class MyRunnable implements Runnable {

		@Override
		public void run() {
			try {
				beginBot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void beginBot() throws AWTException {
		Robot robot = new Robot();
		int[][] screen = new int[width][height];
//		System.out.printf("Current screen position: %d x %d\n", width, height);
		long start = System.nanoTime();
		for(int i = 0; i < width; i+= 5) {
			for(int j = 0; j < height; j+= 5) {
				System.out.printf("Current screen position: %d x %d\n", i, j);
			}
		}
		long end = System.nanoTime();
		System.out.printf("time to do complete sweep: %d", (end - start) / 1000000000);
	}
           
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
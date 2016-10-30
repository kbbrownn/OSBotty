package defaultPackage;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class main {

	public static void main(String[] args) throws AWTException, IOException, InterruptedException {
		
		Robot mouse = new Robot();
		final JFrame frame = new JFrame("OSBotty");
		JLabel emptyLabel = new JLabel("");
		final JLabel firstLabel = new JLabel("<html>" + "Place OSBotty on the same screen as RuneScape and click next." + "</html>");
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
			public void actionPerformed(ActionEvent e) {
				try {
					beginCountdown(frame, pnlCounter, counterLabel, pnlCheck, firstLabel);
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
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		// setup countdown frame

	
		
//		for(i = 0; i < 10000; i++) {

//			mouse.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//			mouse.delay(5);
//			mouse.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

//		}
		
		
		
	}
	
	public static void beginCountdown(JFrame frame, JPanel pnlCounter, JLabel counterLabel, JPanel pnlCheck, JLabel firstLabel) throws InterruptedException {
		Timer timer = new Timer();
		
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
		
		
		
		
		counterLabel.setText("5");
		
		
		// begin countdown
		for(int i = 5; i >= 0; i--) {
			timer.wait(1, 200);
			counterLabel.setText("" + i);
			frame.revalidate();
		}
	}
	
	
	
}

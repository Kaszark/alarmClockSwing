package alarmClock;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class AlarmGUI extends JFrame {
	
//	GridBagLayout grid = new GridBagLayout();
	
	public AlarmGUI() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch(ClassNotFoundException ex) {
				}
				catch(InstantiationException ex) {
				}
				catch(IllegalAccessException ex) {
				}
				catch(UnsupportedLookAndFeelException ex) {
				}
				
				//GridBagLayout grid = new GridBagLayout();
				
				JFrame frame = new JFrame("Alarm Clock");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(new ClockPane(), BorderLayout.NORTH);
				frame.add(new AlarmSetting(), BorderLayout.CENTER);
				frame.setSize(150, 100);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}	
}

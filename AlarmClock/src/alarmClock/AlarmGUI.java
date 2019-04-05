package alarmClock;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

@SuppressWarnings("serial")
public class AlarmGUI extends JFrame {
	
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
				
				JFrame frame = new JFrame("Alarm Clock");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(new ClockPane());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}	
}



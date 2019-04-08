package alarmClock;

import java.awt.BorderLayout;
import java.util.Scanner;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
import java.awt.event.KeyListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class AlarmSetting extends JPanel{
	
	Scanner userIn = new Scanner(System.in);

	public AlarmSetting() {
		try
		{
			//setLayout(new BorderLayout());
			
			JButton set = new JButton("Create Alarm");
			add(set);
			set.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					JFrame alarmSet = new JFrame();
					alarmSet.setSize(325, 125);
					alarmSet.add(settingInfo());
//					alarmSet.pack();
					alarmSet.setVisible(true);
				}
			});
		}
		catch (Exception ex)
		{
			errorMsg(ex.getMessage());
		}
	}
	
	private void errorMsg(String er)
	{
		JOptionPane err = new JOptionPane(er, JOptionPane.ERROR_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = err.createDialog(err, "Error Occured Please Retry");
		
		dialog.setVisible(true);
	}
	
	public JPanel settingInfo() {
		JPanel p = new JPanel();
		
		JTextField month = new JTextField(4);
		JLabel m = new JLabel("Month", SwingConstants.LEFT);
		p.add(m);
		p.add(month);
				
		JTextField day = new JTextField(4);
		JLabel d = new JLabel("Day", SwingConstants.LEFT);
		p.add(d);
		p.add(day);
		
		JTextField year = new JTextField(4);
		JLabel y = new JLabel("Year", SwingConstants.LEFT);
		p.add(y);
		p.add(year);
				
		JTextField hour = new JTextField(4);
		JLabel h = new JLabel("Hour", SwingConstants.LEFT);
		p.add(h);
		p.add(hour);
				
		JTextField min = new JTextField(4);
		JLabel n = new JLabel("Minute", SwingConstants.LEFT);
		p.add(n);
		p.add(min);
				
		JTextField sec = new JTextField(4);
		JLabel s = new JLabel("Second", SwingConstants.LEFT);
		p.add(s);
		p.add(sec);
				
		JTextField mes = new JTextField(4);
		JLabel g = new JLabel("Message", SwingConstants.LEFT);
		p.add(g);
		p.add(mes);

		JButton set = new JButton("Set Alarm!");
		p.add(set, BorderLayout.CENTER);
		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Add CSV file creation here!!!!
			}
		});
		
		return p;
	}
	

}

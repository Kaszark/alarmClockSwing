package alarmClock;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import javax.swing.*;

@SuppressWarnings("serial")
public class AlarmSetting extends JPanel{

	public AlarmSetting() {
		try
		{
			//setLayout(new BorderLayout());
			
			JButton set = new JButton("Set Alarm");
			add(set);
			set.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					JFrame alarmSet = new JFrame();
					settingInfo();
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
	
	public void settingInfo() {
		JLabel lab = new JLabel("Test");
		add(lab);
	}
}

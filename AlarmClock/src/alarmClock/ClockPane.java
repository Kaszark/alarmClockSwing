package alarmClock;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ClockPane extends JPanel {
	private DigitPane month;
	private DigitPane day;
	private DigitPane year;
	private DigitPane hour;
	private DigitPane min;
	private DigitPane second;
	private JLabel[] seperator;
	
	private int tick = 0;
	
	public ClockPane() {
		try
		{
			Calendar cal = GregorianCalendar.getInstance();
			
			month = new DigitPane();
			day = new DigitPane();			
			int ytemp = cal.get(Calendar.YEAR);
			String plz = Integer.toString(ytemp);
			JLabel year = new JLabel(plz);
			hour = new DigitPane();
			min = new DigitPane();
			second = new DigitPane();
			seperator = new JLabel[] {new JLabel(":"), new JLabel(":")};
			
			add(month);
			add(day);
			add(year);
			
			add(hour);
			add(seperator[0]);
			add(min);
			add(seperator[1]);
			add(second);
			
			
			
			Timer timer = new Timer(500, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Calendar cal = GregorianCalendar.getInstance();
					
					month.setValue(cal.get(Calendar.MONTH) + 1);
					day.setValue(cal.get(Calendar.DATE));
					hour.setValue(cal.get(GregorianCalendar.HOUR_OF_DAY));
					min.setValue(cal.get(GregorianCalendar.MINUTE));
					second.setValue(cal.get(GregorianCalendar.SECOND));
					
					if(tick % 2 == 1) {
						seperator[0].setText(":");
						seperator[1].setText(":");
					}
					else {
						seperator[0].setText(":");
						seperator[1].setText(":");
					}
					tick++;
				}
			});
			timer.setRepeats(true);
			timer.setCoalesce(true);
			timer.start();
		}
		catch(Exception ex)
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
	
}

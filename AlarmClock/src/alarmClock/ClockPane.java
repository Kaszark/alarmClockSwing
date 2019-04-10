package alarmClock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ClockPane extends JPanel {
	private DigitPane month;
	private DigitPane day;
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
					
					ArrayList<String> values = new ArrayList<>();
					values.add(new Integer(month.getValue()).toString());
					values.add(new Integer(day.getValue()).toString());
					values.add(plz);
					values.add(new Integer(hour.getValue()).toString());
					values.add(new Integer(min.getValue()).toString());
					values.add(new Integer(second.getValue()).toString());
					alarmCompare(values);
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
	
	@SuppressWarnings("resource")
	private void alarmCompare(ArrayList<String> v) {
		try {
			FileReader file = new FileReader("Alarms.csv");
			Scanner scan = new Scanner(file);
			ArrayList<String> com = new ArrayList<>();
			com.equals(v);		
			while(scan.hasNext())
			{
				ArrayList<String> info = new ArrayList<>();
				String grab = scan.next();
				String[] items = grab.split(",");
				for(int i = 0; i < items.length-1; i++)
				{
					info.add(items[i]);
				}
				//System.out.println(info);
				//System.out.println(v);
				if(v.equals(info))
				{
					JOptionPane.showMessageDialog(null, items[6], "ALERT: ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

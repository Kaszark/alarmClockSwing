package alarmClock;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ClockPane extends JPanel {
	private DigitPane hour;
	private DigitPane min;
	private DigitPane second;
	private JLabel[] seperator;
	
	private int tick = 0;
	
	public ClockPane() {
		setLayout(new GridBagLayout());
		
		hour = new DigitPane();
		min = new DigitPane();
		second = new DigitPane();
		seperator = new JLabel[] {new JLabel(":"), new JLabel(":")};
		
		add(hour);
		add(seperator[0]);
		add(min);
		add(seperator[1]);
		add(second);
		
		Timer timer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				hour.setValue(cal.get(Calendar.HOUR_OF_DAY));
				min.setValue(cal.get(Calendar.MINUTE));
				second.setValue(cal.get(Calendar.SECOND));
				
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
	
}

package alarmClock;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
	private int counter = 0;
	
	private int tick = 0;

	public ClockPane() {
		try {
			Calendar cal = GregorianCalendar.getInstance();

			month = new DigitPane();
			day = new DigitPane();
			int ytemp = cal.get(Calendar.YEAR);
			String plz = Integer.toString(ytemp);
			JLabel year = new JLabel(plz);
			hour = new DigitPane();
			min = new DigitPane();
			second = new DigitPane();
			seperator = new JLabel[] { new JLabel(":"), new JLabel(":") };

			add(month);
			add(day);
			add(year);

			add(hour);
			add(seperator[0]);
			add(min);
			add(seperator[1]);
			add(second);

			Timer timer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Calendar cal = GregorianCalendar.getInstance();

					month.setValue(cal.get(Calendar.MONTH) + 1);
					day.setValue(cal.get(Calendar.DATE));
					hour.setValue(cal.get(GregorianCalendar.HOUR));
					min.setValue(cal.get(GregorianCalendar.MINUTE));
					second.setValue(cal.get(GregorianCalendar.SECOND));

					ArrayList<String> values = new ArrayList<>();
					values.add(new Integer(month.getValue()).toString());
					values.add(new Integer(day.getValue()).toString());
					values.add(plz);
					values.add(new Integer(hour.getValue()).toString());
					values.add(new Integer(min.getValue()).toString());
					values.add(new Integer(second.getValue()).toString());
					//System.out.println(values);
					alarmCompare(values);
					if (tick % 2 == 1) {
						seperator[0].setText(":");
						seperator[1].setText(":");
					} else {
						seperator[0].setText(":");
						seperator[1].setText(":");
					}
					tick++;
				}
			});
			timer.setRepeats(true);
			timer.setCoalesce(true);
			timer.start();
		} catch (Exception ex) {
			errorMsg(ex.getMessage());
		}
	}

	private void errorMsg(String er) {
		JOptionPane err = new JOptionPane(er, JOptionPane.ERROR_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = err.createDialog(err, "Error Occured Please Retry");

		dialog.setVisible(true);
	}

	@SuppressWarnings("resource")
	private void alarmCompare(ArrayList<String> v) {
		try {
			FileReader file = new FileReader("Alarms.csv");
			Scanner scan = new Scanner(file);
//			ArrayList<String> com = new ArrayList<>();
//			com.equals(v);
			//String headers = scan.nextLine();
			while (scan.hasNext()) {
				ArrayList<String> info = new ArrayList<>();
				String grab = scan.nextLine();
				String[] items = grab.split(",");
				JLabel mes = new JLabel();
				if(items.length == 7) {
					for (int i = 0; i < items.length - 1; i++) {
						info.add(items[i]);
						mes = new JLabel(items[6]);
					}
				}
				else {
					for (int i = 0; i < items.length; i++) {
						info.add(items[i]);
					}
				}
				//System.out.println(info);
				if (v.equals(info)) {
					
					JFrame alarm = new JFrame("Alarm");
					JButton dismiss = new JButton("Dismiss");
					Calendar calendar = GregorianCalendar.getInstance();
					alarm.setSize(150, 125);
					alarm.setLocationRelativeTo(getParent());
					alarm.setVisible(true);
					alarm.add(mes, BorderLayout.NORTH);
					alarm.add(dismiss, BorderLayout.WEST);
					JButton snooze = new JButton("Snooze");
					alarm.add(snooze, BorderLayout.EAST);
					JLabel counterLabel = new JLabel("Number of times Snoozed:" + counter);
					alarm.add(counterLabel, BorderLayout.SOUTH);
					
					int currentMonth = calendar.get(GregorianCalendar.MONTH);
					int currentDay = calendar.get(GregorianCalendar.DAY_OF_MONTH);
					int currentYear = calendar.get(GregorianCalendar.YEAR);
					int currentHour = calendar.get(GregorianCalendar.HOUR);
					int min = calendar.get(GregorianCalendar.MINUTE);
					int currentSec = calendar.get(GregorianCalendar.SECOND);
					dismiss.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							alarm.dispose();
							counter = 0;
						}
					});
					
					snooze.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								counter++;
								FileWriter write = new FileWriter("Alarms.csv", true);
								@SuppressWarnings("resource")
								BufferedWriter writer = new BufferedWriter(write);
								PrintWriter pw = new PrintWriter(writer);

								StringBuilder sb = new StringBuilder();
								sb.append(currentMonth + 1);
								sb.append(',');
								sb.append(currentDay);
								sb.append(',');
								sb.append(currentYear);
								sb.append(',');
								sb.append(currentHour);
								sb.append(',');
								sb.append(min + 1);
								sb.append(',');
								sb.append(currentSec);
								sb.append(',');
								pw.println(sb.toString()+"\n");
								//System.out.println(sb.toString());
								pw.close();
								alarm.dispose();

							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}

					}

					);

				}

			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

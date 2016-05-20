package view;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Adventure;
import model.City;
import model.TravelObserver;
import person.Person;

public class PersonView extends JPanel implements TravelObserver {

	private Person person;
	private JPanel panel;
	private JLabel label;

	public PersonView(Adventure a, Person p) {
		a.addTravelObserver(this);
		person = p;
		panel = new JPanel(new GridLayout(0, 1));
		label = new JLabel(person.toString());
		panel.add(label);
		add(panel);
	}

	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination,
			City destination) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				label.setText(person.toString());
			}
		});
	}
}
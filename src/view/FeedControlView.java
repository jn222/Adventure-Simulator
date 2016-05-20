package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Adventure;
import model.InTransitException;

public class FeedControlView extends JPanel implements ActionListener {
	private PersonView[] people;
	private Adventure a;
	private KnapsackView k;
	private JComboBox item, name;
	private JButton feed;
	private JLabel label;
	private ArrayList<String> food = new ArrayList<>();
	private String[] names;

	FeedControlView(Adventure a, KnapsackView k, PersonView p1, PersonView p2,
			PersonView p3, PersonView p4, PersonView p5) {
		this.a = a;
		this.k = k;
		people = new PersonView[] { p1, p2, p3, p4, p5 };
		names = new String[5];
		for (int i = 0; i < 5; i++) {
			names[i] = a.getSquad().getPlayer(i).getName();
		}
		name = new JComboBox(names);
		for (int i = 0; i < a.getSquad().getKnapsack().getEdibleSupplies().length; i++) {
			food.add(a.getSquad().getKnapsack().getEdibleSupplies()[i]
					.toString().substring(
							0,
							a.getSquad().getKnapsack().getEdibleSupplies()[i]
									.toString().indexOf("f" + 1)));
		}
		item = new JComboBox(food.toArray());
		feed = new JButton("Feed");
		label = new JLabel(" to ");
		feed.addActionListener(this);
		add(feed);
		add(item);
		add(label);
		add(name);
	}

	public void updateItems() {
		food.clear();
		for (int i = 0; i < a.getSquad().getKnapsack().getEdibleSupplies().length; i++) {
			food.add(a.getSquad().getKnapsack().getEdibleSupplies()[i]
					.toString().substring(a.getSquad().getKnapsack().getEdibleSupplies()[i]
					.toString().indexOf("f ") + 2, a.getSquad().getKnapsack().getEdibleSupplies()[i]
							.toString().length()));
		}
		item.setModel(new DefaultComboBoxModel(food.toArray()));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		a.getSquad()
				.feed(a.getSquad().getKnapsack().getEdibleSupplies()[item
						.getSelectedIndex()],
						a.getSquad().getPlayer(name.getSelectedIndex()));
		updateItems();
		
		try {
			k.travelUpdate(a, a.getCurrentCity()
					.getDistance(a.getCurrentCity()), null);
			for (PersonView p : people) {
				p.travelUpdate(a,
						a.getCurrentCity().getDistance(a.getCurrentCity()),
						null);
			}
		} catch (InTransitException e) {
			JOptionPane
					.showMessageDialog(null, "You are travelling right now.");
		}
	}

}

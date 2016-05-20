package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import supplies.Food;
import model.Adventure;
import model.City;
import model.Knapsack;
import model.TravelObserver;

public class KnapsackView extends JPanel implements TravelObserver {

	private JPanel panel;
	private Knapsack k;
	private ArrayList<JLabel> supplies = new ArrayList<>();
	private JScrollPane scroll;

	public KnapsackView(Knapsack k, Adventure a) {
		this.k = k;
		a.addTravelObserver(this);
		scroll = new JScrollPane();
		panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel("knapsack:"));

		for (int i = 0; i < k.getSupplies().length; i++) {
			supplies.add(new JLabel(k.getSupplies()[i].toString()));
			panel.add(supplies.get(i));
	}
		scroll.getViewport().add(panel);
		scroll.createVerticalScrollBar();
		add(scroll);
	}

	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination,
			City destination) {
		panel.removeAll();
		supplies.clear();
		panel.add(new JLabel("knapsack:"));
		for (int i = 0; i < k.getSupplies().length; i++) {
				supplies.add(new JLabel(k.getSupplies()[i].toString()));
				panel.add(supplies.get(i));
		}
	}
}

package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import supplies.Supplies;
import model.Adventure;
import model.City;
import model.InTransitException;
import model.ItemNotForSaleException;
import model.TravelObserver;

public class StoreControlView extends JPanel implements ActionListener,
		TravelObserver {

	private Adventure a;
	public JComboBox store_list;
	private JLabel price;
	private String[] supplies;
	public JTextField text;
	public String selected;

	public StoreControlView(Adventure a) {
		try {
			this.a = a;
			supplies = a.getCurrentCity().getStore().getItemNames();
			JLabel label = new JLabel("Buy ");
			add(label);
			text = new JTextField("", 4);
			add(text);
			JLabel label2 = new JLabel(" of ");
			add(label2);
			store_list = new JComboBox(supplies);
			store_list.addActionListener(this);
			add(store_list);
			price = new JLabel("$"
					+ a.getCurrentCity().getStore()
							.getPrice((String) store_list.getSelectedItem()));
			add(price);
		} catch (ItemNotForSaleException e) {
			price.setText("Item not available.");
		} catch (InTransitException e) {
			price.setText("Currently Travelling.");
		}
		selected = (String) store_list.getSelectedItem();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			price.setText("$"
					+ a.getCurrentCity().getStore()
							.getPrice((String) store_list.getSelectedItem()));
		} catch (ItemNotForSaleException e) {
			price.setText("Item not for sale");
		} catch (InTransitException e) {
			price.setText("Currently Travelling.");
		}
		selected = (String) store_list.getSelectedItem();
	}

	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination,
			City destination) {
		if (!a.isTravelling()) {
			store_list.setModel(new DefaultComboBoxModel(destination.getStore().getItemNames()));
		}
	}
}

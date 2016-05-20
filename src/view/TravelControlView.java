package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Adventure;
import model.City;

public class TravelControlView extends JPanel implements ActionListener {

	private Adventure a;
	private JComboBox city_list;
	
	public TravelControlView(Adventure a){
		this.a = a;
		City [] cityarr = a.getCities();
		JLabel label = new JLabel("Travel to: ");
		add(label);
		city_list = new JComboBox(cityarr);
		city_list.addActionListener(this);
		add(city_list);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		a.travel((City) city_list.getSelectedItem());
	}

}

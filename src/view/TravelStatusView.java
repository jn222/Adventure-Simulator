package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Adventure;
import model.City;
import model.InTransitException;
import model.TravelObserver;

public class TravelStatusView extends JPanel implements TravelObserver {
	
	public JLabel status_label;
	private Adventure a;
	public TravelStatusView(Adventure a){
		a.addTravelObserver(this);
		this.a = a;
		status_label = new JLabel();
		try{
			String city = a.getCurrentCity().getName();
			status_label.setText(city);
		} catch (InTransitException e){
			status_label.setText("I am traveling!");
		}
		add(status_label);
	}
	
	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination,
			City destination) {
		if(distance_to_destination == 0){
			status_label.setText("Arrived at " + destination.getName());
		}
		
	}
	
}

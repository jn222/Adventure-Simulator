package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import person.Person;
import model.Adventure;
import model.InTransitException;
import model.ItemNotForSaleException;
import model.Knapsack;
import model.Squad;

public class AdventureView extends JPanel {

	private Adventure adventure;
	
	public AdventureView(Adventure adventure){
		this.adventure = adventure;
		
		setLayout(new BorderLayout());
		
		Squad s = adventure.getSquad();
		Knapsack knapsack = s.getKnapsack();

		KnapsackView k = new KnapsackView(knapsack, adventure);
		Person p1 = s.getPlayer(0);
		PersonView pv1 = new PersonView(adventure, p1);
		Person p2 = s.getPlayer(1);
		PersonView pv2 = new PersonView(adventure, p2);
		Person p3 = s.getPlayer(2);
		PersonView pv3 = new PersonView(adventure, p3);
		Person p4 = s.getPlayer(3);
		PersonView pv4 = new PersonView(adventure, p4);
		Person p5 = s.getPlayer(4);
		PersonView pv5 = new PersonView(adventure, p5);
		JPanel west = new JPanel(new GridLayout(0,1));
		JPanel east = new JPanel(new GridLayout(0,1));
		pv1.setBorder(BorderFactory.createLoweredBevelBorder());
		pv2.setBorder(BorderFactory.createLoweredBevelBorder());
		pv3.setBorder(BorderFactory.createLoweredBevelBorder());
		pv4.setBorder(BorderFactory.createLoweredBevelBorder());
		pv5.setBorder(BorderFactory.createLoweredBevelBorder());
		west.add(pv1);
		west.add(pv2);
		west.add(pv3);
		west.add(pv4);
		west.add(pv5);
		TravelControlView travel_view = new TravelControlView(adventure);
		TravelStatusView travel_status = new TravelStatusView(adventure);
		StoreControlView store_view = new StoreControlView(adventure);
		FeedControlView feed_view = new FeedControlView(adventure, k, pv1, pv2, pv3, pv4, pv5);
		BuyControlView buy_view = new BuyControlView(adventure,k,store_view, feed_view);
		west.add(travel_view);
		west.add(travel_status);
		east.add(k);
		east.add(store_view);
		east.add(buy_view);
		east.add(feed_view);
		add(west, BorderLayout.WEST);
		add(east, BorderLayout.EAST);
	}
}

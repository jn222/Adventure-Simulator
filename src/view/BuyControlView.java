package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Adventure;
import model.InTransitException;
import model.InsufficientFundsException;
import model.ItemNotForSaleException;

public class BuyControlView extends JPanel implements ActionListener {
	
	private JButton button;
	private Adventure adventure;
	private JLabel balance;
	private KnapsackView k;
	private StoreControlView s;
	private FeedControlView f;
	public BuyControlView(Adventure a, KnapsackView k, StoreControlView s, FeedControlView f){
		this.s=s;
		adventure = a;
		this.k=k;
		this.f = f;
		button = new JButton("Place order");
		button.addActionListener(this);
		add(button);
		balance = new JLabel("$" + Double.toString(a.getSquad().getBalance()));
		add(balance);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			adventure.getSquad().purchaseSupply((String)(s.selected), Integer.parseInt(s.text.getText()), adventure.getCurrentCity().getStore());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
		} catch (ItemNotForSaleException e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid item.");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid quantity.");
		} catch (InsufficientFundsException e) {
			JOptionPane.showMessageDialog(null, "You don't have enough money to buy that.");
		} catch (InTransitException e) {
			JOptionPane.showMessageDialog(null, "You are travelling right now.");
		}
		balance.setText("$" + Double.toString(adventure.getSquad().getBalance()));
		try {
			k.travelUpdate(adventure, adventure.getCurrentCity().getDistance(adventure.getCurrentCity()), null);
			f.updateItems();
		} catch (InTransitException e) {
			JOptionPane.showMessageDialog(null, "You are travelling right now.");
		}
	}

}

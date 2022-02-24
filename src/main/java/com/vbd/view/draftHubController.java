package com.vbd.view;

import com.vbd.App;
import com.vbd.model.DraftMode;
import com.vbd.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class draftHubController {
	
	DraftMode d;
	private App main;
	
	public draftHubController(DraftMode d){
		this.d = d;
	}
	
	@FXML
	private ListView<Player> draftedPlayers;
	@FXML
	private ListView<Player> availablePlayers;
	@FXML
	private Label pickNum;
	@FXML
	private Label qbLabel;
	@FXML
	private Label rbLabel1;
	@FXML
	private Label rbLabel2;
	@FXML
	private Label wrLabel1;
	@FXML
	private Label wrLabel2;
	@FXML
	private Label teLabel;
	@FXML
	private Label flexLabel;
	@FXML
	private Label defLabel;
	@FXML
	private Label kLabel;
	@FXML
	private Label bench1;
	@FXML
	private Label bench2;
	@FXML
	private Label bench3;
	@FXML
	private Label bench4;
	@FXML
	private Label bench5;
	@FXML
	private Label bench6;
	@FXML
	private Button back;
	@FXML
	private TextField searchBar;
	@FXML
	private Button search;
	
	ObservableList<Player> availablePlayersL = FXCollections.observableArrayList();
	ObservableList<Player> draftedPlayersL = FXCollections.observableArrayList();
	ObservableList<Player> test = FXCollections.observableArrayList();
	
	ObservableList<Player> addPlayers(ObservableList<Player> oL) {
		for (Player p : d.undrafted){
			if (!p.drafted)
			oL.add(p);
		}
		return oL;
		
	}

	@FXML
	void setTeamLabels() {
		if ((!(d.myTeam.bench.size() > 0) && (!(d.myTeam.starters.size() ==9)))) {
			for (Player p : d.myTeam.starters) {
				if (p.isQB()) {
					qbLabel.setText(p.name);
				}
				if (p.isRB()) {
					if (this.d.myTeam.numRB() == 1) {
						rbLabel1.setText(p.name);
					}
					if (this.d.myTeam.numRB() == 2) {
						rbLabel2.setText(p.name);
					}
				}
				if (p.isWR()) {
					if (this.d.myTeam.numWR() == 1) {
						wrLabel1.setText(p.name);
					}
					if (this.d.myTeam.numWR() == 2) {
						wrLabel2.setText(p.name);
					}
				}
				if (p.isTE()) {
					teLabel.setText(p.name);
				}
				if (p.isDEF()) {
					defLabel.setText(p.name);
				}
				if (p.isK()) {
					kLabel.setText(p.name);
				}
				if (p.isRB() && this.d.myTeam.numRB() > 2 && this.d.myTeam.numFlex() == 1
						|| (p.isWR() && this.d.myTeam.numWR() > 2 && this.d.myTeam.numFlex() == 1)) {
					flexLabel.setText(p.name);
				}

			}
		}
		for (int i = 0; i < d.myTeam.bench.size(); i++) {
			if (i == 0) {
				bench1.setText(d.myTeam.bench.get(i).name);
			}
			if (i == 1) {
				bench2.setText(d.myTeam.bench.get(i).name);
			}
			if (i == 2) {
				bench3.setText(d.myTeam.bench.get(i).name);
			}
			if (i == 3) {
				bench4.setText(d.myTeam.bench.get(i).name);
			}
			if (i == 4) {
				bench5.setText(d.myTeam.bench.get(i).name);
			}
			if (i == 5) {
				bench6.setText(d.myTeam.bench.get(i).name);
			}
		}
	}

	
	
	@FXML
	private void initialize() {
		availablePlayersL = addPlayers(availablePlayersL);
		availablePlayers.setItems(availablePlayersL);
		draftedPlayers.setItems(draftedPlayersL);
		if (this.d.league.pickNum == this.d.league.draftPickNum) {
			pickNum.setText("Submit Your Pick");
		}
	}
	@FXML
	private void remove() {
		int idx = availablePlayers.getSelectionModel().getSelectedIndex();
		if(idx>=0) {
			this.d.draft(availablePlayersL.get(idx));
			draftedPlayersL.add(availablePlayersL.get(idx));
		}
		if (this.d.league.pickNum == this.d.league.draftPickNum) {
			pickNum.setText("Submit Your Pick");
		}
		else {
		pickNum.setText("Submit Pick #" + this.d.league.draftPickNum);
		}
		ObservableList<Player> temp =  FXCollections.observableArrayList();
		addPlayers(temp);
		availablePlayersL = temp;
		availablePlayers.setItems(availablePlayersL);
		setTeamLabels();
	}
	@FXML
	private void searchPlayers() {
		String keyword = searchBar.getText().toUpperCase();
		ObservableList<Player> temp = FXCollections.observableArrayList();
		for(Player p: this.availablePlayersL) {
			if(p.name.toUpperCase().startsWith(keyword)) {
				temp.add(p);
			}
		}
		availablePlayersL = temp;
		availablePlayers.setItems(availablePlayersL);
	}
	@FXML
	private void back() {
		main.showDraft();
	}

}

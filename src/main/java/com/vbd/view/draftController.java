package com.vbd.view;

import java.io.IOException;
import java.util.ArrayList;

import com.vbd.App;
import com.vbd.model.DraftMode;
import com.vbd.model.League;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class draftController {
	ObservableList<Integer> pickNumList8 = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8);
	ObservableList<Integer> pickNumList10 = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	ObservableList<Integer> pickNumList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
	ObservableList<Integer> pickNumList14 = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14);
	ObservableList<Integer> numTeamsList = FXCollections.observableArrayList(8, 10, 12, 14);
	ObservableList<Integer> qbNumList = FXCollections.observableArrayList(1);
	ObservableList<Integer> rbNumList = FXCollections.observableArrayList(2);
	ObservableList<Integer> wrNumList = FXCollections.observableArrayList(2);
	ObservableList<Integer> benchNumList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	ObservableList<Integer> passYdsPerPtList = FXCollections.observableArrayList(5, 10, 15, 20, 25, 30);
	ObservableList<Integer> TDList = FXCollections.observableArrayList(4, 6);
	ObservableList<Integer> pointsPerIntList = FXCollections.observableArrayList(0, -1, -2);
	ObservableList<? extends Number> pointsPerRecList = FXCollections.observableArrayList(0.0, 0.5, 1.0);
	ObservableList<Integer> pointsPerTwoPtConList = FXCollections.observableArrayList(1, 2, 3, 4, 5);

	private App main;
	DraftMode dh;

	@FXML
	private ChoiceBox<Integer> pickNum;
	@FXML
	private ChoiceBox<Integer> numTeams;
	@FXML
	private ChoiceBox<Integer> qbNum;
	@FXML
	private ChoiceBox<Integer> rbNum;
	@FXML
	private ChoiceBox<Integer> wrNum;
	@FXML
	private ChoiceBox<Integer> teNum;
	@FXML
	private ChoiceBox<Integer> flexNum;
	@FXML
	private ChoiceBox<Integer> benchNum;
	@FXML
	private ChoiceBox<Integer> passYdsPerPt;
	@FXML
	private ChoiceBox<Integer> passTD;
	@FXML
	private ChoiceBox<Integer> pointsPerInt;
	@FXML
	private ChoiceBox<Integer> rushYdsPerPt;
	@FXML
	private ChoiceBox<Integer> rushTD;
	@FXML
	private ChoiceBox<Double> pointsPerRec;
	@FXML
	private ChoiceBox<Integer> recYdsPerPt;
	@FXML
	private ChoiceBox<Integer> pointsPerRecTD;
	@FXML
	private ChoiceBox<Integer> pointsPerFumble;
	@FXML
	private ChoiceBox<Integer> pointsPerTwoPtCon;
	@FXML
	private ChoiceBox<Integer> pointsPerSack;
	@FXML
	private ChoiceBox<Integer> pointsPerDEFInt;
	@FXML
	private ChoiceBox<Integer> pointsPerFumbleRec;
	@FXML
	private ChoiceBox<Integer> pointsPerSafety;
	@FXML
	private ChoiceBox<Integer> pointsPerDefTD;
	@FXML
	private ChoiceBox<Integer> pointsPerRetTD;
	@FXML
	private Button enter;

	@FXML
	private void initialize() {
		pickNum.setItems(pickNumList);
		pickNum.setValue(1);
		numTeams.setItems(numTeamsList);
		numTeams.setValue(12);
		qbNum.setItems(qbNumList);
		qbNum.setValue(1);
		rbNum.setItems(rbNumList);
		rbNum.setValue(2);
		wrNum.setItems(wrNumList);
		wrNum.setValue(2);
		teNum.setItems(qbNumList);
		teNum.setValue(1);
		flexNum.setItems(qbNumList);
		flexNum.setValue(1);
		benchNum.setItems(benchNumList);
		benchNum.setValue(6);
		passYdsPerPt.setItems(passYdsPerPtList);
		passYdsPerPt.setValue(25);
		passTD.setItems(TDList);
		passTD.setValue(6);
		pointsPerInt.setItems(pointsPerIntList);
		pointsPerInt.setValue(-2);
		rushYdsPerPt.setItems(passYdsPerPtList);
		rushYdsPerPt.setValue(10);
		rushTD.setItems(TDList);
		rushTD.setValue(6);
		pointsPerRec.setItems((ObservableList<Double>) pointsPerRecList);
		pointsPerRec.setValue(0.0);
		recYdsPerPt.setItems(passYdsPerPtList);
		recYdsPerPt.setValue(10);
		pointsPerRecTD.setItems(TDList);
		pointsPerRecTD.setValue(6);
		pointsPerFumble.setItems(pointsPerIntList);
		pointsPerFumble.setValue(-2);
		pointsPerTwoPtCon.setItems(pointsPerTwoPtConList);
		pointsPerTwoPtCon.setValue(2);
		pointsPerSack.setItems(rbNumList);
		pointsPerSack.setValue(1);
		pointsPerDEFInt.setItems(rbNumList);
		pointsPerDEFInt.setValue(2);
		pointsPerFumbleRec.setItems(rbNumList);
		pointsPerFumbleRec.setValue(2);
		pointsPerSafety.setItems(pointsPerTwoPtConList);
		pointsPerSafety.setValue(2);
		pointsPerDefTD.setItems(TDList);
		pointsPerDefTD.setValue(6);
		pointsPerRetTD.setItems(TDList);
		pointsPerRetTD.setValue(6);
	}

	public void addDraftMode(DraftMode d) {
		this.dh = d;
	}

	@FXML
	void setPickOptions() {
		if (numTeams.getValue() == 8) {
			pickNum.setItems(pickNumList8);
			pickNum.setValue(1);
		}
		if (numTeams.getValue() == 10) {
			pickNum.setItems(pickNumList10);
			pickNum.setValue(1);
		}
		if (numTeams.getValue() == 12) {
			pickNum.setItems(pickNumList);
			pickNum.setValue(1);
		}
		if (numTeams.getValue() == 14) {
			pickNum.setItems(pickNumList14);
			pickNum.setValue(1);
		}
	}

	@FXML
	private void enterSettings() throws IOException {
		// main.createLeague(pickNum.getValue(), numeams.getValue(), qbNum.getValue(),
		// rbNum.getValue(),wrNum.getValue());
		League l1 = new League(1, pickNum.getValue(), numTeams.getValue(), qbNum.getValue(), rbNum.getValue(),
				wrNum.getValue(), teNum.getValue(), flexNum.getValue(), 1, 1, benchNum.getValue(),
				qbNum.getValue() + rbNum.getValue() + wrNum.getValue() + teNum.getValue() + flexNum.getValue() + 2
						+ benchNum.getValue(),
				1.0 / passYdsPerPt.getValue(), passTD.getValue(), pointsPerInt.getValue(),
				1.0 / rushYdsPerPt.getValue(), rushTD.getValue(), pointsPerRec.getValue(), 1.0 / recYdsPerPt.getValue(),
				pointsPerRecTD.getValue(),
				pointsPerFumble.getValue(), pointsPerTwoPtCon.getValue(), 1, 3, pointsPerSack.getValue(),
				pointsPerDEFInt.getValue(), pointsPerFumbleRec.getValue(), pointsPerSafety.getValue(),
				pointsPerDefTD.getValue(), pointsPerRetTD.getValue(),
				10.0, 8.0, 6.0, 2.0, 1.0, 0.0);
		// System.out.print(l1.passPtsPerYd);
		// main.createDraftMode(l1);
		main.showDraftHub(l1);
	}

}

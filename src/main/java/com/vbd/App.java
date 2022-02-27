package com.vbd;

import javafx.application.Application;
import java.io.IOException;

import com.vbd.model.*;
import com.vbd.view.draftHubController;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList; 

public class App extends Application {

    private Stage primaryStage;
    private static BorderPane rootLayout;
    DraftMode d;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Value Based Drafting Tool");

        initRootLayout();

        showDraft();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public static void showDraft() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/draft.fxml"));
            AnchorPane draft = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(draft);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void showDraftHub(League l) {
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            draftHubController controller = new draftHubController(createDraftMode(l));
            loader.setLocation(App.class.getResource("/draftHub.fxml"));
            loader.setController(controller);
            BorderPane draftHub = (BorderPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(draftHub);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
//    public void createLeague() {
//    	League l1 = new League(pickNum, numTeams, qbNum, rbNum, wrNum);
//    	System.out.print(l1.pickNum);
//    }
    public static DraftMode createDraftMode (League l) throws IOException {
    	DraftMode tempD = new DraftMode();
    	ArrayList<Player> tempU = new ArrayList<Player>();
    	//League tempL = new League(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Document doc = Jsoup.connect("https://www.fantasypros.com/nfl/projections/qb.php?week=draft").get();
        //String title = doc.title();  
        //System.out.println("title is: " + title);  
        int i = 0;
        int pass = 2;
        while(i<35) {
        	String name = doc.select("td.player-label").get(i).text();
        	String passYds = doc.select("td.center").get(pass).text();
        	int comma = passYds.indexOf(',');
        	if(comma >0) {
        		passYds = passYds.substring(0, comma) + passYds.substring(comma+1, passYds.length());
        	}
        	double passYdsd = Double.parseDouble(passYds);
        	double passTds = Double.parseDouble(doc.select("td.center").get(pass + 1).text());
        	double passInts = Double.parseDouble(doc.select("td.center").get(pass + 2).text());
        	double rushYds = Double.parseDouble(doc.select("td.center").get(pass + 4).text());
        	double rushTds = Double.parseDouble(doc.select("td.center").get(pass + 5).text());
        	double fumbles = Double.parseDouble(doc.select("td.center").get(pass + 6).text());
        	//System.out.println(name + " " + passYdsd + " " + passTds + " " + passInts + " " + rushYds + " " + rushTds + " " + fumbles);
        	tempU.add(tempD.createUndraftedQB(name, l, passYdsd, passTds, rushYds, rushTds, passInts, fumbles, 0.0));
        	i++;
        	pass +=10;
        }
        Document docRB = Jsoup.connect("https://www.fantasypros.com/nfl/projections/rb.php?week=draft").get();
        int h = 0;
        int rush = 1;
        while(h<70) {
        	String name = docRB.select("td.player-label").get(h).text();
        	String rushYds = docRB.select("td.center").get(rush).text();
        	int comma = rushYds.indexOf(',');
        	if(comma >0) {
        		rushYds = rushYds.substring(0, comma) + rushYds.substring(comma+1, rushYds.length());
        	}
        	double rushYdsd = Double.parseDouble(rushYds);
        	double rushTds = Double.parseDouble(docRB.select("td.center").get(rush + 1).text());
        	double recs = Double.parseDouble(docRB.select("td.center").get(rush + 2).text());
        	double recYds = Double.parseDouble(docRB.select("td.center").get(rush + 3).text());
        	double recTds = Double.parseDouble(docRB.select("td.center").get(rush + 4).text());
        	double fumbles = Double.parseDouble(docRB.select("td.center").get(rush + 5).text());
        	//System.out.println(name + " " + rushYdsd + " " + rushTds + " " + recs + " " + recYds + " " + recTds + " " + fumbles);
        	tempU.add(tempD.createUndraftedRB(name, l, rushYdsd, rushTds, recs, recYds, recTds, fumbles, 0 ));
        	h++;
        	rush +=8;
        }
        Document docWR = Jsoup.connect("https://www.fantasypros.com/nfl/projections/wr.php?week=draft").get();
        int x = 0;
        int receptions = 1;
        while(x<70) {
        	String name = docWR.select("td.player-label").get(x).text();
        	double rushYds = Double.parseDouble(docWR.select("td.center").get(receptions + 3).text()); 
        	double rushTds = Double.parseDouble(docWR.select("td.center").get(receptions + 4).text());
        	double recs = Double.parseDouble(docWR.select("td.center").get(receptions - 1).text());
        	String recYds = docWR.select("td.center").get(receptions).text();
        	int comma = recYds.indexOf(',');
        	if(comma >0) {
        		recYds = recYds.substring(0, comma) + recYds.substring(comma+1, recYds.length());
        	}
        	double recYdsd = Double.parseDouble(recYds);
        	double recTds = Double.parseDouble(docWR.select("td.center").get(receptions + 1).text());
        	double fumbles = Double.parseDouble(docWR.select("td.center").get(receptions + 5).text());
        	//System.out.println(name + " " + recs + " " + recYdsd + " " + recTds + " " + rushYds + " " + rushTds + " " + fumbles);
        	tempU.add(tempD.createUndraftedWR(name, l, rushYds, rushTds, recs, recYdsd, recTds, fumbles, 0 ));
        	x++;
        	receptions +=8;
        }
        Document docTE = Jsoup.connect("https://www.fantasypros.com/nfl/projections/te.php?week=draft").get();
        int t = 0;
        int receptionsT = 0;
        while(t<40) {
        	String name = docTE.select("td.player-label").get(t).text();
        	double rushYds = 0;
        	double rushTds = 0;
        	double recs = Double.parseDouble(docTE.select("td.center").get(receptionsT).text());
        	String recYds = docTE.select("td.center").get(receptionsT + 1).text();
        	int comma = recYds.indexOf(',');
        	if(comma >0) {
        		recYds = recYds.substring(0, comma) + recYds.substring(comma+1, recYds.length());
        	}
        	double recYdsd = Double.parseDouble(recYds);
        	double recTds = Double.parseDouble(docTE.select("td.center").get(receptionsT + 2).text());
        	double fumbles = Double.parseDouble(docTE.select("td.center").get(receptionsT + 3).text());
        	//System.out.println(name + " " + rushYds + " " + rushTds + " " + recs + " " + recYdsd + " " + recTds + " " + fumbles);
        	tempU.add(tempD.createUndraftedTE(name, l, rushYds, rushTds, recs, recYdsd, recTds, fumbles, 0 ));
        	t++;
        	receptionsT +=5;
        }
        Document docDEF = Jsoup.connect("http://www.fftoday.com/rankings/playerproj.php?PosID=99&LeagueID=1").get();
    	int r = 0;
    	int defIdx = 1;
    	while (r < 20) {
    		String name = docDEF.select("td.sort1").get(defIdx).text();
    		double sack = Double.parseDouble(docDEF.select("td.sort1").get(defIdx + 2).text());
    		double fumRec = Double.parseDouble(docDEF.select("td.sort1").get(defIdx+ 3).text());
    		double ints = Double.parseDouble(docDEF.select("td.sort1").get(defIdx + 4).text());
    		double defTD = Double.parseDouble(docDEF.select("td.sort1").get(defIdx + 5).text());
    		double ptsAllowed = Double.parseDouble(docDEF.select("td.sort1").get(defIdx + 6).text());
    		double safety = Double.parseDouble(docDEF.select("td.sort1").get(defIdx + 9).text());
    		double retTD = Double.parseDouble(docDEF.select("td.sort1").get(defIdx + 10).text());
    		//System.out.println(name + " " + sack + " " + fumRec + " " + ints + " " + defTD + " " + ptsAllowed + " " + safety + " " + retTD);
    		defIdx += 13;
    		r++;
    		tempU.add(tempD.createUndraftedDEF(name, l, sack, ints, fumRec, safety, defTD, retTD, ptsAllowed));
    	}
        Document docK = Jsoup.connect("https://www.fantasypros.com/nfl/projections/k.php?week=draft").get();
        int k = 0;
        int indxK = 0;
        while(k<20) {
        	String name = docK.select("td.player-label").get(k).text();
        	double fg = Double.parseDouble(docK.select("td.center").get(indxK).text());
        	double xPT = Double.parseDouble(docK.select("td.center").get(indxK + 2).text());
        	//System.out.println(name + " " + fg + " " +  xPT);
        	k++;
        	indxK = indxK + 4;
        	tempU.add(tempD.createUndraftedK(name, l, xPT, fg));
        }
        return new DraftMode(tempU, l, new Team(0,0,0,0,0,0,0));
    }

    public static void main(String[] args) throws IOException {

        launch(args);
    }
}

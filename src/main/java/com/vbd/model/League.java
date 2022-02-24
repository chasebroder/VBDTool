package com.vbd.model;

public class League {
	public int draftPickNum; // your pick
	public int pickNum;
	public int numTeams;
	public int qbNum;
	public int rbNum;
	public int wrNum;
	public int teNum;
	public int flexNum;
	public int kickNum;
	public int defNum;
	public int benchNum;
	public int rosterNum;
	public Double passPtsPerYd;
	public double passTD;
	public double passInt;
	public Double rushPtsPerYd;
	public double rushTD;
	public Double pointsPerReception;
	public Double recPtsPerYD;
	public double recTD;
	public double fumble;
	public double twoPtCon;
	public double PATMade;
	public double FGMade;
	public double sack;
	public double defInt;
	public double defFumble;
	public double safety;
	public double defTD;
	public double retTD;
	public double ptAllowed0;
	public double ptAllowed713;
	public double ptAllowed1420;
	public double ptAllowed2127;
	public double ptAllowed2834;
	public double ptAllowed35;

	public League(int draftPickNum, int pickNum, int numTeams,  int qbNum, int rbNum, int wrNum, int teNum, int flexNum,
			int kickNum, int defNum, int benchNum, int rosterNum, Double passPtsPerYd, double passTD, double passInt,
			Double rushPtsPerYd, double rushTD, Double pointsPerReception, Double recPtsPerYD, double recTD,
			double fumble, double twoPtCon, double PATMade, double FGMade, double sack, double defInt,
			double defFumble, double safety, double defTD, double retTD, double ptAllowed0, double ptAllowed713,
			double ptAllowed1420, double ptAllowed2127, double ptAllowed2834, double ptAllowed35) {
		this.draftPickNum = draftPickNum;
		this.numTeams = numTeams;
		this.pickNum = pickNum;
		this.qbNum = qbNum;
		this.rbNum = rbNum;
		this.wrNum = wrNum;
		this.teNum = teNum;
		this.flexNum = flexNum;
		this.kickNum = kickNum;
		this.defNum = defNum;
		this.benchNum = benchNum;
		this.rosterNum = rosterNum;
		this.passPtsPerYd = passPtsPerYd;
		this.passTD = passTD;
		this.passInt = passInt;
		this.rushPtsPerYd = rushPtsPerYd;
		this.rushTD = rushTD;
		this.pointsPerReception = pointsPerReception;
		this.recPtsPerYD = recPtsPerYD;
		this.recTD = recTD;
		this.fumble = fumble;
		this.twoPtCon = twoPtCon;
		this.PATMade = PATMade;
		this.FGMade = FGMade;
		this.sack = sack;
		this.defInt = defInt;
		this.defFumble = defFumble;
		this.safety = safety;
		this.defTD = defTD;
		this.retTD = retTD;
		this.ptAllowed0 = ptAllowed0;
		this.ptAllowed713 = ptAllowed713;
		this.ptAllowed1420 = ptAllowed1420;
		this.ptAllowed2127 = ptAllowed2127;
		this.ptAllowed2834 = ptAllowed2834;
		this.ptAllowed35 = ptAllowed35;

	}

}

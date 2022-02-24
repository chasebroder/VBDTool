package com.vbd.model;

import java.util.ArrayList;

public class Team {
  public ArrayList<Player> roster; // list of players on your team
  public ArrayList<Player> starters; // list of starters on your team
  public ArrayList<Player> bench; // list of players on your bench
  int qbs; // number of quarterbacks on your team
  int rbs; // number of running backs on your team
  int wrs; // number of wide receivers on your team
  int tes; // number of tight ends on your team
  int flex; // number of flexes on your team
  int k; // number of kickers on your team
  int def; // number of defenses on your team

  // for testing
  public Team(int qbs, int rbs, int wrs, int tes, int flex, int k, int def) {
    this.roster = new ArrayList<Player>();
    this.starters = new ArrayList<Player>();
    this.bench = new ArrayList<Player>();
    this.qbs = qbs;
    this.rbs = rbs;
    this.wrs = wrs;
    this.tes = tes;
    this.flex = flex;
    this.k = k;
    this.def = def;
  }

  // drafts player onto your team
  void draftPlayer(Player p, League l) {
    //does player fit roster needs?
    if (this.eligible(p, l)) {
      this.roster.add(p);
    } else {
      //will eventually replace this
      throw new IllegalArgumentException("Doesn't fit roster needs");
    }
    // double dispatch to determine if player starter or bench
    p.determineRole(this, l);
    l.draftPickNum += (l.numTeams - l.draftPickNum % l.numTeams) * 2 + 1;
  }

  //can player in this position be drafted onto team?
  boolean eligible(Player p, League l) {
    if (this.bench.size() < l.benchNum) {
      return true;
    } else {
      //is it still eligible if no more bench spots?
      //double dispatch
      return p.eligible(this, l);
    }
  }

  //lets you see a list of your team
  void viewTeam() {
    int player = 1;
    while (player <= roster.size()) {
      System.out.println("Round " + player + ": " + roster.get(player - 1).name);
      player++;
    }
  }

  public int numRB() {
	  return this.rbs;
  }
  public int numWR() {
	  return this.wrs;
  }
  public int numFlex() {
	  return this.flex;
  }
}

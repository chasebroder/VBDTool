package com.vbd.model;

public abstract class Player {
  public String name; // name of player
  public double xValue; // xValue of player: will have to figure out when to calculate this
  public double projectedPoints; // projected points of player
  League lg; // league settings
  public boolean drafted; //is the player drafted?

  // initializes player name and fantasy league
  public Player(String name, League lg) {
    this.name = name;
    this.lg = lg;
    this.drafted = false;
  }

  // projects points for player given estimated statistics
  abstract void projectPoints(League lg);

  // determines if player is starter or bench player on given team, given league's
  // settings
  abstract void determineRole(Team t, League lg);

  //can the player be drafted onto the team if no bench spots remain?
  abstract boolean eligible(Team t, League lg);

  public abstract boolean isQB();
    
    public abstract boolean isRB();
    
    public abstract boolean isWR();
    
    public abstract boolean isTE();
    
    public abstract boolean isDEF();
    
    public abstract boolean isK();
}

// RBs, WRs, TEs will extend this
abstract class Flex extends Player {
  double rushYds;
  double rushTDs;
  double receptions;
  double receivingYds;
  double receivingTDs;
  double fumbles;
  double twoPtCons;

  Flex(String name, League lg, double rushYds, double rushTDs, double receptions, double receivingYds,
      double receivingTDs, double fumbles, double twoPtCons) {
    super(name, lg);
    this.rushYds = rushYds;
    this.rushTDs = rushTDs;
    this.receptions = receptions;
    this.receivingYds = receivingYds;
    this.receivingTDs = receivingTDs;
    this.fumbles = fumbles;
    this.twoPtCons = twoPtCons;
    this.projectPoints(lg);
  }

  // projects total points for flex given league's settings
  void projectPoints(League lg) {
    this.projectedPoints = this.rushYds * lg.rushPtsPerYd + this.rushTDs * lg.rushTD
        + this.receptions * lg.pointsPerReception + this.receivingYds * lg.recPtsPerYD
        + this.receivingTDs * lg.recTD + this.fumbles * lg.fumble + this.twoPtCons * lg.twoPtCon;
  }
}

// Quarterback
class QB extends Player {
  double passYds;
  double passTDs;
  double rushYds;
  double rushTDs;
  double interceptions;
  double fumbles;
  double twoPtCons;

  QB(String name, League lg, double passYds, double passTDs, double rushYds, double rushTDs, double interceptions,
      double fumbles, double twoPtCons) {
    super(name, lg);
    this.passYds = passYds;
    this.passTDs = passTDs;
    this.rushYds = rushYds;
    this.rushTDs = rushTDs;
    this.interceptions = interceptions;
    this.fumbles = fumbles;
    this.twoPtCons = twoPtCons;
    this.projectPoints(lg);
  }

  // projects total points for the quarterback given league's settings
  void projectPoints(League lg) {
    this.projectedPoints = this.passYds * lg.passPtsPerYd + this.passTDs * lg.passTD
        + this.rushYds * lg.rushPtsPerYd + this.rushTDs * lg.rushTD + this.twoPtCons * lg.twoPtCon
        + this.interceptions * lg.passInt + this.fumbles * lg.fumble;
    // interceptions and fumbles should be negative in League class, so added to
    // avoid double negative
  }

  // determines if player is starter or bench player on given team
  // EFFECT: checks teams qbs field; if less than # that starts, add to starters
  // increments qbs field to display added quarterback
  void determineRole(Team t, League lg) { 
    if (t.qbs < lg.qbNum) {
      t.starters.add(this); 
    } else { 
      t.bench.add(this);
    } 
    t.qbs += 1; 
  }

  //can the QB be drafted onto the team w/o any bench spots?
  boolean eligible(Team t, League l) {
    return t.qbs < l.qbNum;
  }

  public boolean isQB() {
    return true;
  }
    
    public boolean isRB() {
      return false;
    }
    
    public boolean isWR() {
      return false;
    }
    
    public boolean isTE() {
      return false;
    }
    
    public boolean isDEF() {
      return false;
    }
    
    public boolean isK() {
      return false;
    }
}

// Running Back
class RB extends Flex {
  RB(String name, League lg, double rushYds, double rushTDs, double receptions, double receivingYds,
      double receivingTDs, double fumbles, double twoPtCons) {
    super(name, lg, rushYds, rushTDs, receptions, receivingYds, receivingTDs, fumbles, twoPtCons);
  }

  // determines if player is starter or bench player on given team
  // EFFECT: checks teams rbs field; if less than # starters, add as starter
  // if flex is less than # flex starters, then starter
  // increments rbs field to display added rb

  void determineRole(Team t, League l) { 
    if (t.rbs < lg.rbNum) { 
      t.starters.add(this); 
    } else if (t.flex < lg.flexNum) {
      t.starters.add(this);
      t.flex++;
    }
    else { 
      t.bench.add(this); } 
    t.rbs += 1;
  }

  //can the RB be drafted onto the team w/o any bench spots?
  boolean eligible(Team t, League l) {
    return t.rbs < l.rbNum || t.flex < l.flexNum;
  }

  public boolean isQB() {
    return false;
  }
    
    public boolean isRB() {
      return true;
    }
    
    public boolean isWR() {
      return false;
    }
    
    public boolean isTE() {
      return false;
    }
    
    public boolean isDEF() {
      return false;
    }
    
    public boolean isK() {
      return false;
    }
}

// Wide Receiver
class WR extends Flex {
  WR(String name, League lg, double rushYds, double rushTDs, double receptions, double receivingYds,
      double receivingTDs, double fumbles, double twoPtCons) {
    super(name, lg, rushYds, rushTDs, receptions, receivingYds, receivingTDs, fumbles, twoPtCons);
  }

  // determines if player is starter or bench player on given team
  // EFFECT: checks teams wrs field; if less than # starters, add as starter
  // if flex is less than # flex starters, then starter
  // increments wrs field to display added wr
  void determineRole(Team t, League l) { 
    if (t.wrs < lg.wrNum) { 
      t.starters.add(this); 
    } else if (t.flex < lg.flexNum) {
      t.starters.add(this);
      t.flex++;
    }
    else { 
      t.bench.add(this); 
    } 
    t.wrs += 1;
  }

  //can the WR be drafted onto the team w/o any bench spots?
  boolean eligible(Team t, League l) {
    return t.wrs < l.wrNum || t.flex < l.flexNum;
  }

  public boolean isQB() {
    return false;
  }
    
    public boolean isRB() {
      return false;
    }
    
    public boolean isWR() {
      return true;
    }
    
    public boolean isTE() {
      return false;
    }
    
    public boolean isDEF() {
      return false;
    }
    
    public boolean isK() {
      return false;
    }
}

// Tight End
class TE extends Flex {
  TE(String name, League lg, double rushYds, double rushTDs, double receptions, double receivingYds,
      double receivingTDs, double fumbles, double twoPtCons) {
    super(name, lg, rushYds, rushTDs, receptions, receivingYds, receivingTDs, fumbles, twoPtCons);
  }

  // determines if player is starter or bench player on given team
  // EFFECT: checks teams te field; if less than # starters, add as starter
  // if flex is less than # flex starters, then starter
  // increments te field to display added te
  void determineRole(Team t, League l) { 
    if (t.tes < lg.teNum) { 
      t.starters.add(this); 
    } else if (t.flex < lg.flexNum) {
      t.starters.add(this);
      t.flex++;
    } else { 
      t.bench.add(this); 
    } 
    t.tes += 1;
  }

  //can the TE be drafted onto the team w/o any bench spots?
  boolean eligible(Team t, League l) {
    return t.tes < l.teNum || t.flex < l.flexNum;
  }

  public boolean isQB() {
    return false;
  }
    
    public boolean isRB() {
      return false;
    }
    
    public boolean isWR() {
      return false;
    }
    
    public boolean isTE() {
      return true;
    }
    
    public boolean isDEF() {
      return false;
    }
    
    public boolean isK() {
      return false;
    }
}

class K extends Player {
  double PATsMade;
  double FGMade;

  K(String name, League lg, double PATsMade, double FGMade) {
    super(name, lg);
    this.PATsMade = PATsMade;
    this.FGMade = FGMade;
    this.projectPoints(lg);
  }

  // projects total points for kicker given league's settings
  void projectPoints(League lg) {
    this.projectedPoints = this.PATsMade * lg.PATMade + this.FGMade * lg.FGMade;
  }

  // determines if player is starter or bench player on given team
  // EFFECT: checks teams k field; if less than # that starts, add to starters
  // increments k field to display added k
  void determineRole(Team t, League lg) { 
    if (t.k < lg.kickNum) {
      t.starters.add(this); 
    } else { 
      t.bench.add(this); 
    } 
    t.k += 1; 
  }

  //can the K be drafted onto the team w/o any bench spots?
  boolean eligible(Team t, League l) {
    return t.k < l.kickNum;
  }

  public boolean isQB() {
    return false;
  }
    
    public boolean isRB() {
      return false;
    }
    
    public boolean isWR() {
      return false;
    }
    
    public boolean isTE() {
      return false;
    }
    
    public boolean isDEF() {
      return false;
    }
    
    public boolean isK() {
      return true;
    }
}

class DEF extends Player {
  double sacks; // amount of sacks
  double interceptions; // amount of interceptions
  double fumbles; // amount of funble recoveries
  double safeties; // amount of safeties
  double defTDs; // amount of defensive touchdowns
  double retTDs; // amount of return touchdowns
  double ptsAllowed; // amount of points allowed

  DEF(String name, League lg, double sacks, double interceptions, double fumbles, double safeties, double defTDs,
      double retTDs, double ptsAllowed) {
    super(name, lg);
    this.sacks = sacks;
    this.interceptions = interceptions;
    this.fumbles = fumbles;
    this.safeties = safeties;
    this.defTDs = defTDs;
    this.retTDs = retTDs;
    this.ptsAllowed = ptsAllowed;
    this.projectPoints(lg);
  }

  // projects total points for defense given league's settings
  void projectPoints(League lg) {
    this.projectedPoints = this.sacks * lg.sack + this.interceptions * lg.defInt
        + this.fumbles * lg.defFumble + this.safeties * lg.safety + this.defTDs * lg.defTD
        + this.retTDs * lg.retTD + this.calculatePointsAllowed(this.ptsAllowed, lg);
  }

  // how many points a defense gets depending on how many points it allows
  double calculatePointsAllowed(double ptsAllowed, League lg) {
    double pointsPerGame = ptsAllowed / 16;
    if (pointsPerGame > 7) {
      return lg.ptAllowed0 * 16;
    }
    else if (pointsPerGame > 6 && pointsPerGame <= 13) {
      return lg.ptAllowed713 * 16;
    }
    else if (pointsPerGame > 13 && pointsPerGame <= 20) {
      return lg.ptAllowed1420 * 16;
    }
    else if (pointsPerGame > 20 && pointsPerGame <= 27) {
      return lg.ptAllowed2127 * 16;
    }
    else if (pointsPerGame > 27 && pointsPerGame <= 34) {
      return lg.ptAllowed2834 * 16;
    }
    else {
      return lg.ptAllowed35 * 16;
    }
  }

  // determines if player is starter or bench player on given team
  // EFFECT: checks teams def field; if less than # that starts, add to starters
  // increments def field to display added defense

  void determineRole(Team t, League lg) { 
    if (t.def < lg.defNum) {
      t.starters.add(this); 
    } else { 
      t.bench.add(this); 
    } 
    t.def += 1; 
  }

  //can the DEF be drafted onto the team w/o any bench spots?
  boolean eligible(Team t, League l) {
    return t.def < l.defNum;
  }

  public boolean isQB() {
    return false;
  }
    
    public boolean isRB() {
      return false;
    }
    
    public boolean isWR() {
      return false;
    }
    
    public boolean isTE() {
      return false;
    }
    
    public boolean isDEF() {
      return true;
    }
    
    public boolean isK() {
      return false;
    }

}

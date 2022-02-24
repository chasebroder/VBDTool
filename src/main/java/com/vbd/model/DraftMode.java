package com.vbd.model;

import java.util.ArrayList;

public class DraftMode {
	int pickNum;
	public ArrayList<Player> undrafted;
	ArrayList<Player> drafted;
	ArrayList<Player> QBUndrafted;
	ArrayList<Player> RBUndrafted;
	ArrayList<Player> WRUndrafted;
	ArrayList<Player> TEUndrafted;
	ArrayList<Player> KUndrafted;
	ArrayList<Player> DEFUndrafted;
	public League league;
	public Team myTeam;

	public DraftMode() {
	}

	public DraftMode(ArrayList<Player> undrafted, League league, Team myTeam) {
		this.undrafted = undrafted;
		this.league = league;
		this.myTeam = myTeam;
		this.heapBuild();
		this.heapSort();
		this.QBUndrafted = new ArrayList<Player>();
		this.RBUndrafted = new ArrayList<Player>();
		this.WRUndrafted = new ArrayList<Player>();
		this.TEUndrafted = new ArrayList<Player>();
		this.KUndrafted = new ArrayList<Player>();
		this.DEFUndrafted = new ArrayList<Player>();
		this.createUndraftedPlayersList();
		// Should be 15
		this.calculateXValue(this.QBUndrafted, 9);
		// should be 36
		this.calculateXValue(this.RBUndrafted, 40);
		// should be 38
		this.calculateXValue(this.WRUndrafted, 38);
		// should by 8
		this.calculateXValue(this.TEUndrafted, 9);
		this.calculateXValue(this.KUndrafted, 0);
		// should be 2
		this.calculateXValue(this.DEFUndrafted, 1);
		this.needFactor();
		this.heapBuildX();
		this.heapSortX();
	}

	public void createUndrafted(ArrayList<Player> list) {

	}

	// EFFECT: creates a new quarterback and adds it to list of undrafted players
	void addQB(String name, double passYds, double passTDs, double rushYds, double rushTDs, double interceptions,
			double fumbles) {
		this.undrafted.add(new QB(name, this.league, passYds, passTDs, rushYds, rushTDs, interceptions, fumbles, 0));
	}

	// EFFECT: creates a new running back and adds it to list of undrafted players
	void addRB(String name, int rushYds, int rushTDs, int receptions, int receivingYds, int receivingTDs, int fumbles,
			int twoPtCons) {
		this.undrafted.add(new RB(name, this.league, rushYds, rushTDs, receptions, receivingYds, receivingTDs, fumbles,
				twoPtCons));
	}

	// EFFECT: creates a new wide receiver and adds it to list of undrafted players
	void addWR(String name, int rushYds, int rushTDs, int receptions, int receivingYds, int receivingTDs, int fumbles,
			int twoPtCons) {
		this.undrafted.add(new WR(name, this.league, rushYds, rushTDs, receptions, receivingYds, receivingTDs, fumbles,
				twoPtCons));
	}

	// EFFECT: creates a new tight end and adds it to list of undrafted players
	void addTE(String name, int rushYds, int rushTDs, int receptions, int receivingYds, int receivingTDs, int fumbles,
			int twoPtCons) {
		this.undrafted.add(new TE(name, this.league, rushYds, rushTDs, receptions, receivingYds, receivingTDs, fumbles,
				twoPtCons));
	}

	// EFFECT: creates a new kicker and adds it to list of undrafted players
	void addK(String name, int PATsMade, int FGMade) {
		this.undrafted.add(new K(name, this.league, PATsMade, FGMade));
	}

	// EFFECT: creates a new defense and adds it to list of undrafted players
	void addDEF(String name, int sacks, int interceptions, int fumbles, int safeties, int defTDs, int retTDs,
			int ptsAllowed) {
		this.undrafted
				.add(new DEF(name, this.league, sacks, interceptions, fumbles, safeties, defTDs, retTDs, ptsAllowed));
	}

	// Effect: Adds all the Players to a list of undrafted players
	public QB createUndraftedQB(String name, League l, double passYds, double passTds, double rushYds, double rushTds,
			double passInts, double fumbles, double twoPtCon) {
		return new QB(name, l, passYds, passTds, rushYds, rushTds, passInts, fumbles, twoPtCon);
	}

	public RB createUndraftedRB(String name, League l, double rushYds, double rushTDs, double receptions, double recYds,
			double recTDs, double fumbles, double twoPtCon) {
		return new RB(name, l, rushYds, rushTDs, receptions, recYds, recTDs, fumbles, twoPtCon);
	}

	public WR createUndraftedWR(String name, League l, double rushYds, double rushTDs, double receptions, double recYds,
			double recTDs, double fumbles, double twoPtCon) {
		return new WR(name, l, rushYds, rushTDs, receptions, recYds, recTDs, fumbles, twoPtCon);
	}

	public TE createUndraftedTE(String name, League l, double rushYds, double rushTDs, double receptions, double recYds,
			double recTDs, double fumbles, double twoPtCon) {
		return new TE(name, l, rushYds, rushTDs, receptions, recYds, recTDs, fumbles, twoPtCon);
	}

	public DEF createUndraftedDEF(String name, League l, double sack, double ints, double fumRec, double safety,
			double defTD, double retTD, double ptsAllowed) {
		return new DEF(name, l, sack, ints, fumRec, safety, defTD, retTD, ptsAllowed);
	}

	public K createUndraftedK(String name, League l, double xPT, double FG) {
		return new K(name, l, xPT, FG);
	}

	// Effect: Adds all the Players to a list of their positions
	void createUndraftedPlayersList() {
		for (Player p : this.undrafted) {
			if (p instanceof QB) {
				QBUndrafted.add(p);
			}
			if (p instanceof RB) {
				RBUndrafted.add(p);
			}
			if (p instanceof WR) {
				WRUndrafted.add(p);
			}
			if (p instanceof TE) {
				TEUndrafted.add(p);
			}
			if (p instanceof K) {
				KUndrafted.add(p);
			}
			if (p instanceof DEF) {
				DEFUndrafted.add(p);
			}
		}
	}

	// Effect: builds a heap
	void heapBuild() {
		for (int i = 1; i < this.undrafted.size(); i++) {
			upHeap(i);
		}
	}

	void heapBuildX() {
		for (int i = 1; i < this.undrafted.size(); i++) {
			upHeapX(i);
		}
	}

	// Effect:Used when adding elements to the heap; bubbles up the element until a
	// heap is made
	void upHeap(int i) {
		int parentIdx = ((i - 1) / 2);
		if (this.undrafted.get(i).projectedPoints < this.undrafted.get(parentIdx).projectedPoints) {
			Player parent = this.undrafted.get(parentIdx);
			Player child = this.undrafted.get(i);
			this.undrafted.set(parentIdx, child);
			this.undrafted.set(i, parent);
			upHeap(parentIdx);
		}
	}

	// Effect:Used when adding elements to the heap; bubbles up the element until a
	// heap is made
	void upHeapX(int i) {
		int parentIdx = ((i - 1) / 2);
		if (this.undrafted.get(i).xValue < this.undrafted.get(parentIdx).xValue) {
			Player parent = this.undrafted.get(parentIdx);
			Player child = this.undrafted.get(i);
			this.undrafted.set(parentIdx, child);
			this.undrafted.set(i, parent);
			upHeapX(parentIdx);
		}
	}

	// Effect:used when sorting heap; Swaps first and last items then bubbles down
	// until a heap is made
	void downHeap(int i, int maxIdx) {
		int leftIdx = 2 * i + 1;
		int rightIdx = 2 * i + 2;
		int biggestIdx;
		if (rightIdx <= maxIdx && leftIdx <= maxIdx) {
			if (this.undrafted.get(i).projectedPoints > this.undrafted.get(leftIdx).projectedPoints
					|| (this.undrafted.get(i).projectedPoints > this.undrafted.get(rightIdx).projectedPoints)) {
				if (this.undrafted.get(leftIdx).projectedPoints < this.undrafted.get(rightIdx).projectedPoints) {
					biggestIdx = leftIdx;
				} else {
					biggestIdx = rightIdx;
				}
				Player parent = this.undrafted.get(i);
				Player child = this.undrafted.get(biggestIdx);
				this.undrafted.set(i, child);
				this.undrafted.set(biggestIdx, parent);
				downHeap(biggestIdx, maxIdx);
			}
		}
		if (rightIdx > maxIdx && leftIdx <= maxIdx
				&& this.undrafted.get(i).projectedPoints > this.undrafted.get(leftIdx).projectedPoints) {
			Player parent = this.undrafted.get(i);
			Player child = this.undrafted.get(leftIdx);
			this.undrafted.set(i, child);
			this.undrafted.set(leftIdx, parent);
			downHeap(leftIdx, maxIdx);
		}
	}

	// Effect:used when sorting heap; Swaps first and last items then bubbles down
	// until a heap is made
	void downHeapX(int i, int maxIdx) {
		int leftIdx = 2 * i + 1;
		int rightIdx = 2 * i + 2;
		int biggestIdx;
		if (rightIdx <= maxIdx && leftIdx <= maxIdx) {
			if (this.undrafted.get(i).xValue > this.undrafted.get(leftIdx).xValue
					|| (this.undrafted.get(i).xValue > this.undrafted.get(rightIdx).xValue)) {
				if (this.undrafted.get(leftIdx).xValue < this.undrafted.get(rightIdx).xValue) {
					biggestIdx = leftIdx;
				} else {
					biggestIdx = rightIdx;
				}
				Player parent = this.undrafted.get(i);
				Player child = this.undrafted.get(biggestIdx);
				this.undrafted.set(i, child);
				this.undrafted.set(biggestIdx, parent);
				downHeapX(biggestIdx, maxIdx);
			}
		}
		if (rightIdx > maxIdx && leftIdx <= maxIdx
				&& this.undrafted.get(i).xValue > this.undrafted.get(leftIdx).xValue) {
			// System.out.println("swapo" + undrafted.get(i).xValue + " " +
			// undrafted.get(leftIdx).xValue);
			Player parent = this.undrafted.get(i);
			Player child = this.undrafted.get(leftIdx);
			this.undrafted.set(i, child);
			this.undrafted.set(leftIdx, parent);
			downHeapX(leftIdx, maxIdx);
		}
	}

	// Effect:sorts the heap from lowest element value to highest
	void heapSort() {
		int i = 0;
		int end = this.undrafted.size() - 1;
		while (i < this.undrafted.size()) {
			Player max = this.undrafted.get(0);
			Player last = this.undrafted.get(end);
			this.undrafted.set(0, last);
			this.undrafted.set(end, max);
			end--;
			i++;
			downHeap(0, end);
		}
	}

	// Effect:sorts the heap from lowest element value to highest
	void heapSortX() {
		int i = 0;
		int end = this.undrafted.size() - 1;
		while (i < this.undrafted.size()) {
			// int q =0;
			// while(q<undrafted.size()) {
			// //System.out.print(undrafted.get(q).xValue + " ");
			// q++;
			// }
			// System.out.println("");
			Player max = this.undrafted.get(0);
			Player last = this.undrafted.get(end);
			this.undrafted.set(0, last);
			this.undrafted.set(end, max);
			end--;
			i++;

			downHeapX(0, end);

		}
	}

	// Effect:Calculates the xValue of Players
	void calculateXValue(ArrayList<Player> players, int benchmark) {
		for (Player p : players) {
			p.xValue = p.projectedPoints - players.get(benchmark).projectedPoints;
		}
	}

	void needFactor() {
		for (Player p : this.undrafted) {
			if ((p instanceof QB) && this.myTeam.qbs >= 1) {
				p.xValue = p.xValue * (1 - this.myTeam.qbs * 0.2);
			}
			if ((p instanceof RB) && this.myTeam.flex == 1 && this.myTeam.rbs >= 2) {
				p.xValue = p.xValue * (1 - (this.myTeam.rbs - 1) * 0.2);
			}
			if ((p instanceof WR) && this.myTeam.flex == 1 && this.myTeam.wrs >= 2) {
				p.xValue = p.xValue * (1 - (this.myTeam.wrs - 1) * 0.2);
			}
			if ((p instanceof TE) && this.myTeam.flex == 1 && this.myTeam.tes >= 1) {
				p.xValue = p.xValue * (1 - this.myTeam.tes * 0.2);
			}
			if ((p instanceof K) && this.myTeam.k >= 1) {
				p.xValue = p.xValue * (1 - this.myTeam.k * 0.2);
			}
			if ((p instanceof DEF) && this.myTeam.def >= 1) {
				p.xValue = p.xValue * (1 - this.myTeam.def * 0.2);
			}
		}
	}

	// drafts a player
	// EFFECT: removes player from undrafted and position undrafted arraylists
	// If drafted to your team, calls other method on team
	public void draft(Player p) {
		// is it your pick?
		if (p.drafted == false) {

			if (this.league.draftPickNum == this.league.pickNum) {
				// need to make sure there's room on the bench: not too positive what to do
				// otherwise
				if (this.myTeam.bench.size() < this.league.benchNum) {
					this.myTeam.draftPlayer(p, this.league);
				}
				// this.myTeam.draftPlayer(p, this.league);
				this.needFactor();
				this.heapBuildX();
				this.heapSortX();
			}
			// if (p instanceof QB) {
			// this.QBUndrafted.remove(p);
			// } else if (p instanceof RB) {
			// this.RBUndrafted.remove(p);
			// } else if (p instanceof WR) {
			// this.WRUndrafted.remove(p);
			// } else if (p instanceof TE) {
			// this.TEUndrafted.remove(p);
			// } else if (p instanceof K) {
			// this.KUndrafted.remove(p);
			// } else {
			// this.DEFUndrafted.remove(p);
			// }
			// increments pick number by one
			this.league.draftPickNum += 1;
		} else {
			// throw new IllegalArgumentException("Player has already been drafted");
			System.out.println("Player has already been drafted");
		}
		p.drafted = true;
	}
}

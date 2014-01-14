package com.taipeihot.jazzlist.fight;

public abstract class FightAction {
	String selfAnimation;
	String opponentAnimation;
	
	public FightAction() {
		
	}
	
	public FightAction(String selfAnime, String opponentAnime) {
		this.selfAnimation = selfAnime;
		this.opponentAnimation = opponentAnime;
	}
	/** == player1 perform action on player2 == **/
	public abstract String getMessage(Player player1, Player player2);
	
	public String getSelfAnimation() {
		return selfAnimation;
	}

	public String getOpponentAnimation() {
		return opponentAnimation;
	}
	
	
}
package controller.dto;

import domain.Rank;

public class RankDto {
	private final int money;
	private final int match;
	private final boolean bonus;
	private final Long amount;

	public RankDto(Rank rank, Long amount) {
		this.match = rank.getMatch();
		this.money = rank.getMoney();
		this.bonus = rank.getBonus();
		this.amount = amount;
	}

	public Rank toEntity() {
		return Rank.of(getMatch(), isBonus());
	}

	public int getMoney() {
		return money;
	}

	public int getMatch() {
		return match;
	}

	public boolean isBonus() {
		return this.match == Rank.SECOND.getMatch() && this.bonus;
	}

	public Long getAmount() {
		return amount;
	}
}

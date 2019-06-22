package com.woowacourse.lotto.domain.request;

import java.util.List;

public class LottoSearchRequest {
	private int selectedRound;
	private List<Integer> rounds;

	public int getSelectedRound() {
		return selectedRound;
	}

	public void setSelectedRound(int selectedRound) {
		this.selectedRound = selectedRound;
	}

	public List<Integer> getRounds() {
		return rounds;
	}

	public void setRounds(List<Integer> rounds) {
		this.rounds = rounds;
	}
}

package com.woowacourse.lotto.domain.request;

import java.util.List;

public class WinningLottoInputRequest {
	private int bonusBall;
	private int countOfManualLotto;
	private List<String> winningLotto;

	public int getBonusBall() {
		return bonusBall;
	}

	public void setBonusBall(int bonusBall) {
		this.bonusBall = bonusBall;
	}

	public int getCountOfManualLotto() {
		return countOfManualLotto;
	}

	public void setCountOfManualLotto(int countOfManualLotto) {
		this.countOfManualLotto = countOfManualLotto;
	}

	public List<String> getWinningLotto() {
		return winningLotto;
	}

	public void setWinningLotto(List<String> winningLotto) {
		this.winningLotto = winningLotto;
	}
}

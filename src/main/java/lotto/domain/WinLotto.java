package lotto.domain;

import java.util.List;

import lotto.utils.StringUtils;

public class WinLotto {
	private List<Integer> winLotto;

	public WinLotto(String winLotto) {
		this.winLotto = StringUtils.split(winLotto);
	}

	public List<Integer> getWinLotto() {
		return winLotto;
	}
}

package lotto.domain;

import lotto.utils.StringUtils;

public class WinLotto {
	private Lotto winLotto;

	public WinLotto(String winLotto) {
		this.winLotto = new Lotto(StringUtils.split(winLotto));
	}

	// public List<Integer> getWinLotto() {
	// 	return winLotto;
	// }
}

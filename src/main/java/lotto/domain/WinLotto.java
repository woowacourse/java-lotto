package lotto.domain;

import lotto.utils.StringUtils;

public class WinLotto {
	private Lotto winLotto;
	private BonusBall bonusBall;

	public WinLotto(String winLotto, String bonusBall) {
		this.winLotto = new Lotto(StringUtils.split(winLotto));
		this.bonusBall = new BonusBall(bonusBall);
	}

	// public List<Integer> getWinLotto() {
	// 	return winLotto;
	// }
}

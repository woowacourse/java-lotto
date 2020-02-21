package lotto.domain;

import lotto.utils.LottoNoUtils;

public class WinLotto extends Lotto implements Cloneable {
	private static final int MIN_WIN_VALUE = 3;
	private static final int NO_WIN_VALUE = 0;

	private final BonusBall bonusBall;

	public WinLotto(String winLotto, String bonusBall) {
		super(LottoNoUtils.split(winLotto));
		this.bonusBall = new BonusBall(bonusBall);
	}

	public int compare(Lotto lotto) {
		int count = (int)this.lottoNumbers.stream()
			.filter(x -> lotto.isContain(x))
			.count();
		if (count < MIN_WIN_VALUE) {
			count = NO_WIN_VALUE;
		}
		return count;
	}

	public boolean isMatchBonus(Lotto lotto) {
		return bonusBall.isContainBonusBall(lotto);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

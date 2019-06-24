package com.woowacourse.lotto.domain.request;

import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.WinningLotto;

public class WinningLottoRequest {
	private WinningLotto winningLotto;
	private Lottos lottos;
	private int round;
	private LottoMoney lottoMoney;

	public WinningLotto getWinningLotto() {
		return winningLotto;
	}

	public void setWinningLotto(WinningLotto winningLotto) {
		this.winningLotto = winningLotto;
	}

	public Lottos getLottos() {
		return lottos;
	}

	public void setLottos(Lottos lottos) {
		this.lottos = lottos;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public LottoMoney getLottoMoney() {
		return lottoMoney;
	}

	public void setLottoMoney(LottoMoney lottoMoney) {
		this.lottoMoney = lottoMoney;
	}
}

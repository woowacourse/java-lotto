package com.woowacourse.lotto.domain.request;

import java.util.List;

import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.domain.factory.LottosFactory;

public class LottoGenerateRequest {
	private int countOfManualLotto;
	private LottoMoney lottoMoney;
	private LottosFactory lottosFactory;
	private List<String> manualLottos;
	private int round;

	public int getCountOfManualLotto() {
		return countOfManualLotto;
	}

	public void setCountOfManualLotto(int countOfManualLotto) {
		this.countOfManualLotto = countOfManualLotto;
	}

	public LottoMoney getLottoMoney() {
		return lottoMoney;
	}

	public void setLottoMoney(LottoMoney lottoMoney) {
		this.lottoMoney = lottoMoney;
	}

	public LottosFactory getLottosFactory() {
		return lottosFactory;
	}

	public void setLottosFactory(LottosFactory lottosFactory) {
		this.lottosFactory = lottosFactory;
	}

	public List<String> getManualLottos() {
		return manualLottos;
	}

	public void setManualLottos(List<String> manualLottos) {
		this.manualLottos = manualLottos;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
}
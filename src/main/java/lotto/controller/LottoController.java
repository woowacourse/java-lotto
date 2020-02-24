package lotto.controller;

import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoGenerator;
import lotto.domain.lotto.LottoParser;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottomoney.InvalidLottoMoneyException;
import lotto.domain.lottomoney.LottoMoney;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.result.LottoRank;
import lotto.domain.result.LottoRankRecord;
import lotto.domain.result.WinningLotto;

public class LottoController {
	public void run() {
		LottoController lottoController = new LottoController();
		LottoMoney inputLottoMoney = receiveInputMoney();
		int numberOfLotto = inputLottoMoney.calculateNumberOfLotto();
		printPurchaseCompleteMessage(numberOfLotto);

		Lottos lottos = lottoController.purchaseLotto(numberOfLotto);
		printPurchasedLotto(lottos);

		Lotto inputWinningLotto = new Lotto(LottoParser.parser(inputWinningLottoNumber()));
		LottoNumber inputBonusNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		WinningLotto winningLotto = new WinningLotto(inputWinningLotto, inputBonusNumber);

		LottoRankRecord lottoRankCount = new LottoRankRecord(lottos, winningLotto);
		printStatisticsMessage();
		printWinningResult(lottoRankCount.getLottoRankCount());

		int winningRatio = lottoController.calculateWinningRatio(lottoRankCount.getLottoRankCount(), inputLottoMoney);
		printWinningRatio(winningRatio);
	}

	private static LottoMoney receiveInputMoney() {
		try {
			return new LottoMoney(inputMoney());
		} catch (InvalidLottoMoneyException ime) {
			printExceptionMessage(ime.getMessage());
			return receiveInputMoney();
		}
	}

	private Lottos purchaseLotto(int numberOfLotto) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(LottoGenerator.generate());
		}
		return new Lottos(lottos);
	}

	private int calculateWinningRatio(Map<LottoRank, Integer> lottoRankCount, LottoMoney inputLottoMoney) {
		LottoMoney totalWinningMoney = LottoRank.MISS.getWinningMoney();
		for (Map.Entry<LottoRank, Integer> lottoEntry : lottoRankCount.entrySet()) {
			totalWinningMoney = totalWinningMoney.add(
				lottoEntry.getKey().getWinningMoney().multiply(lottoEntry.getValue()));
		}
		return totalWinningMoney.getWinningRate(inputLottoMoney);
	}
}

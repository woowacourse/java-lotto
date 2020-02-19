package lotto;

import java.util.Map;

import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoStatistics;
import lotto.domain.Lottos;
import lotto.domain.RandomLottoGenerator;
import lotto.domain.WinningLotto;

public class LottoApplication {
	public static void main(String[] args) {
		int amount = 2100000000;
		LottoMoney lottoMoney = new LottoMoney(amount);
		Lottos lottos = new RandomLottoGenerator().generate(lottoMoney.calculateBuyCount());
		WinningLotto winningLotto = new WinningLotto(new RandomLottoGenerator().generate(), LottoNumber.of(6));
		Map<LottoRank, Long> matchResults = lottos.calculate(winningLotto);
		LottoStatistics lottoStatistics = new LottoStatistics(matchResults);
		long totalProfits = lottoStatistics.calculateTotalProfits(lottoMoney);
		System.out.println(totalProfits);
	}
}

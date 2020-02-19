package lotto;

import java.util.Arrays;
import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoStatistics;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

public class LottoApplication {
	public static void main(String[] args) {
		int amount = 14000;
		LottoMoney lottoMoney = new LottoMoney(amount);
		Lottos lottos = new Lottos(Arrays.asList(new Lotto(Arrays.asList(LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(3),
				LottoNumber.of(43),
				LottoNumber.of(44),
				LottoNumber.of(45)))));
		WinningLotto winningLotto = new WinningLotto(
				Lotto.of(1, 2, 5, 43, 44, 45),
				LottoNumber.of(7));
		Map<LottoRank, Long> matchResults = lottos.calculate(winningLotto);
		LottoStatistics lottoStatistics = new LottoStatistics(matchResults);
		long totalProfits = lottoStatistics.calculateTotalProfits(lottoMoney);
		System.out.println(totalProfits);
	}
}

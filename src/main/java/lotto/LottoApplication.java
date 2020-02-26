package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoStore;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(InputView.inputPurchaseMoney());
		List<Lotto> manualLottos = LottoStore.buy(lottoPurchaseMoney,
				InputView.inputManualLotto(Integer.parseInt(InputView.inputManualLottoCount())));
		List<Lotto> autoLottos = LottoStore.buy(lottoPurchaseMoney);
		Lottos lottos = Stream.concat(manualLottos.stream(), autoLottos.stream())
				.collect(Collectors.collectingAndThen(Collectors.toList(), Lottos::new));
		OutputView.printBuyCount(manualLottos.size(), autoLottos.size());
		OutputView.printLottos(lottos);
		WinningLotto winningLotto = new WinningLotto(LottoFactory.create(InputView.inputWinningLotto()),
				LottoNumber.of(InputView.inputWinningLottoBonus()));
		LottoStatistics lottoStatistics = new LottoStatistics(lottoPurchaseMoney, lottos.match(winningLotto));
		OutputView.printStatistics(lottoStatistics);
	}
}

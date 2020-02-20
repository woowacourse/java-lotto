package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

public class OutputView {
	public static void printBuyCount(int buyCount) {
		System.out.println(buyCount + "개를 구매했습니다.");
	}

	public static void printLottos(Lottos lottos) {
		lottos.forEach(OutputView::printLotto);
	}

	private static void printLotto(Lotto lotto) {
		List<Integer> lottoNumbers = lotto.getValue()
				.stream()
				.map(LottoNumber::getValue)
				.collect(Collectors.toList());
		System.out.println(lottoNumbers);
	}
}

package com.woowacourse.lotto.view;

import java.util.Map;

import com.woowacourse.lotto.domain.*;

public class OutputView {
	private static final String PRINT_RESULT_MESSAGE = "당첨 통계\n---------";
	private static final String PRINT_EARNINGS_RATE = "총 수익률은 %d%%" + " 입니다.\n";
	private static final String PRINT_SECOND_OF_LOTTO = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
	private static final String PRINT_RESULT_OF_LOTTO = "%d개 일치 (%d원)- %d개\n";
	private static final String PRINT_COUNT_OF_PURCHASED_LOTTO = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";

	public static void printLotto(Lottos generateLotto) {
		for (Lotto lotto : generateLotto.getLottos()) {
			System.out.println(lotto);
		}
	}

	public static void printLottoResult(LottoResult lottoResult) {
		System.out.println(PRINT_RESULT_MESSAGE);
		lottoResult.getRanks().forEach(rank -> printRank(rank, lottoResult.valueOf(rank)));
	}

	private static void printRank(LottoRank lottoRank, int count) {
		System.out.printf(lottoRank == LottoRank.SECOND ? PRINT_SECOND_OF_LOTTO : PRINT_RESULT_OF_LOTTO, lottoRank.getCount(), lottoRank.getPrice(), count);
	}

	public static void printEarningsRate(Money money, LottoResult lottoResult) {
		System.out.printf(PRINT_EARNINGS_RATE, (money.calculateEarningsRate(lottoResult.sum())));
	}

	public static void printCountOfPurchasedLotto(int countOfAllLotto, int countOfManualLotto) {
		System.out.printf(PRINT_COUNT_OF_PURCHASED_LOTTO, countOfManualLotto, countOfAllLotto - countOfManualLotto);
	}
}

package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.dto.LottoRankDTO;

public class OutputViewConsole {
	private static final String PRINT_RESULT_MESSAGE = "당첨 통계\n---------";
	public static final String PRINT_EARNINGS_RATE = "총 수익률은 %d%%" + " 입니다.\n";
	private static final String PRINT_COUNT_OF_PURCHASED_LOTTO = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";

	public static void printLotto(Lottos generateLotto) {
		for (Lotto lotto : generateLotto.getLottos()) {
			System.out.println(lotto);
		}
	}

	public static void printLottoResult(LottoRankDTO lottoRankDTO) {
		System.out.println(PRINT_RESULT_MESSAGE);
		System.out.println(lottoRankDTO.printLottoRank());
	}

	public static void printEarningsRate(LottoMoney lottoMoney, LottoResult lottoResult) {
		System.out.printf(PRINT_EARNINGS_RATE, (lottoMoney.calculateEarningsRate(lottoResult.sum())));
	}

	public static void printCountOfPurchasedLotto(int countOfAllLotto, int countOfManualLotto) {
		System.out.printf(PRINT_COUNT_OF_PURCHASED_LOTTO, countOfManualLotto, countOfAllLotto - countOfManualLotto);
	}
}

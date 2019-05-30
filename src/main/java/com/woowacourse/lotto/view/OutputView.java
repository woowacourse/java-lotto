package com.woowacourse.lotto.view;

import java.util.Map;

import com.woowacourse.lotto.domain.*;

import static com.woowacourse.lotto.view.UserOutput.PRINT_LOTTO_MATCH_RESULT;
import static com.woowacourse.lotto.view.UserOutput.PRINT_LOTTO_MATCH_SECOND;

public class OutputView {
	public static void printLotto(Lottos generateLotto) {
		for (Lotto lotto : generateLotto.getLottos()) {
			System.out.println(lotto);
		}
	}

	public static void printNumberOfLotto(Money money) {
		System.out.printf(UserOutput.PRINT_NUMBER_OF_LOTTO.getUserOutputMessage(), money.getCountOfLotto());
	}

	public static void printLottoMatchResult(Map<LottoRank, Integer> lottoMatchResult) {
		StringBuffer stringBuffer = new StringBuffer();
		lottoMatchResult.keySet().stream()
				.filter(lottoRank -> lottoRank != LottoRank.ZERO)
				.forEach(lottoRank -> stringBuffer.append((lottoRank == LottoRank.SECOND) ?
						String.format(PRINT_LOTTO_MATCH_SECOND.getUserOutputMessage(), lottoRank.getCount(), lottoRank.getPrice(), lottoMatchResult.get(lottoRank)) :
						String.format(PRINT_LOTTO_MATCH_RESULT.getUserOutputMessage(), lottoRank.getCount(), lottoRank.getPrice(), lottoMatchResult.get(lottoRank))))
		;
		System.out.print(stringBuffer.toString());
	}

	public static void printEarningsRate(LottoResult lottoResult) {
		System.out.printf(UserOutput.PRINT_EARNINGS_RATE.getUserOutputMessage(), (int) lottoResult.getEarningsRate());
	}

	public static void printExceptionMessage(String exceptionMessage) {
		System.out.println(exceptionMessage);
	}
}

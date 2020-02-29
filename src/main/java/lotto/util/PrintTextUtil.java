package lotto.util;

import lotto.domain.result.LottoRank;

public class PrintTextUtil {
	private static final String ONE_RANK_MATCH_RESULT_MESSAGE = "%s %s %s";
	private static final String MATCH_COUNT_MESSAGE = "%s개 일치";
	private static final String BONUS_BALL_MESSAGE = ", 보너스 볼 일치";
	private static final String TOTAL_PRIZE_AND_COUNT_MESSAGE = " (%s원) - %s개\n";
	private static final String EMPTY_MESSAGE = "";

	public static String parseLottoMatchingResult(LottoRank lottoRank, Long count) {
		if (lottoRank.isMissing()) {
			return EMPTY_MESSAGE;
		}
		return String.format(ONE_RANK_MATCH_RESULT_MESSAGE, getMatchCountMessage(lottoRank),
			getBonusBallMessageOrEmpty(lottoRank), getTotalPrizeAndCountMessage(lottoRank, count));
	}

	private static String getMatchCountMessage(LottoRank lottoRank) {
		return String.format(MATCH_COUNT_MESSAGE, lottoRank.getMatchCount());
	}

	private static String getBonusBallMessageOrEmpty(LottoRank lottoRank) {
		if (lottoRank == LottoRank.SECOND) {
			return BONUS_BALL_MESSAGE;
		}
		return EMPTY_MESSAGE;
	}

	private static String getTotalPrizeAndCountMessage(LottoRank lottoRank, Long count) {
		return String.format(TOTAL_PRIZE_AND_COUNT_MESSAGE, lottoRank.getPrize(), count);
	}
}

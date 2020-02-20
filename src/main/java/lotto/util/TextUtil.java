package lotto.util;

import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.MatchResult;

/**
 * 출력 문자열을 생성해주는 유틸리티 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/20
 */
public class TextUtil {
	public static final String LOTTO_NUMBER_DELIMITER = ", ";
	public static final String EMPTY_TEXT = "";
	public static final String SECOND_WINNING_MESSAGE = "%s개 일치, 보너스 볼 일치(%s원) - %d개\n";
	public static final String WINNING_MESSAGE = "%s개 일치(%s원) - %d개\n";

	private TextUtil() {
	}

	public static String generateLottoTextWithComma(Lotto lotto) {
		return lotto.getLotto()
				.stream()
				.map(LottoNumber::toString)
				.collect(Collectors.joining(LOTTO_NUMBER_DELIMITER));
	}

	public static String generateMatchResultText(MatchResult matchResult) {
		StringBuilder stringBuilder = new StringBuilder();
		for (LottoRank rank : LottoRank.valuesAsReverse()) {
			long matchCount = matchResult.findMatchCountByLottoRank(rank);
			String matchText = generateMatchResultTextByMatchCount(rank, matchCount);
			stringBuilder.append(matchText);
		}
		return stringBuilder.toString();
	}

	private static String generateMatchResultTextByMatchCount(LottoRank rank, long matchCount) {
		if (LottoRank.MISS.equals(rank)) {
			return EMPTY_TEXT;
		}
		if (LottoRank.SECOND.equals(rank)) {
			return String.format(SECOND_WINNING_MESSAGE, rank.getMatchCount(), rank.getWinnings(), matchCount);
		}
		return String.format(WINNING_MESSAGE, rank.getMatchCount(), rank.getWinnings(), matchCount);
	}
}

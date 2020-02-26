package lotto.util;

import java.util.stream.Collectors;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoTicket.LottoTicket;
import lotto.domain.result.LottoRank;

public class StringUtil {
	private static final String JOINING_DELIMITER = ", ";
	private static final String PREFIX = "[";
	private static final String SUFFIX = "]";
	private static final String SECOND_WINNING_RANK_MESSAGE = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
	private static final String DEFAULT_WINNING_RANK_MESSAGE = "%d개 일치 (%d원) - %d개";

	public static String joiningLottoNumbersAt(LottoTicket lottoTicket) {
		return lottoTicket.getLottoNumbers().stream()
			.map(LottoNumber::toString)
			.collect(Collectors.joining(JOINING_DELIMITER, PREFIX, SUFFIX));
	}

	public static String generateFormOfLottoRank(LottoRank lottoRank, Long lottoRankCount) {
		int matchCount = lottoRank.getMatchCount();
		long winningLottoMoney = lottoRank.getWinningLottoMoney();
		if (lottoRank.equals(LottoRank.SECOND)) {
			return String.format(SECOND_WINNING_RANK_MESSAGE, matchCount, winningLottoMoney, lottoRankCount);
		}
		return String.format(DEFAULT_WINNING_RANK_MESSAGE, matchCount, winningLottoMoney, lottoRankCount);
	}
}

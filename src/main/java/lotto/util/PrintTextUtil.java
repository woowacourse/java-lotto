package lotto.util;

import static java.lang.System.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Set;

import lotto.domain.result.LottoRank;
import lotto.domain.result.WinningResult;
import lotto.domain.ticket.LottoBall;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;

public class PrintTextUtil {
	private static final String LOTTO_NUMBERS_DELIMITER = ", ";
	private static final String LOTTO_NUMBER_PREFIX = "[";
	private static final String LOTTO_NUMBER_SUFFIX = "]";
	private static final String LINE_SEPARATOR = lineSeparator();
	private static final String SINGLE_RANK_STATISTIC_MESSAGE = String.format("%%s개 일치 (%%s원) - %%s개%s",
		LINE_SEPARATOR);
	private static final String SINGLE_RANK_STATISTIC_WITH_BONUS_BALL_MESSAGE = String.format(
		"%%s개 일치, 보너스 볼 일치 (%%s원) - %%s개%s", LINE_SEPARATOR);
	private static final String EMPTY_MESSAGE = "";

	private PrintTextUtil() {
	}

	public static String createLottosText(LottoTickets lottoTickets) {
		return lottoTickets.getLottoTickets().stream()
			.map(LottoTicket::getLottoBalls)
			.map(PrintTextUtil::parseLottoTicket)
			.map(PrintTextUtil::joinLottoTicket)
			.collect(joining(LINE_SEPARATOR));
	}

	private static List<String> parseLottoTicket(Set<LottoBall> lottoBalls) {
		return lottoBalls.stream()
			.map(LottoBall::toString)
			.collect(toList());
	}

	private static String joinLottoTicket(List<String> lottoTickets) {
		return lottoTickets.stream()
			.collect(joining(LOTTO_NUMBERS_DELIMITER, LOTTO_NUMBER_PREFIX, LOTTO_NUMBER_SUFFIX));
	}

	public static String createLottosStatisticText(WinningResult winningResult) {
		return LottoRank.valuesAscendingOrder().stream()
			.map(rank -> createSingleStatisticMessage(rank, winningResult.findWinningCount(rank)))
			.collect(joining());
	}

	private static String createSingleStatisticMessage(LottoRank lottoRank, Long count) {
		if (lottoRank == LottoRank.MISSING) {
			return EMPTY_MESSAGE;
		}
		if (lottoRank == LottoRank.SECOND) {
			return String.format(SINGLE_RANK_STATISTIC_WITH_BONUS_BALL_MESSAGE, lottoRank.getMatchCount(),
				lottoRank.getPrize(), count);
		}
		return String.format(SINGLE_RANK_STATISTIC_MESSAGE, lottoRank.getMatchCount(), lottoRank.getPrize(), count);
	}
}

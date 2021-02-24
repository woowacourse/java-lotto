package lotto.view;

import lotto.domain.result.Rank;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.view.OutputView.DEFAULT_STATISTICS_FORMAT;
import static lotto.view.OutputView.STATISTICS_FORMAT_FOR_SECOND;

enum RankMessage {
	FIRST(DEFAULT_STATISTICS_FORMAT),
	SECOND(STATISTICS_FORMAT_FOR_SECOND),
	THIRD(DEFAULT_STATISTICS_FORMAT),
	FOURTH(DEFAULT_STATISTICS_FORMAT),
	FIFTH(DEFAULT_STATISTICS_FORMAT),
	NONE(DEFAULT_STATISTICS_FORMAT);

	private final String message;

	RankMessage(String message) {
		Rank correspondingRank = getCorrespondingRank();
		int count = correspondingRank.getCount();
		int prize = correspondingRank.getPrize();
		this.message = String.format(message, count, prize);
	}

	static List<String> getRankMessages() {
		return Rank.getRanksForStatistics()
				.stream()
				.map(RankMessage::getMessageOf)
				.collect(Collectors.toList());
	}

	private static String getMessageOf(Rank rank) {
		String rankName = rank.name();
		return RankMessage.valueOf(rankName)
				.message;
	}

	private Rank getCorrespondingRank() {
		return Rank.valueOf(this.name());
	}
}

package lotto.view;

import lotto.domain.Rank;

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
        this.message = String.format(message, Rank.valueOf(this.name()).getCount(), Rank.valueOf(this.name()).getPrize());
    }

    static List<String> getRankMessages() {
        return Rank.getRanksForStatistics()
                .stream()
                .map(RankMessage::getCorrespondingMessage)
                .collect(Collectors.toList());
    }

    private static String getCorrespondingMessage(Rank rank) {
        return RankMessage.valueOf(rank.name()).message;
    }
}

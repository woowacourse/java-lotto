package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LottoRank {
    NO_REWARD(0, 0L, false),
    FIFTH_PLACE(3, 5_000L, false),
    FORTH_PLACE(4, 50_000L, false),
    THIRD_PLACE(5, 1_500_000L, false),
    SECOND_PLACE(5, 30_000_000L, true),
    FIRST_PLACE(6, 2_000_000_000L, false);

    private final int matchCount;
    private final long winningAmount;
    private final boolean containsBonus;

    LottoRank(int matchCount, long winningAmount, boolean containsBonus) {
        this.matchCount = matchCount;
        this.winningAmount = winningAmount;
        this.containsBonus = containsBonus;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public long getWinningAmount() {
        return winningAmount;
    }

    public static LottoRank findRankWithMatchResult(MatchResultDto matchResultDto) {
        if (matchResultDto.getMatchCount() == SECOND_PLACE.matchCount && matchResultDto.isContainsBonusNumber()) {
            return SECOND_PLACE;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchResultDto.getMatchCount())
                .filter(rank -> rank.containsBonus == matchResultDto.isContainsBonusNumber())
                .findAny()
                .orElse(NO_REWARD);
    }

    public static Map<LottoRank, String> getRankInfo() {
        return Arrays.stream(values())
                .filter(lottoRank -> lottoRank != NO_REWARD)
                .collect(Collectors.toMap(Function.identity(), LottoRank::generateRankMessage,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    private String generateRankMessage() {
        if (this == LottoRank.SECOND_PLACE) {
            return String.format("%d개 일치, 보너스 볼 일치(%d원)", getMatchCount(), getWinningAmount());
        }

        return String.format("%d개 일치 (%d원)", getMatchCount(), getWinningAmount());
    }

    public long calculateWinningAmount(int count) {
        return winningAmount * count;
    }
}

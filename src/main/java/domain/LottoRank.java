package domain;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;

public enum LottoRank {
    RANK_1(1, 2_000_000_000, 6, false),
    RANK_2(2, 30_000_000, 5, true),
    RANK_3(3, 1_500_000, 5, false),
    RANK_4(4, 50_000, 4, false),
    RANK_5(5, 5_000, 3, false),
    NONE(Integer.MAX_VALUE, 0, 0, false);

    private final int rank;
    private final int price;
    private final int matchedCount;
    private final boolean isBonus;

    LottoRank(final int rank, final int price, final int matchedCount, final boolean isBonus) {
        this.rank = rank;
        this.price = price;
        this.matchedCount = matchedCount;
        this.isBonus = isBonus;
    }

    // 일치하는 번호 갯수와 보너스 번호 일치 여부로 랭크 반환
    public static LottoRank findByMatchedCountAndIsBonus(final int matchedCount, final boolean isBonus) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matchedCount == matchedCount)
                .filter(rank -> Objects.equals(isBonus, rank.isBonus))
                .findAny()
                .orElse(NONE);
    }

    // 당첨 내역 리스트 반환
    public static List<LottoRank> findByAllWithoutNone() {
        return Arrays.stream(LottoRank.values())
                .sorted((r1, r2) -> r2.rank - r1.rank)
                .toList();
    }

    public int getPrice() {
        return price;
    }

    public int getMatchedCount() {
        return matchedCount;
    }
}

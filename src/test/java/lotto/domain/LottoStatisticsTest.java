package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LottoStatisticsTest {

    static class LottoStatisticsTestData {
        final Wallet wallet;
        final WinningInform winningInform;
        final Map<MatchRank, Integer> expectedRankCounts;
        final long expectedTotalPrize;

        LottoStatisticsTestData(Wallet wallet, WinningInform winningInform,
                                Map<MatchRank, Integer> expectedRankCounts, long expectedTotalPrize) {
            this.wallet = wallet;
            this.winningInform = winningInform;
            this.expectedRankCounts = expectedRankCounts;
            this.expectedTotalPrize = expectedTotalPrize;
        }
    }

    private static Stream<LottoStatisticsTestData> provideTestData() {
        // 케이스 1: 모든 MatchRank가 한개씩 있는 경우
        Lotto winningLotto = new Lotto(LottoNumber.from(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonus = new LottoNumber(7);
        WinningInform winningInform = new WinningInform(winningLotto, bonus);

        Lotto ticket1 = new Lotto(LottoNumber.from(List.of(1, 2, 3, 7, 8, 9))); //3개 매칭
        Lotto ticket2 = new Lotto(LottoNumber.from(List.of(1, 2, 3, 4, 7, 8))); // 4개 매칭
        Lotto ticket3 = new Lotto(LottoNumber.from(List.of(1, 2, 3, 4, 5, 8))); // 5개 매칭
        Lotto ticket4 = new Lotto(LottoNumber.from(List.of(1, 2, 3, 4, 5, 7))); // 5개 매칭 + 보너스
        Lotto ticket5 = new Lotto(LottoNumber.from(List.of(1, 2, 3, 4, 5, 6))); // 6개 매칭
        Wallet wallet = new Wallet(List.of(ticket1, ticket2, ticket3, ticket4, ticket5));

        Map<MatchRank, Integer> expectedCounts1 = new EnumMap<>(MatchRank.class);
        expectedCounts1.put(MatchRank.MATCH_THREE, 1);
        expectedCounts1.put(MatchRank.MATCH_FOUR, 1);
        expectedCounts1.put(MatchRank.MATCH_FIVE, 1);
        expectedCounts1.put(MatchRank.MATCH_BONUS, 1);
        expectedCounts1.put(MatchRank.MATCH_SIX, 1);
        expectedCounts1.put(MatchRank.NO_MATCH, 0);

        long expectedTotalPrize = Stream.of(MatchRank.values())
                .filter(rank -> rank != MatchRank.NO_MATCH)
                .mapToLong(MatchRank::getMoney)
                .sum();

        LottoStatisticsTestData data1 = new LottoStatisticsTestData(wallet, winningInform, expectedCounts1,
                expectedTotalPrize);

        // 케이스 2: 빈 Wallet
        Wallet emptyWallet = new Wallet(List.of());
        Map<MatchRank, Integer> expectedCounts2 = new EnumMap<>(MatchRank.class);
        for (MatchRank rank : MatchRank.values()) {
            expectedCounts2.put(rank, 0);
        }
        LottoStatisticsTestData data2 = new LottoStatisticsTestData(emptyWallet, winningInform, expectedCounts2, 0);

        return Stream.of(data1, data2);
    }

    @DisplayName("당첨번호와의 일치 개수 통계를 검증한다.")
    @ParameterizedTest
    @MethodSource("provideTestData")
    void testRankCounts(LottoStatisticsTestData testData) {
        LottoStatistics statistics = LottoStatistics.from(testData.wallet, testData.winningInform);
        assertThat(statistics.getRankCounts()).isEqualTo(testData.expectedRankCounts);
    }

    @DisplayName("총 당첨 금액을 검증한다.")
    @ParameterizedTest
    @MethodSource("provideTestData")
    void testTotalPrize(LottoStatisticsTestData testData) {
        LottoStatistics statistics = LottoStatistics.from(testData.wallet, testData.winningInform);
        assertThat(statistics.getTotalPrize()).isEqualTo(testData.expectedTotalPrize);
    }
}
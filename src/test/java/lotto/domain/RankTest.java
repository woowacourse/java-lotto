package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    
    private static final Lotto WIN_LOTTO = LottoFactory.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
    private static final WinningLotto WINNING_LOTTO = WinningLotto.of(WIN_LOTTO, LottoNumber.from(7));
    
    @ParameterizedTest
    @MethodSource("generateLotto")
    @DisplayName("당첨 확인 테스트")
    void searchRankTest(Lotto lotto, Rank rank) {
        
        // when
        Rank searchedRank = Rank.searchRank(WINNING_LOTTO, lotto);
        
        // then
        assertThat(searchedRank).isEqualTo(rank);
    }
    
    private static Stream<Arguments> generateLotto() {
        Lotto firstRankLotto = LottoFactory.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto secondRankLotto = LottoFactory.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto thirdRankLotto = LottoFactory.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 45));
        Lotto fourthRankLotto = LottoFactory.fromNumbers(Arrays.asList(1, 2, 3, 4, 44, 45));
        Lotto fifthRankLotto = LottoFactory.fromNumbers(Arrays.asList(1, 2, 3, 43, 44, 45));
        Lotto nothingRankLotto = LottoFactory.fromNumbers(Arrays.asList(1, 2, 42, 43, 44, 45));
        
        return Stream.of(
                Arguments.of(firstRankLotto, Rank.FIRST),
                Arguments.of(secondRankLotto, Rank.SECOND),
                Arguments.of(thirdRankLotto, Rank.THIRD),
                Arguments.of(fourthRankLotto, Rank.FOURTH),
                Arguments.of(fifthRankLotto, Rank.FIFTH),
                Arguments.of(nothingRankLotto, Rank.NOTHING)
        );
    }
}
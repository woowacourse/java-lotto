package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoStatisticResultTest {
    
    private static final WinningLotto WINNING_LOTTO = WinningLotto.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
    
    private static final List<Lotto> LOTTO_GROUP = new ArrayList<>();
    
    private static Stream<Arguments> generateLottoStaticResult() {
        LottoStatisticResult firstRankResult
                = generateLottoStatisticResult(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
        
        LottoStatisticResult secondRankResult
                = generateLottoStatisticResult(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 7)));
        
        LottoStatisticResult thirdRankResult
                = generateLottoStatisticResult(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 45)));
        
        LottoStatisticResult fourthRankResult
                = generateLottoStatisticResult(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 44, 45)));
        
        LottoStatisticResult fifthRankResult
                = generateLottoStatisticResult(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 43, 44, 45)));
        
        LottoStatisticResult nothingRankResult
                = generateLottoStatisticResult(Lotto.fromNumbers(Arrays.asList(1, 2, 42, 43, 44, 45)));
        
        return Stream.of(
                Arguments.of(firstRankResult, Rank.FIRST), Arguments.of(secondRankResult, Rank.SECOND),
                Arguments.of(thirdRankResult, Rank.THIRD), Arguments.of(fourthRankResult, Rank.FOURTH),
                Arguments.of(fifthRankResult, Rank.FIFTH), Arguments.of(nothingRankResult, Rank.NOTHING)
        );
    }
    
    private static LottoStatisticResult generateLottoStatisticResult(Lotto lotto) {
        LOTTO_GROUP.clear();
        LOTTO_GROUP.add(lotto);
        
        Lottos lottos = new Lottos(LOTTO_GROUP);
        
        return lottos.retrieveResults(WINNING_LOTTO);
    }
    
    @ParameterizedTest
    @MethodSource("generateLottoStaticResult")
    @DisplayName("당첨 확인 테스트")
    void searchRankTest(LottoStatisticResult result, Rank rank) {
        
        // when
        Long count = result.get(rank);
        
        // then
        assertThat(count).isEqualTo(1L);
    }
    
    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateIncomeRate() {
        // given
        List<Lotto> lottoGroup = new ArrayList<>();
        lottoGroup.add(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 43, 44, 45)));
        lottoGroup.add(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 44, 45)));
        lottoGroup.add(Lotto.fromNumbers(Arrays.asList(40, 41, 42, 43, 44, 45)));
        
        Lottos lottos = new Lottos(lottoGroup);
        LottoStatisticResult result = lottos.retrieveResults(WINNING_LOTTO);
        
        // when
        double incomeRate = result.calculateIncomeRate();
        
        // then
        assertThat(incomeRate).isEqualTo((double) 55000 / 3000);
    }
}

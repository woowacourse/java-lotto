package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    
    @Test
    void 로또_당첨_통계를_계산한다() {
        //given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 4, 5, 6)),
                new Lotto(List.of(1, 9, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 10, 15, 5, 6)),
                new Lotto(List.of(1, 2, 11, 4, 5, 6))
        );
        List<Integer> matchNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        Lottos sut = new Lottos(lottos);
        
        //when
        EnumMap<LottoPrize, Integer> result = sut.getStatistics(matchNumbers, bonusNumber);
        
        //then
        assertThat(result).containsEntry(LottoPrize.FIRST, 1);
        assertThat(result).containsEntry(LottoPrize.SECOND, 0);
        assertThat(result).containsEntry(LottoPrize.THIRD, 2);
        assertThat(result).containsEntry(LottoPrize.FOURTH, 1);
        assertThat(result).containsEntry(LottoPrize.FIFTH, 1);
    }
    
    @ParameterizedTest
    @MethodSource("provideLottoStaticsArguments")
    void 로또_당첨_수익률을_계산한다(List<Integer> matchNumbers, int bonusNumber, double expectedIncomeRate) {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        );
        Lottos sut = new Lottos(lottos);
        
        // when
        double result = sut.getIncomeRate(matchNumbers, bonusNumber, 10000);
        
        // then
        assertThat(result).isEqualTo(expectedIncomeRate);
    }
    
    private static Stream<Arguments> provideLottoStaticsArguments() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7, 200000f), //1등
                Arguments.of(List.of(2, 3, 4, 5, 6, 7), 8, 150f), //3등
                Arguments.of(List.of(3, 4, 5, 6, 7, 8), 9, 5f), //4등
                Arguments.of(List.of(4, 5, 6, 7, 8, 9), 10, 1f) //5등 2개
        );
    }
    
}
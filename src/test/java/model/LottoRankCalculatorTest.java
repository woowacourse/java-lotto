package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.numbers.LottoNumber;
import model.numbers.LottoNumbers;
import model.numbers.WinningLotto;
import model.rank.LottoRank;
import model.rank.LottoRankCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoRankCalculatorTest {

    private LottoRankCalculator lottoRankCalculator;
    private WinningLotto validWinningLotto;

    @BeforeEach
    void setUp() {
        lottoRankCalculator = new LottoRankCalculator();
        validWinningLotto = new WinningLotto(
                new LottoNumbers(fromIntegerListToLottoNumberList(List.of(1, 2, 3, 4, 5, 6))), new LottoNumber(7));
    }

    private static Stream<Arguments> provideLottoNumbersAndExpectedRanks() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), LottoRank.FIRST),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), LottoRank.SECOND),
                Arguments.of(List.of(1, 2, 3, 4, 5, 8), LottoRank.THIRD),
                Arguments.of(List.of(1, 2, 3, 4, 8, 9), LottoRank.FOURTH),
                Arguments.of(List.of(1, 2, 3, 8, 9, 10), LottoRank.FIFTH),
                Arguments.of(List.of(1, 2, 8, 9, 10, 11), null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideLottoNumbersAndExpectedRanks")
    void 조건에_알맞은_순위를_반환한다(List<Integer> numbers, LottoRank expectedRank) {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(fromIntegerListToLottoNumberList(numbers));

        // when
        LottoRank actualRank = lottoRankCalculator.calculate(lottoNumbers, validWinningLotto);

        // then
        assertThat(actualRank).isEqualTo(expectedRank);
    }

    @Test
    void 보너스_번호에_대한_확인이_요구되는_경우_알맞은_순위를_반환한다() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(fromIntegerListToLottoNumberList(List.of(1, 2, 3, 4, 5, 8)));

        // when
        LottoRank resultRank = lottoRankCalculator.calculate(lottoNumbers, validWinningLotto);

        // then
        assertThat(resultRank).isEqualTo(LottoRank.THIRD);
    }

    private List<LottoNumber> fromIntegerListToLottoNumberList(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}

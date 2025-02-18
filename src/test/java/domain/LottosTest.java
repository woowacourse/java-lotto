package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testUtil.StaticNumberPicker;
import util.NumberPicker;

class LottosTest {

    @Test
    void 로또들을_구입한다() {
        //given
        int purchaseMoney = 3000;
        NumberPicker numberPicker = new StaticNumberPicker(List.of(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(14, 15, 16, 13, 12, 9),
            List.of(43, 41, 40, 23, 35, 22)
        ));

        //when
        Lottos lottos = Lottos.purchase(purchaseMoney, numberPicker);

        //then
        assertThat(lottos.getLottoCount()).isEqualTo(3);
    }

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
        assertThat(result).containsAllEntriesOf(Map.of(
            LottoPrize.FIRST, 1,
            LottoPrize.SECOND, 0,
            LottoPrize.THIRD, 2,
            LottoPrize.FOURTH, 1,
            LottoPrize.FIFTH, 1
        ));
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

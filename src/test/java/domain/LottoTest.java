package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dto.LottoMatchResult;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testUtil.StaticNumberPicker;
import util.NumberPicker;

class LottoTest {

    @Test
    void 구입_금액만큼_로또를_구입한다() {
        // given
        int money = 4000;
        NumberPicker numberPicker = new StaticNumberPicker(List.of(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(14, 15, 16, 13, 12, 9),
            List.of(43, 41, 40, 23, 35, 22),
            List.of(9, 7, 13, 14, 16, 2)
        ));

        // when
        List<Lotto> result = Lotto.purchase(money, numberPicker);

        // then
        assertThat(result).hasSize(4);

    }

    @Test
    void 금액이_1000원_미만이면_예외를_발생한다() {
        int money = 999;

        NumberPicker numberPicker = new StaticNumberPicker(List.of(
            List.of(1, 2, 3, 4, 5, 6),
            List.of(14, 15, 16, 13, 12, 9),
            List.of(43, 41, 40, 23, 35, 22),
            List.of(9, 7, 13, 14, 16, 2)
        ));
        assertThatThrownBy(() -> Lotto.purchase(money, numberPicker))
            .isExactlyInstanceOf(IllegalStateException.class)
            .hasMessage("금액은 1000원 이상이여아 합니다.");
    }

    @ParameterizedTest
    @MethodSource("provideMatchNumbersAndBonusNumber")
    void 당첨된_로또번호_개수를_계산한다(List<Integer> matchNumbers, int bonusNumber, int expectedMatchCount,
                           boolean expectedBonusMatch) {
        //given
        Numbers numbers = Numbers.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto sut = new Lotto(numbers);

        //when
        LottoMatchResult result = sut.getMatchResult(matchNumbers, bonusNumber);

        //than
        assertThat(result.matchCount()).isEqualTo(expectedMatchCount);
        assertThat(result.isBonusMatch()).isEqualTo(expectedBonusMatch);
    }

    @Test
    void 로또_당첨_번호에_중복이_있으면_예외를_발생한다() {
        // given
        Numbers numbers = Numbers.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto sut = new Lotto(numbers);
        List<Integer> duplicatedMatchNumbers = List.of(1, 2, 3, 4, 5, 5);
        int bonusNumber = 6;

        // expected
        assertThatThrownBy(() -> sut.getMatchResult(duplicatedMatchNumbers, bonusNumber))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 당첨 번호는 중복되면 안됩니다.");
    }

    @Test
    void 로또_당첨_번호가_6개가_아니면_예외를_발생한다() {
        // given
        Numbers numbers = Numbers.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto sut = new Lotto(numbers);
        List<Integer> illegalCountMatchNumber = List.of(1, 2, 3, 4, 5);
        int bonusNumber = 6;

        // expected
        assertThatThrownBy(() -> sut.getMatchResult(illegalCountMatchNumber, bonusNumber))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 당첨 번호는 6개여야 합니다.");
    }

    @Test
    void 보너스_번호에_중복이_있으면_예외를_발생한다() {
        // given
        Numbers numbers = Numbers.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto sut = new Lotto(numbers);
        List<Integer> duplicatedMatchNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;

        // expected
        assertThatThrownBy(() -> sut.getMatchResult(duplicatedMatchNumbers, bonusNumber))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("보너스 번호는 중복되면 안됩니다.");
    }

    public static Stream<Arguments> provideMatchNumbersAndBonusNumber() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7, 6, false),
            Arguments.of(List.of(1, 2, 3, 4, 5, 7), 8, 5, false),
            Arguments.of(List.of(1, 2, 3, 4, 8, 9), 10, 4, false),
            Arguments.of(List.of(21, 23, 13, 11, 19, 20), 25, 0, false)
        );
    }
}

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    static Stream<Arguments> generateOutOfBoundLottos() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @DisplayName("로또 번호는 6개로 생성 가능")
    @Test
    void test1() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        new Lotto(numbers);
    }

    @DisplayName("로또 번호가 6개가 아니면 예외")
    @ParameterizedTest
    @MethodSource("generateOutOfBoundLottos")
    void test3(List<Integer> numbers) {
        // given

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 범위가 벗어나는 경우 예외")
    @Test
    void test4() {
        // given
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 46);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 숫자가 중복되는 경우 예외")
    @Test
    void test5() {
        // given
        List<Integer> numbers = List.of(1, 1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 존재 여부 파악 가능")
    @ParameterizedTest
    @CsvSource(value = {"1, true", "2, true", "3, true", "4, true", "5, true", "6, true", "7, false"})
    void discoverIsExistNumberOrNot(int number, boolean expected) {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number compare = new Number(number);

        // when
        final boolean result = lotto.isExist(compare);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("1등 결과를 올바르게 반환할 수 있다.")
    @Test
    void returnFirstPrize() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto winningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonusNumber = new Number(7);
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        // when
        LottoPrize lottoPrize = lotto.getLottoPrize(winningLotto);

        // then
        assertThat(lottoPrize).isSameAs(LottoPrize.FIRST);
    }

    @DisplayName("2등 결과를 올바르게 반환할 수 있다.")
    @Test
    void returnSecondPrize() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto winningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonusNumber = new Number(7);
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        // when
        LottoPrize lottoPrize = lotto.getLottoPrize(winningLotto);

        // then
        assertThat(lottoPrize).isSameAs(LottoPrize.SECOND);
    }

    @DisplayName("3등 결과를 올바르게 반환할 수 있다.")
    @Test
    void returnThirdPrize() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto winningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonusNumber = new Number(7);
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        // when
        LottoPrize lottoPrize = lotto.getLottoPrize(winningLotto);

        // then
        assertThat(lottoPrize).isSameAs(LottoPrize.THIRD);
    }

    @DisplayName("4등 결과를 올바르게 반환할 수 있다.")
    @Test
    void returnFourthPrize() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        Lotto winningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonusNumber = new Number(7);
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        // when
        LottoPrize lottoPrize = lotto.getLottoPrize(winningLotto);

        // then
        assertThat(lottoPrize).isSameAs(LottoPrize.FOURTH);
    }

    @DisplayName("5등 결과를 올바르게 반환할 수 있다.")
    @Test
    void returnFifthPrize() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        Lotto winningNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Number bonusNumber = new Number(7);
        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);

        // when
        LottoPrize lottoPrize = lotto.getLottoPrize(winningLotto);

        // then
        assertThat(lottoPrize).isSameAs(LottoPrize.FIFTH);
    }
}

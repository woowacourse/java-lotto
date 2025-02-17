package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.lottogeneratestrategy.LottoPickStrategy;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @ParameterizedTest
    @MethodSource("generateNotSixNumber")
    void 로또_번호의_개수가_6개가_아닌경우_예외를_반환한다(List<Integer> numbers) {
        assertThatThrownBy(() -> Lotto.createWinningLotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "1, true",
            "2, true",
            "7, false"
    })
    void 번호를_포함하는지_판단한다(int number, boolean expected) {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.createWinningLotto(numbers);
        //when
        boolean actual = lotto.contains(new Number(number));
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 로또_번호가_중복되는_경우_예외를_반환한다() {
        assertThatThrownBy(() -> Lotto.createWinningLotto(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복되지 않아야 합니다.");
    }

    @Test
    void 서로_다른_로또에서_몇개의_숫자가_같은지_계산할_수_있다() {
        //given
        Lotto lotto1 = Lotto.createWinningLotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.createRandomLotto((int size) -> List.of(1, 2, 3, 10, 11, 12));

        //when
        int actual = lotto1.calculateMatchCount(lotto2);

        //then
        assertThat(actual).isEqualTo(3);
    }

    static Stream<Arguments> generateNotSixNumber() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "1, true",
            "2, true",
            "3, true",
            "4, true",
            "5, true",
            "6, true",
            "7, false"
    })
    void 로또가_특정_번호를_포함하는지_판단할_수_있다(int value, boolean expected) {
        //given
        Lotto lotto = Lotto.createRandomLotto((int size) -> List.of(1, 2, 3, 4, 5, 6));
        Number number = new Number(value);
        //when
        boolean actual = lotto.contains(number);
        //then
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void 로또를_생성할_수_있다() {
        //given
        LottoPickStrategy fixNumberStrategy = (int size) -> List.of(1, 2, 3, 4, 5, 6);

        //when
        Lotto lotto = Lotto.createRandomLotto(fixNumberStrategy);

        //then
        assertThat(lotto)
                .extracting("numbers")
                .isEqualTo(Set.of(
                        new Number(1), new Number(2), new Number(3), new Number(4), new Number(5), new Number(6)
                ));
    }
}

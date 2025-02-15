package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void 로또_번호의_개수가_6개가_아닌경우_예외를_반환한다(Set<Number> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
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
        Set<Number> numbers = Set.of(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5),
                new Number(6));
        Lotto lotto = new Lotto(numbers);
        //when
        boolean actual = lotto.contains(new Number(number));
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 로또_번호가_중복되는_경우_예외를_반환한다() {
        //given
        List<Number> numbers = List.of(
                new Number(1),
                new Number(1),
                new Number(2),
                new Number(3),
                new Number(4),
                new Number(5));

        //when then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복되지 않아야 합니다.");
    }

    @Test
    void 서로_다른_로또에서_몇개의_숫자가_같은지_계산할_수_있다() {
        //given
        Lotto lotto1 = new Lotto(List.of(
                new Number(1),
                new Number(2),
                new Number(3),
                new Number(4),
                new Number(5),
                new Number(6)
        ));
        Lotto lotto2 = new Lotto(List.of(
                new Number(1),
                new Number(2),
                new Number(3),
                new Number(10),
                new Number(11),
                new Number(12)
        ));

        //when
        int actual = lotto1.calculateMatchCount(lotto2);

        //then
        assertThat(actual).isEqualTo(3);
    }

    static Stream<Arguments> generateNotSixNumber() {
        return Stream.of(
                Arguments.of(Set.of(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5))),
                Arguments.of(
                        Set.of(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5), new Number(6),
                                new Number(7)))
        );
    }
}

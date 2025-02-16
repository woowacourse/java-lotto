package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.numbergenerator.NumberGenerator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Test
    void 숫자_생성_전략에_따라_로또를_생성할_수_있다() {
        //given
        NumberGenerator fixNumberGenerator = new NumberGenerator() {
            int index = 0;
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

            @Override
            public int generate() {
                int number = numbers.get(index++);
                index %= numbers.size();
                return number;
            }
        };

        //when
        Lotto lotto = Lotto.create(fixNumberGenerator);

        //then
        assertThat(lotto)
                .extracting("numbers")
                .isEqualTo(Set.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)
                ));
    }

    @ParameterizedTest
    @MethodSource("generateNotSixLottoNumber")
    void 로또_번호의_개수가_6개가_아닌경우_예외를_반환한다(Set<LottoNumber> numbers) {
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
        Set<LottoNumber> numbers = Set.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5),
                new LottoNumber(6));
        Lotto lotto = new Lotto(numbers);
        //when
        boolean actual = lotto.contains(new LottoNumber(number));
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 로또_번호가_중복되는_경우_예외를_반환한다() {
        //given
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1),
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5));

        //when then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복되지 않아야 합니다.");
    }

    @Test
    void 서로_다른_로또에서_몇개의_숫자가_같은지_계산할_수_있다() {
        //given
        Lotto lotto1 = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        Lotto lotto2 = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(10),
                new LottoNumber(11),
                new LottoNumber(12)
        ));

        //when
        int actual = lotto1.calculateMatchCount(lotto2);

        //then
        assertThat(actual).isEqualTo(3);
    }

    static Stream<Arguments> generateNotSixLottoNumber() {
        return Stream.of(
                Arguments.of(Set.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5))),
                Arguments.of(
                        Set.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                                new LottoNumber(5), new LottoNumber(6),
                                new LottoNumber(7)))
        );
    }
}

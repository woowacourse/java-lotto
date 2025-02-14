package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @ParameterizedTest
    @DisplayName("숫자가 6개가 아니면 예외가 발생한다.")
    @MethodSource("makeNotSixNumbers")
    void createLottoWithWrongSizeNumbers(List<LottoNumber> numbers) {
        assertThatThrownBy(() ->
                new Lotto(numbers)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자가 6개가 아닙니다.");
    }

    private static Stream<Arguments> makeNotSixNumbers() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7)
        ).map(numbers ->
                Arguments.of(numbers.stream()
                        .map(LottoNumber::new)
                        .toList())
        );
    }


    @DisplayName("다른 로또와 매칭되는 번호의 개수를 계산해 반환한다.")
    @Test
    void 다른_로또와_매칭되는_번호의_개수를_계산해_반환한다() {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        Lotto otherLotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        assertThat(lotto.calculateMatchingCount(otherLotto)).isEqualTo(6);
    }

    @DisplayName("번호를 알려주면 존재 여부를 알려준다.")
    @CsvSource(value = {"1,true", "7,false"})
    @ParameterizedTest
    void 번호를_알려주면_존재_여부를_알려준다(int number, boolean expected) {
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        assertThat(lotto.has(new LottoNumber(number))).isEqualTo(expected);
    }

}

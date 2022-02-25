package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.vo.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoTest {

    @Test
    @DisplayName("로또 번호는 6자리 숫자로 생성된다.")
    void createSixSizeNumbers() {
        List<Number> numbers = givenNumbers(1, 2, 3, 4, 5, 6);

        assertThat(new Lotto(numbers)).isNotNull();
    }

    @Test
    @DisplayName("로또 번호는 5자리 숫자가 전달되면 예외가 발생한다.")
    void throwExceptionWhenFiveSizeNumbers() {
        List<Number> numbers = givenNumbers(1, 2, 3, 4, 5);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lotto(numbers))
            .withMessageMatching("로또 번호는 6자리 이어야 한다.");
    }

    @Test
    @DisplayName("로또 번호는 7자리 숫자가 전달되면 예외가 발생한다.")
    void throwExceptionWhenSevenSizeNumbers() {
        List<Number> numbers = givenNumbers(1, 2, 3, 4, 5, 6, 7);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lotto(numbers))
            .withMessageMatching("로또 번호는 6자리 이어야 한다.");
    }

    @Test
    @DisplayName("로또 번호가 중복될 경우 예외가 발생한다.")
    void throwExceptionWhenDuplicateNumbers() {
        List<Number> numbers = givenNumbers(1, 2, 3, 4, 5, 5);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lotto(numbers))
            .withMessageMatching("로또 번호는 중복될 수 없다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"6,true", "7,false"})
    @DisplayName("해당하는 숫자가 포함되어 있는지 확인한다.")
    void checkNotContainsBonusNumber(int number, boolean expected) {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 6));

        assertThat(lotto.contains(new Number(number))).isEqualTo(expected);
    }

    @Test
    @DisplayName("외부에서 생성된 번호가 변경되어도 생성된 로또의 번호는 바뀌지 않는다.")
    void immutabilityLotto() {
        List<Number> numbers = givenNumbers(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        Number addNumber = new Number(7);
        numbers.add(addNumber);

        assertThat(lotto.contains(addNumber)).isFalse();
    }

    private static List<Number> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(Number::new)
            .collect(Collectors.toList());
    }
}

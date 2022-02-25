package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.vo.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("보너스 숫자가 포함되어있는지 확인한다.")
    void checkContainsBonusNumber() {
        Number bonus = new Number(7);
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 7));

        assertThat(lotto.containsNumber(bonus)).isTrue();
    }

    @Test
    @DisplayName("보너스 숫자가 없는지 확인한다.")
    void checkNotContainsBonusNumber() {
        Number bonus = new Number(6);
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 7));

        assertThat(lotto.containsNumber(bonus)).isFalse();
    }

    @Test
    @DisplayName("일치하는 숫자의 개수를 반환한다.")
    void countMatchNumbers() {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 7));
        Lotto anotherLotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 7));

        assertThat(lotto.countMatchNumbers(anotherLotto))
                .isEqualTo(6);
    }

    private static List<Number> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(Number::new)
                .collect(Collectors.toList());
    }
}
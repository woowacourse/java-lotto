package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Test
    @DisplayName("로또 번호는 6자리 숫자로 생성된다.")
    void createSixSizeNumbers() {
        List<LottoNumber> numbers = givenNumbers(1, 2, 3, 4, 5, 6);

        assertThat(new Lotto(numbers)).isNotNull();
    }

    @Test
    @DisplayName("로또 번호는 5자리 숫자가 전달되면 예외가 발생한다.")
    void throwExceptionWhenFiveSizeNumbers() {
        List<LottoNumber> numbers = givenNumbers(1, 2, 3, 4, 5);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessageMatching("로또 번호는 6자리 이어야 한다.");
    }

    @Test
    @DisplayName("로또 번호는 7자리 숫자가 전달되면 예외가 발생한다.")
    void throwExceptionWhenSevenSizeNumbers() {
        List<LottoNumber> numbers = givenNumbers(1, 2, 3, 4, 5, 6, 7);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessageMatching("로또 번호는 6자리 이어야 한다.");
    }

    @Test
    @DisplayName("로또 번호가 중복될 경우 예외가 발생한다.")
    void throwExceptionWhenDuplicateNumbers() {
        List<LottoNumber> numbers = givenNumbers(1, 2, 3, 4, 5, 5);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Lotto(numbers))
                .withMessageMatching("로또 번호는 중복될 수 없다.");
    }

    @Test
    @DisplayName("숫자가 포함 되어있는지 확인한다.")
    void checkContainsNumber() {
        LottoNumber bonus = new LottoNumber(7);
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 7));

        assertThat(lotto.containsNumber(bonus)).isTrue();
    }

    @Test
    @DisplayName("숫자가 미포함 되었는지 확인한다.")
    void checkNotContainsNumber() {
        LottoNumber bonus = new LottoNumber(6);
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 7));

        assertThat(lotto.containsNumber(bonus)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("numbersAndMatchCount")
    @DisplayName("일치하는 숫자의 개수를 반환한다.")
    void countMatchNumbers(List<LottoNumber> numbers, int matchCount) {
        Lotto lotto = new Lotto(givenNumbers(1, 2, 3, 4, 5, 7));
        Lotto anotherLotto = new Lotto(numbers);

        assertThat(lotto.countMatchNumbers(anotherLotto))
                .isEqualTo(matchCount);
    }

    private static Stream<Arguments> numbersAndMatchCount() {
        return Stream.of(
                Arguments.of(givenNumbers(1, 2, 3, 4, 5, 7), 6),
                Arguments.of(givenNumbers(1, 2, 3, 4, 5, 10), 5),
                Arguments.of(givenNumbers(1, 2, 3, 4, 10, 11), 4),
                Arguments.of(givenNumbers(1, 2, 3, 10, 11, 12), 3),
                Arguments.of(givenNumbers(1, 2, 10, 11, 12, 13), 2),
                Arguments.of(givenNumbers(1, 10, 11, 12, 13, 14), 1),
                Arguments.of(givenNumbers(10, 11, 12, 13, 14, 15), 0)
        );
    }

    private static List<LottoNumber> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
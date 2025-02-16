package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {
    @DisplayName("중복이 없는 6개의 숫자로 로또 객체를 생성한다")
    @Test
    void createLottoWithDistinctNumbers() {
        List<Integer> distinctNumbers = Arrays.asList(1, 2, 3, 4, 5, 45);

        assertThatCode(() -> Lotto.of(distinctNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("6개의 숫자에 중복이 있을 경우 예외를 던진다")
    @Test
    void throwExceptionWhenCreateLottoWithDuplicateNumbers() {
        List<Integer> duplicateNumber = Arrays.asList(1, 2, 3, 4, 6, 6);

        assertThatThrownBy(() -> Lotto.of(duplicateNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("NULL일 경우 예외를 던진다")
    @Test
    void throwExceptionWithNull() {
        assertThatThrownBy(() -> Lotto.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @DisplayName("6개의 숫자에 중복이 있을 경우 예외를 던진다")
    @Test
    void throwExceptionIfSizeOfLottoIsNot6() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        assertThatThrownBy(() -> Lotto.of(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @DisplayName("로또 번호가 범위 밖이면 예외를 던진다")
    @ValueSource(ints = {0, 46, -1, Integer.MAX_VALUE})
    @ParameterizedTest
    void throwExceptionWithOutOfRangeLottoNumbers(int number) {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        numbers.add(number);

        assertThatThrownBy(() -> Lotto.of(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45사이여야 합니다.");
    }

    @DisplayName("로또 객체 생성 시 숫자들을 정렬한다")
    @Test
    void createOrderedLotto() {
        List<Integer> unorderedNumbers = Arrays.asList(43, 26, 1, 3, 5, 2);
        Lotto lotto = Lotto.of(unorderedNumbers);

        List<Integer> orderedNumbers = unorderedNumbers.stream().sorted().toList();
        Lotto orderedLotto = Lotto.of(orderedNumbers);
        assertThat(lotto.toString()).isEqualTo(orderedLotto.toString());
    }
}
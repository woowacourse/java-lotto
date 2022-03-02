package lotto.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("중복된 숫자로 생성시 예외 발생")
    void incorrect() {
        // given
        List<Integer> numbers = getNumbers(2, 2, 3, 4, 5, 6);

        // then
        assertThatThrownBy(() -> new LottoNumbers(numbers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 개수는 중복이 불가능합니다.");
    }

    @Test
    @DisplayName("숫자가 6개 미만")
    void incorrect4() {
        // given
        List<Integer> numbers = getNumbers(2, 5, 6);

        // then
        assertThatThrownBy(() -> new LottoNumbers(numbers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 개수는 6개로 제한됩니다.");
    }

    @Test
    @DisplayName("중복된 숫자가 없는 경우")
    void correct() {
        // given
        List<Integer> numbers = getNumbers(1, 2, 3, 4, 5, 6);

        // then
        assertThatNoException().isThrownBy(() -> new LottoNumbers(numbers));
    }

    @Test
    @DisplayName("equals 확인")
    void test() {
        // given
        List<Integer> numbers1 = getNumbers(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = getNumbers(5, 2, 1, 3, 4, 6);

        LottoNumbers lottoNumbers1 = new LottoNumbers(numbers1);
        LottoNumbers lottoNumbers2 = new LottoNumbers(numbers2);

        // then
        assertThat(lottoNumbers1).isEqualTo(lottoNumbers2);
    }


    private List<Integer> getNumbers(int... numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }
}

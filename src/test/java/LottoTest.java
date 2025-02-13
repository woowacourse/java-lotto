import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또 번호는 6개만 가능")
    @Test
    void test1() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = new Lotto(numbers);

        // then
        assertThat(lotto.getNumbers()).containsAll(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("로또 번호는 오름차순으로 저장된다.")
    @Test
    void test2() {
        // given & when
        Lotto lotto = new Lotto(List.of(6, 5, 1, 2, 3, 4));

        // then
        assertThat(lotto.getNumbers()).isSorted();
    }

    @DisplayName("로또 번호가 6개가 아니면 예외")
    @Test
    void test3() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

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
}
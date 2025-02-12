import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    void 로또_번호는_6개만_가능() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = new Lotto(numbers);

        // then
        assertThat(lotto.getNumbers()).containsAll(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 로또_번호가_6개가_아니면_예외() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_범위가_벗어나는_경우_예외() {
        // given
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 46);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자가_중복되는_경우_예외() {
        // given
        List<Integer> numbers = List.of(1, 1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
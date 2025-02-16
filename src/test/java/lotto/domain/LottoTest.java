package lotto.domain;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    void 로또를_정상적으로_생성한다() {
        // Given
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // When & Then
        Assertions.assertThatCode(() -> new Lotto(numbers))
                .doesNotThrowAnyException();
    }

    @Test
    void 두_로또를_비교하여_일치하는_로또_번호_개수를_반환한다() {
        // Given
        final Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        final Lotto lotto = new Lotto(Arrays.asList(7, 2, 3, 4, 5, 6));

        // When
        final int count = winningLotto.countMatchingLottoNumber(lotto);

        // Then
        Assertions.assertThat(count).isEqualTo(5);
    }

    @Test
    void 로또_번호의_개수가_6개가_아니면_예외가_발생한다() {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호의 개수는 6개여야 합니다.");
    }

    @Test
    void 로또_번호가_중복될_경우_예외가_발생한다() {
        // Given

        /// When & Then
        Assertions.assertThatThrownBy(() -> new Lotto(Arrays.asList(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복되지 않은 로또 번호를 입력해 주세요.");
    }
}

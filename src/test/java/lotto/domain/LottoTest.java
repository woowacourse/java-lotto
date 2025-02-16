package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {

    @Test
    void 로또를_정상적으로_생성한다() {
        // Given
        final Set<Integer> numbers = Set.of(1, 2, 3, 4, 5, 6);

        // When & Then
        Assertions.assertThatCode(() -> new Lotto(numbers))
                .doesNotThrowAnyException();
    }

    @Test
    void 로또_번호의_개수가_6개가_아니면_예외가_발생한다() {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> new Lotto(Set.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복되지 않은 6개의 숫자여야 합니다.");
    }

    @Test
    void 로또_번호가_중복될_경우_예외가_발생한다() {
        // Given

        /// When & Then
        Assertions.assertThatThrownBy(() -> new Lotto(new HashSet<>(List.of(1, 1, 2, 3, 4, 5))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복되지 않은 6개의 숫자여야 합니다.");
    }

    @Test
    void 두_로또를_비교하여_일치하는_로또_번호_개수를_반환한다() {
        // Given
        final Lotto winningLotto = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        final Lotto lotto = new Lotto(Set.of(7, 2, 3, 4, 5, 6));

        // When
        final int count = winningLotto.countMatchingLottoNumber(lotto);

        // Then
        Assertions.assertThat(count).isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource({
            "1, true",
            "7, false"
    })
    void 로또가_주어진_로또_번호를_포함하고_있으면_true를_반환한다(final int value, final boolean expected) {
        // Given
        final Lotto winningLotto = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        final LottoNumber lottoNumber = LottoNumber.from(value);

        // When & Then
        Assertions.assertThat(winningLotto.contains(lottoNumber)).isEqualTo(expected);
    }

    @Test
    void 정렬된_로또_번호를_얻는다() {
        // Given
        final Lotto lotto = new Lotto(Set.of(5, 2, 4, 3, 6, 1));

        // When
        List<Integer> sortedNumbers = lotto.getSortedNumbers();

        // Then
        Assertions.assertThat(sortedNumbers).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }
}

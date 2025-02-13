package lotto.domain;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    void 로또를_정상적으로_생성한다() {
        // Given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // When & Then
        Assertions.assertThatCode(() -> new Lotto(numbers))
                .doesNotThrowAnyException();
    }

    @Test
    void 두_로또를_비교하여_일치하는_로또_번호_개수를_반환한다() {
        // Given
        Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(Arrays.asList(7, 2, 3, 4, 5, 6));

        // When
        int count = winningLotto.countMatchingLottoNumber(lotto);

        // Then
        Assertions.assertThat(count).isEqualTo(5);
    }
}

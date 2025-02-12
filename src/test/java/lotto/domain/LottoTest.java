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
}

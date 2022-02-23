package lotto;

import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("구매한 로또 개수는 1개 이상이어야 한다.")
    void createLottos() {
        List<Lotto> lottos = List.of(new Lotto(givenNumbers(1, 2, 3, 4, 5, 6)));

        assertThat(new Lottos(lottos)).isNotNull();
    }

    @Test
    @DisplayName("구매한 로또 개수는 1개 이상이어야 한다.")
    void throwExceptionWhenEmptyLottos() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Lottos(Collections.emptyList()))
            .withMessage("구매한 로또 개수는 1개 이상이어야 한다.");
    }

    private static List<Number> givenNumbers(int... numbers) {
        return Arrays.stream(numbers)
            .mapToObj(Number::new)
            .collect(Collectors.toList());
    }
}

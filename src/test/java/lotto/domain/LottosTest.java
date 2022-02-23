package lotto.domain;

import static lotto.domain.LottoTest.createLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    void constructor() {
        final Lottos lottos = new Lottos(Stream.of(createLottoNumbers(1, 2, 3, 4, 5, 6),
                        createLottoNumbers(2, 3, 4, 5, 6, 7))
                .map(Lotto::new)
                .collect(Collectors.toList()));
        assertThat(lottos).isInstanceOf(Lottos.class);
    }
}

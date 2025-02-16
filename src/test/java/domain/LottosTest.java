package domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import domain.lotto.Lottos;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또를_발행한다() {
        Lottos lottos = Lottos.issue(
                2000,
                (minNumber, maxNumber, maxSize) -> List.of(1, 2, 3, 4, 5, 6)
        );

        assertThat(lottos.getLottos().getFirst().getBallNumbers())
                .isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

}
package factory;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.Lottos;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {
    private static final int VALID_LOTTO_SIZE = 6;

    @Test
    void createAutoLottosByQuantity() {
        // given
        Lottos autoLottosByQuantity = LottoFactory.createAutoLottosByQuantity(5);

        // when
        List<Lotto> lottos = autoLottosByQuantity.getLottos();
        boolean hasInvalidSizedLotto = lottos.stream()
                .anyMatch(lotto -> lotto.getLottoNumbers().size() != VALID_LOTTO_SIZE);

        // then
        assertThat(hasInvalidSizedLotto).isFalse();
    }
}

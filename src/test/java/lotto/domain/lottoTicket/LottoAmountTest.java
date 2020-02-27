package lotto.domain.lottoTicket;

import lotto.exception.InvalidManualLottoAmountException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoAmountTest {
    @Test
    void initFailTest() {
        assertThatThrownBy(() -> new LottoAmount(10, 11))
                .isInstanceOf(InvalidManualLottoAmountException.class);
    }
}

package lotto.domain;

import lotto.domain.lotto.LottoNumbersFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumbersFactoryTest {
    @Test
    @DisplayName("자동 로또 숫자들 갯수 테스트")
    void generateAutoLottoTicket() {
        assertThat(LottoNumbersFactory.generateAutoLottoNumbers()
                .size()).isEqualTo(6);
    }
}

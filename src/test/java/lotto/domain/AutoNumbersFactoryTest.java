package lotto.domain;

import lotto.domain.lotto.AutoNumbersFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoNumbersFactoryTest {
    @Test
    @DisplayName("자동 로또 숫자들 갯수 테스트")
    void generateAutoLottoTicket() {
        assertThat(AutoNumbersFactory.generateAutoLottoTicket()
                .size()).isEqualTo(6);
    }
}

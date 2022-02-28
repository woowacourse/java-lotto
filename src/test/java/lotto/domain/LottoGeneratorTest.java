package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    @DisplayName("수동 로또 구매시 입력한 금액보다 더 많은 개수 구매로 인한 에러")
    @Test
    void buyLottosManualOverInputMoney() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoGenerator.of(new Money(14000), 15))
                .withMessage("[ERROR] 금액이 부족합니다.");
    }
}

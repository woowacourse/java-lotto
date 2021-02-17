package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class BonusNumberTest {

    @DisplayName("BonusNumber정상 생성 테스트")
    @Test
    void BonusNumber_생성된다() {
        assertThatCode(() -> BonusNumber.of(4))
                .doesNotThrowAnyException();
    }
}
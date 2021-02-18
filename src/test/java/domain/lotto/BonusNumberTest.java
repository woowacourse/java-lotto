package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BonusNumberTest {

    @DisplayName("BonusNumber정상 생성 테스트")
    @Test
    void BonusNumber_생성된다() {
        assertThatCode(() -> BonusNumber.of(4))
                .doesNotThrowAnyException();
    }

    @DisplayName("BonusNumber isSameNumber 테스트")
    @Test
    void BonusNumber_isSameNumber_테스트() {
        assertThat(BonusNumber.of(5).isSameNumber(new LottoNumber(5))).isTrue();
    }
}
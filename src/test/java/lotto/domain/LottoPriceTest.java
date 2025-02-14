package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoPriceTest {

    @Test
    void 로또_구입_금액을_입력한다() {
        // Given
        int rawPrice = 1000;

        // When & Then
        Assertions.assertThatCode(() -> new LottoPrice(rawPrice))
                .doesNotThrowAnyException();
    }

    @Test
    void 천원_미만이면_예외가_발생한다() {
        // Given
        int rawPrice = 999;

        // When & Then
        Assertions.assertThatThrownBy(() -> new LottoPrice(rawPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 구입 금액은 1000원 이상이어야 합니다.");
    }

    @Test
    void 구입_금액에_해당하는_로또_개수를_출력한다() {
        // Given
        LottoPrice lottoPrice = new LottoPrice(5300);

        // When & Then
        Assertions.assertThat(lottoPrice.calculateLottoCount()).isEqualTo(5);
    }

}


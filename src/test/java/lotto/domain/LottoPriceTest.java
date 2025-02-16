package lotto.domain;

import static org.assertj.core.api.Assertions.within;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoPriceTest {

    @Test
    void 로또_구입_금액을_입력한다() {
        // Given

        // When
        final LottoPrice lottoPrice = new LottoPrice(1000);

        // Then
        Assertions.assertThatCode(() -> {
            Assertions.assertThat(lottoPrice);
        }).doesNotThrowAnyException();
    }

    @Test
    void 천원_미만이면_예외가_발생한다() {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> new LottoPrice(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 구입 금액은 1000원 이상이어야 합니다.");
    }

    @Test
    void 구입_금액에_해당하는_로또_개수를_출력한다() {
        // Given
        final LottoPrice lottoPrice = new LottoPrice(5300);

        // When & Then
        Assertions.assertThat(lottoPrice.calculateLottoCount()).isEqualTo(5);
    }

    @Test
    void 입력받은_금액을_구입_금액으로_나눈_실수값을_반환한다() {
        // Given
        final LottoPrice lottoPrice = new LottoPrice(14000);

        // When & Then
        Assertions.assertThat(lottoPrice.divideFrom(5000)).isCloseTo(0.35, within(0.01));
    }
}


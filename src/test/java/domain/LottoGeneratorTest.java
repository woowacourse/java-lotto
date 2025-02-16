package domain;

import static error.ErrorMessage.INVALID_LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoGeneratorTest {
    LottoGenerator lottoGenerator = new LottoGenerator();

    @ParameterizedTest
    @ValueSource(ints = {1, 1234})
    @DisplayName("1000원 단위가 아닐 경우 예외를 발생시킨다.")
    void 구입금액이_1000원_단위가_아닌_경우(int purchaseAmount) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            int lottoQuantity = lottoGenerator.purchaseLottoByAmount(purchaseAmount);
        });
        assertThat(exception.getMessage()).isEqualTo(INVALID_LOTTO_PRICE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 10000})
    @DisplayName("1000원 단위인 경우 성공한다.")
    void 구입금액이_1000원_단위인_경우(int purchaseAmount) {
        int expectedQuantity = purchaseAmount / 1000;
        int lottoQuantity = lottoGenerator.purchaseLottoByAmount(purchaseAmount);
        assertEquals(expectedQuantity, lottoQuantity);
    }
}

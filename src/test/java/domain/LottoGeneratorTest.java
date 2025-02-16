package domain;

import static error.ErrorMessage.INVALID_LOTTO_NUMBER_INPUT;
import static error.ErrorMessage.INVALID_LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @ParameterizedTest
    @ValueSource(strings = {",1,2,3,4,5,6", "1,,2,3,,4,5,6"})
    @DisplayName("당첨 번호를 쉼표로 구분하지 않을 경우 예외가 발생한다.")
    void 당첨_번호를_쉼표로_구분하지_않을_경우_예외가_발생한다(String winningNumberInput) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Lotto winningLotto = lottoGenerator.createWinningLotto(winningNumberInput);
        });
        assertThat(exception.getMessage()).isEqualTo(INVALID_LOTTO_NUMBER_INPUT.getMessage());
    }

    @DisplayName("당첨 번호를 쉼표로 구분한 경우 당첨 로또가 생성된다.")
    @Test
    void 당첨_번호를_쉼표로_구분한_경우_당첨_로또가_생성된다() {
        String winningNumberInput = "6, 5, 4, 3, 2, 1";
        List<Integer> expectedNumbers = List.of(6, 5, 4, 3, 2, 1);
        Lotto winningLotto = lottoGenerator.createWinningLotto(winningNumberInput);
        assertEquals(expectedNumbers, winningLotto.getNumbers());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 1000})
    @DisplayName("구입 갯수에 맞게 로또가 생성된다.")
    void 구입_갯수에_맞게_로또가_생성된다(int quantity) {
        List<Lotto> lottoBundle = lottoGenerator.createLottoBundleForQuantity(quantity);
        assertEquals(quantity, lottoBundle.size());
    }
}

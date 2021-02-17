package lotto.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class LottoStoreTest {

    @ParameterizedTest
    @DisplayName("구입가능한 로또 매수 계산")
    @CsvSource(value = {"14000,14", "10200,10", "500,0", "0,0"})
    void calculateAffordableLottoPiecesTest(int money, int expectedLottoPieces) {
        LottoStore lottoStore = new LottoStore();
        int calculatedLottoPieces = lottoStore.calculateAffordableLottoPieces(money);
        assertThat(calculatedLottoPieces).isEqualTo(expectedLottoPieces);
    }
}

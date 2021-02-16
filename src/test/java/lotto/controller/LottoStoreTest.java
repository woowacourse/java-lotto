package lotto.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoStoreTest {

    @Test
    @DisplayName("구입가능한 로또 매수 계산")
    void calculateAffordableLottoPiecesTest() {
        int money = 14000;
        int expectedLottoPieces = 14;
        LottoStore lottoStore = new LottoStore();
        int calculatedLottoPieces = lottoStore.calculateAffordableLottoPieces(money);
        assertThat(calculatedLottoPieces).isEqualTo(expectedLottoPieces);
    }
}

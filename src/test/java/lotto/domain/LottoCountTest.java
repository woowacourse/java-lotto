package lotto.domain;

import lotto.exception.ExceedMoneyException;
import lotto.exception.InvalidRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
    private PurchaseMoney money;

    @BeforeEach
    void init() {
        this.money = new PurchaseMoney("15000");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-3"})
    void negativeInputTest(String input) {
        assertThatThrownBy(() -> {
            new LottoCount(input, money.parseToPiece());
        }).isInstanceOf(InvalidRangeException.class)
                .hasMessageMatching("음수는 입력할 수 없습니다.");
    }

    @Test
    void overNumberManual() {
        assertThatThrownBy(() -> {
            new LottoCount("16", money.parseToPiece());
        }).isInstanceOf(ExceedMoneyException.class)
                .hasMessageMatching("15장 이하만 구매가 가능합니다.");
    }

    @Test
    void manualCountTest() {
        assertThat(new LottoCount("2", money.parseToPiece())
                .getManualLotto())
                .isEqualTo(2);
    }

    @Test
    void autoCountTest() {
        assertThat(new LottoCount("2", money.parseToPiece())
                .getAutoLottoCount())
                .isEqualTo(13);
    }
}

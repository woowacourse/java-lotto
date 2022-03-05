package model.LottoCount;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TotalLottoCountTest {

    @ParameterizedTest
    @DisplayName("투입 금액이 천원보다 작으면 오류를 발생한다.")
    @CsvSource(value = {"-1 : 2", "0 : 4", "900 : 4"}, delimiter = ':')
    void generateMoney_UnderThanThousand(int money, int manualLottoCount) {
        assertThatThrownBy(() -> new TotalLottoCount(money, manualLottoCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 투입 금액은 천원 단위의 금액으로 입력하세요.");
    }

    @Test
    @DisplayName("투입 금액이 천원 단위가 아니면 오류를 발생한다.")
    void generateMoney_NotThousandUnit() {
        assertThatThrownBy(() -> new TotalLottoCount(14500, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 투입 금액은 천원 단위의 금액으로 입력하세요.");
    }

    @Test
    @DisplayName("투입 금액이 수동 구매 로또 개수의 합보다 작으면 오류를 발생한다.")
    void generateMoney_LessThanManualLottoPriceSum() {
        assertThatThrownBy(() -> new TotalLottoCount(2000, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 투입 금액이 수동 구매 로또 가격합 보다 적습니다.");
    }
}

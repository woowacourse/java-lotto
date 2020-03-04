package lotto;

import domain.LottoCount;
import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoCountTest {
    @Test
    @DisplayName("구매금액이 주어질 때 로또 구매 개수 확인")
    void calculateLottoCountTest() {
        Money money = new Money(14800);
        LottoCount lottoCount = new LottoCount(money.getLottoCount(), 3);
        assertThat(lottoCount.getTotalCount()).isEqualTo(14);
        assertThat(lottoCount.getAutoCount()).isEqualTo(11);
        assertThat(lottoCount.getManualCount()).isEqualTo(3);
    }

    @ParameterizedTest
    @DisplayName("0이상 lottoCount 보다 작은 범위 벗어나면 예외 발생")
    @ValueSource(ints = {-1, 11, 5})
    void checkManualAvailableTest(int manualCount) {
        assertThatThrownBy(() -> {
            Money money = new Money(4000);
            new LottoCount(money.getLottoCount(), manualCount);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("수동으로 구매 가능한 로또 개수가 아닙니다. 현재 입력 : %s ", manualCount));
    }
}

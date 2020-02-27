package lotto;

import domain.LottoCount;
import domain.ManualCount;
import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualCountTest {

    @Test
    @DisplayName("수동 로또 구매 개수에 문자가 입력되었는 지 확인")
    void checkNotNumberTest() {
        assertThatThrownBy(() -> {
            new ManualCount("수동", new LottoCount(1));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("문자는 입력될 수 없습니다. 현재 입력 : %s ", "수동"));
    }

    @ParameterizedTest
    @DisplayName("0이상 lottoCount 보다 작은 범위 벗어나면 예외 발생")
    @ValueSource(strings = {"-1", "11", "5"})
    void checkManualAvailableTest(String manualCount) {
        assertThatThrownBy(() -> {
            Money money = new Money("4000");
            LottoCount lottoCount = new LottoCount(money.getLottoCount());
            new ManualCount(manualCount, lottoCount);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("수동으로 구매 가능한 로또 개수가 아닙니다. 현재 입력 : %s ", manualCount));
    }

}

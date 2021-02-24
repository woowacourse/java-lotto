package lotto.domain;

import lotto.domain.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualAmountTest {

    private Money money;

    @BeforeEach
    void setUp() {
        money = new Money("4000");
    }

    @Test
    @DisplayName("수동 구매 개수 생성된다.")
    public void createManualCountTest() {
        ManualAmount manualAmount = new ManualAmount("4", money);

        assertThat(manualAmount).isInstanceOf(ManualAmount.class);
    }

    @Test
    @DisplayName("수동 구매 개수는 구매가능 개수를 초과할 수 없다.")
    public void validateManualCount() {
        assertThatThrownBy(() -> {
            new ManualAmount("5", money);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(String.format(ManualAmount.INVALID_COUNT_ERROR_MESSAGE, money.getLottoCount()));
    }

    @Test
    @DisplayName("수동 구매 개수는 음수일 수 없다.")
    public void negativeManualCountTest() {
        assertThatThrownBy(() -> {
            new ManualAmount("-1", money);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(String.format(ManualAmount.INVALID_COUNT_ERROR_MESSAGE, money.getLottoCount()));
    }

}

package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualPurchaseCountsTest {
    final int allCounts = 14;

    @ParameterizedTest
    @DisplayName("수동 구매 개수를 입력하면 그 개수가 올바른지 검증하고 반환한다.")
    @CsvSource({"'0', 0", "'14', 14"})
    void getManualLottoCounts(final String inputManualLottoCounts, final int expected) {
        //when
        final ManualPurchaseCounts manualPurchaseCounts = new ManualPurchaseCounts(inputManualLottoCounts, expected);
        final int actual = manualPurchaseCounts.getManualLottoCounts();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("올바르지 않은 수동 구매 개수가 주어질 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"a", "1.2", "-1"})
    void create_exceptionByInvalidManualCounts(String value) {
        //given
        final String expectedExceptionMessage = "0 이상의 정수를 입력해주세요.";
        //when then
        assertThatThrownBy(() -> new ManualPurchaseCounts(value, allCounts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @Test
    @DisplayName("구입금액으로 구매할 수 있는 총 개수를 초과하는 수동 구매 개수가 주어질 경우 예외를 발생시킨다.")
    void create_exceptionByManualCountsOverThanMaximumAllCounts() {
        //given
        final String overCountsThanMaximumAllCounts = "15";
        final String expectedExceptionMessage = "구입금액으로 구매할 수 있는 개수를 초과한 개수입니다. 다시 입력해주세요.";
        //when then
        assertThatThrownBy(() -> new ManualPurchaseCounts(overCountsThanMaximumAllCounts, allCounts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
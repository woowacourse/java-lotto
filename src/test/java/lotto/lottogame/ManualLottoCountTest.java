package lotto.lottogame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.lottogame.ManualLottoCount.ERROR_MESSAGE_INVALID_RANGE_WITH_COUNT;
import static lotto.lottoticket.LottoTicketValidation.ERROR_MESSAGE_INVALID_INPUT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoCountTest {
    @Test
    @DisplayName("생성 확인")
    void create() {
        LottoCount lottoCount = new LottoCount(4);
        assertThat(new ManualLottoCount("3", lottoCount)).isEqualTo(new ManualLottoCount("3", lottoCount));
    }

    @ParameterizedTest
    @DisplayName("숫자를 입력하지 않은 경우 예외처리")
    @ValueSource(strings = {"-", "d"})
    void AutoLottoCountNotNumber(String value) {
        assertThatThrownBy(() -> new ManualLottoCount(value, new LottoCount(4))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_INPUT);
    }

    @ParameterizedTest
    @DisplayName("유효한 범위인지 확인")
    @ValueSource(strings = {"6", "-1"})
    void AutoLottoCountInvalidRange(String value) {
        LottoCount lottoCount = new LottoCount(5);
        assertThatThrownBy(() -> new ManualLottoCount(value, lottoCount).validRange(lottoCount)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_RANGE_WITH_COUNT);
    }

    @Test
    @DisplayName("올바른 입력 확인")
    void AutoLottoCountValidInput() {
        LottoCount lottoCount = new LottoCount(9);
        ManualLottoCount manualLottoCount = new ManualLottoCount("5", lottoCount);
        assertThat(manualLottoCount.makeAutoCount(lottoCount)).isEqualTo(new LottoCount(4));
    }
}

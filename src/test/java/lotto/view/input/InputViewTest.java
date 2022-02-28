package lotto.view.input;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.AppConfig;
import lotto.exception.LottoException;
import lotto.exception.ball.BallNumberExceptionStatus;
import lotto.exception.money.MoneyExceptionStatus;
import lotto.view.input.reader.CustomReader;

class InputViewTest {

    private static final AppConfig APP_CONFIG = AppConfig.getInstance();

    private final InputView inputView = APP_CONFIG.inputView;
    private final CustomReader customReader = APP_CONFIG.reader;

    @DisplayName("구입 금액은 숫자여야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"100a", "1 1 1", ""})
    void moneyNotNumericExceptionTest(final String inputText) {
        customReader.initText(inputText);
        assertThatThrownBy(inputView::requestMoney)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(MoneyExceptionStatus.MONEY_MUST_BE_NUMERIC.getMessage());
    }

    @DisplayName("당첨 번호는 숫자여야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"1,1,1,1 1", "1,,,,", "1,a,1,a,1", ""})
    void winningNumbersNotNumericExceptionTest(final String inputText) {
        customReader.initText(inputText);
        assertThatThrownBy(inputView::requestWinningNumbers)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(BallNumberExceptionStatus.BALL_MUST_BE_NUMERIC.getMessage());
    }

    @DisplayName("보너스 볼은 숫자여야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"100a", "1 1 1", ""})
    void bonusNumberNotNumericExceptionTest(final String inputText) {
        customReader.initText(inputText);
        assertThatThrownBy(inputView::requestBonusNumber)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(BallNumberExceptionStatus.BALL_MUST_BE_NUMERIC.getMessage());
    }

}

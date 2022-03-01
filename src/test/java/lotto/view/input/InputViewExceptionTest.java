package lotto.view.input;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.LottoExceptionStatus;
import lotto.view.input.reader.CustomReader;

class InputViewExceptionTest {

    private final CustomReader customReader = new CustomReader();
    private final InputView inputView = new InputView(customReader);

    private interface TestCallback {
        void run();
    }

    @DisplayName("구입 금액으로, 숫자 이외의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"100a", "1 1 1", ""})
    void moneyNotNumericExceptionTest(final String inputLine) {
        customReader.initText(inputLine);
        assertThatThrownBy(inputView::requestMoney)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(LottoExceptionStatus.MONEY_MUST_BE_NUMERIC.getMessage());
    }

    @DisplayName("로또의 개수로, 숫자 이외의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"100a", "1 1 1", ""})
    void ticketCountNotNumericExceptionTest(final String inputLine) {
        customReader.initText(inputLine);
        assertThatThrownBy(inputView::requestTicketCount)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(LottoExceptionStatus.TICKET_COUNT_MUST_BE_NUMERIC.getMessage());
    }

    @DisplayName("로또 번호를 구성하는 볼 번호로, 숫자 이외의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"1,1,1,1 1", "1,,,,", "1,a,1,a,1", ""})
    void ticketNumbersNotNumericExceptionTest(final String inputLine) {
        customReader.initText(inputLine);
        assertThatThrownBy(inputView::requestTicketNumbers)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(LottoExceptionStatus.BALL_NUMBER_MUST_BE_NUMERIC.getMessage());
    }

    @DisplayName("보너스 볼 번호로, 숫자 이외의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"100a", "1 1 1", ""})
    void bonusNumberNotNumericExceptionTest(final String inputLine) {
        customReader.initText(inputLine);
        assertThatThrownBy(inputView::requestBonusNumber)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(LottoExceptionStatus.BALL_NUMBER_MUST_BE_NUMERIC.getMessage());
    }

}

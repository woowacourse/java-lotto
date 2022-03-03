package lotto.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;
import lotto.utils.MoneyUnit;
import lotto.view.input.InputView;
import lotto.view.input.reader.CustomReader;
import lotto.view.output.OutputView;

import org.junit.jupiter.params.provider.ValueSource;

class LottoViewTest {

    private final CustomReader customReader = new CustomReader();
    private final InputView inputView = new InputView(customReader);
    private final OutputView outputView = new OutputView();
    private final LottoView lottoView = new LottoView(inputView, outputView);

    @DisplayName("수동으로 구매할 로또의 개수는 양수여야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(ints = {0, -1, -20})
    void manualTicketCountNotPositiveExceptionTest(final int manualTicketCount) {
        customReader.initText(String.valueOf(manualTicketCount));

        final int totalTicketCount = 10;
        assertThatThrownBy(() -> lottoView.requestManualTicketCount(totalTicketCount))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(LottoExceptionStatus.MANUAL_TICKET_COUNT_MUST_BE_POSITIVE.getMessage());
    }

    @DisplayName("수동으로 구매할 로또의 개수는 구매 가능한 로또의 개수보다 클 수 없다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}, 수동으로 구매할 로또의 개수 : {1}")
    @MethodSource("provideForManualTicketCountTooManyExceptionTest")
    void manualTicketCountTooManyExceptionTest(final int money, final int manualTicketCount) {
        final List<String> inputLines = List.of(String.valueOf(money), String.valueOf(manualTicketCount));
        customReader.initText(inputLines);

        final int totalTicketCount = MoneyUnit.divide(money);
        assertThatThrownBy(() -> lottoView.requestManualTicketCount(totalTicketCount))
                .isInstanceOf(LottoException.class)
                        .hasMessageContaining(LottoExceptionStatus.MANUAL_TICKET_COUNT_CANNOT_BE_TOO_MANY.getMessage());
    }

    public static Stream<Arguments> provideForManualTicketCountTooManyExceptionTest() {
        return Stream.of(
                Arguments.of(1000, 2),
                Arguments.of(5000, 6),
                Arguments.of(10000, 20)
        );
    }

}

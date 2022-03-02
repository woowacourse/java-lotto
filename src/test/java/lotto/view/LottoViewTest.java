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

class LottoViewTest {

    private final CustomReader customReader = new CustomReader();
    private final InputView inputView = new InputView(customReader);
    private final OutputView outputView = new OutputView();
    private final LottoView lottoView = new LottoView(inputView, outputView);

    @DisplayName("수동으로 구매할 로또의 개수는 구매 가능한 로또의 개수보다 클 수 없다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}, 수동으로 구매할 로또의 개수 : {1}")
    @MethodSource("provideForRequestManualTicketCountExceptionTest")
    void requestManualTicketCountExceptionTest(final int money, final int manualTicketCount) {
        final List<String> inputValues = List.of(String.valueOf(money), String.valueOf(manualTicketCount));
        customReader.initText(inputValues);

        final int totalTicketCount = MoneyUnit.divide(money);
        assertThatThrownBy(() -> lottoView.requestManualTicketCount(totalTicketCount))
                .isInstanceOf(LottoException.class)
                        .hasMessageContaining(LottoExceptionStatus.MANUAL_TICKET_COUNT_CANNOT_BE_MORE_THAT_TOTAL_TICKET_COUNT.getMessage());
    }

    public static Stream<Arguments> provideForRequestManualTicketCountExceptionTest() {
        return Stream.of(
                Arguments.of(1000, 2),
                Arguments.of(5000, 6),
                Arguments.of(10000, 20)
        );
    }

}

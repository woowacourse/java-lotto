package lotto.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.AppConfig;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.dto.TicketDto;
import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;
import lotto.exception.ball.BallNumberExceptionStatus;
import lotto.exception.money.MoneyExceptionStatus;
import lotto.exception.ticket.TicketNumbersExceptionStatus;
import lotto.service.LottoService;
import lotto.view.input.reader.CustomReader;

class LottoControllerTest {

    private static final AppConfig APP_CONFIG = AppConfig.getInstance();

    private final LottoController lottoController = APP_CONFIG.lottoController;
    private final LottoService lottoService = APP_CONFIG.lottoService;
    private final CustomTicketGenerator customTicketGenerator = APP_CONFIG.ticketGenerator;
    private final CustomReader customReader = APP_CONFIG.reader;

    private void purchaseTicketsExceptionTest(final String inputText, final MoneyExceptionStatus exceptionStatus) {
        customReader.initText(inputText);
        assertThatThrownBy(lottoController::purchaseTickets)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(exceptionStatus.getMessage());
    }

    @DisplayName("로또 구매 기능, 숫자 이외의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"", "100a", "1 1"})
    void purchaseTicketsNotNumericExceptionTest(final String inputText) {
        purchaseTicketsExceptionTest(inputText, MoneyExceptionStatus.MONEY_MUST_BE_NUMERIC);
    }

    @DisplayName("로또 구매 기능, 1000원 단위로 입력해야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"10", "1010", "100001"})
    void purchaseTicketsNotDivisibleExceptionTest(final String inputText) {
        purchaseTicketsExceptionTest(inputText, MoneyExceptionStatus.MONEY_MUST_BE_DIVISIBLE);
    }

    @DisplayName("로또 구매 기능, 0은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"0"})
    void purchaseTicketsNotZeroExceptionTest(final String inputText) {
        purchaseTicketsExceptionTest(inputText, MoneyExceptionStatus.MONEY_CANNOT_BE_ZERO);
    }

    @DisplayName("로또 구매 기능, 정상 작동 테스트")
    @ParameterizedTest(name = "[{index}] {1}원어치 로또 구매")
    @MethodSource("lotto.controller.provider.LottoControllerTestProvider#provideForPurchaseTicketsTest")
    void purchaseTicketsTest(final List<TicketDto> generatedTickets, final String inputText) {
        customTicketGenerator.initTickets(generatedTickets);
        customReader.initText(inputText);

        lottoController.purchaseTickets();
        final List<TicketDto> ticketDtos = lottoService.getTicketDtos();
        checkTicketEquals(ticketDtos, generatedTickets);

    }

    void checkTicketEquals(final List<TicketDto> actual, final List<TicketDto> expected) {
        assertThat(actual.size()).isEqualTo(expected.size());
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i).getBallNumbers()).isEqualTo(expected.get(i).getBallNumbers());
        }
    }

    private void winningNumbersAndBonusNumberExceptionTest(final List<String> inputValues,
                                             final List<TicketDto> generatedTickets,
                                             final LottoExceptionStatus exceptionStatus) {
        customReader.initText(inputValues);
        customTicketGenerator.initTickets(generatedTickets);
        lottoController.purchaseTickets();
        assertThatThrownBy(lottoController::checkOutAnalysis)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(exceptionStatus.getMessage());
    }

    @DisplayName("로또 당첨 번호 입력, 숫자 이외의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 당첨 번호 입력 : \"{1}\"")
    @MethodSource(
            "lotto.controller.provider.LottoControllerTestProvider#provideForWinningNumbersNotNumericExceptionTest")
    void winningNumbersNotNumericExceptionTest(final String inputMoney,
                                               final String inputWinningNumbers,
                                               final String inputBonusNumber,
                                               final List<TicketDto> generatedTickets) {
        final List<String> inputValues = List.of(inputMoney, inputWinningNumbers, inputBonusNumber);
        winningNumbersAndBonusNumberExceptionTest(
                inputValues, generatedTickets, BallNumberExceptionStatus.BALL_MUST_BE_NUMERIC);
    }

    @DisplayName("로또 당첨 번호 입력, 번호는 6개로 구성되어야 합니다.")
    @ParameterizedTest(name = "[{index}] 당첨 번호 입력 : \"{1}\"")
    @MethodSource(
            "lotto.controller.provider.LottoControllerTestProvider#provideForWinningNumbersOutOfSizeExceptionTest")
    void winningNumbersOutOfSizeExceptionTest(final String inputMoney,
                                              final String inputWinningNumbers,
                                              final String inputBonusNumber,
                                              final List<TicketDto> generatedTickets) {
        final List<String> inputValues = List.of(inputMoney, inputWinningNumbers, inputBonusNumber);
        winningNumbersAndBonusNumberExceptionTest(
                inputValues, generatedTickets, TicketNumbersExceptionStatus.TICKET_NUMBERS_CANNOT_BE_OUT_OF_SIZE);
    }

    @DisplayName("로또 당첨 번호 입력, 범위 밖의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 당첨 번호 입력 : \"{1}\"")
    @MethodSource(
            "lotto.controller.provider.LottoControllerTestProvider#provideForWinningNumbersOutOfRangeExceptionTest")
    void winningNumbersOutOfRangeExceptionTest(final String inputMoney,
                                               final String inputWinningNumbers,
                                               final String inputBonusNumber,
                                               final List<TicketDto> generatedTickets) {
        final List<String> inputValues = List.of(inputMoney, inputWinningNumbers, inputBonusNumber);
        winningNumbersAndBonusNumberExceptionTest(
                inputValues, generatedTickets, BallNumberExceptionStatus.BALL_CANNOT_BE_OUT_OF_RANGE);
    }

    @DisplayName("로또 당첨 번호 입력, 번호는 중복될 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 당첨 번호 입력 : \"{1}\"")
    @MethodSource(
            "lotto.controller.provider.LottoControllerTestProvider#provideForWinningNumbersDuplicatedExceptionTest")
    void winningNumbersDuplicatedExceptionTest(final String inputMoney,
                                               final String inputWinningNumbers,
                                               final String inputBonusNumber,
                                               final List<TicketDto> generatedTickets) {
        final List<String> inputValues = List.of(inputMoney, inputWinningNumbers, inputBonusNumber);
        winningNumbersAndBonusNumberExceptionTest(
                inputValues, generatedTickets, TicketNumbersExceptionStatus.TICKET_NUMBERS_CANNOT_BE_DUPLICATED);
    }

    @DisplayName("보너스 볼 번호 입력, 숫자 이외의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 보너스 볼 입력 : \"{2}\"")
    @MethodSource(
            "lotto.controller.provider.LottoControllerTestProvider#provideForBonusNumberNotNumericExceptionTest")
    void bonusNumberNotNumericExceptionTest(final String inputMoney,
                                            final String inputWinningNumbers,
                                            final String inputBonusNumber,
                                            final List<TicketDto> generatedTickets) {
        final List<String> inputValues = List.of(inputMoney, inputWinningNumbers, inputBonusNumber);
        winningNumbersAndBonusNumberExceptionTest(
                inputValues, generatedTickets, BallNumberExceptionStatus.BALL_MUST_BE_NUMERIC);
    }

    @DisplayName("보너스 볼 번호 입력, 범위 밖의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 보너스 볼 입력 : \"{2}\"")
    @MethodSource(
            "lotto.controller.provider.LottoControllerTestProvider#provideForBonusNumberOutOfRangeExceptionTest")
    void bonusNumberOutOfRangeExceptionTest(final String inputMoney,
                                            final String inputWinningNumbers,
                                            final String inputBonusNumber,
                                            final List<TicketDto> generatedTickets) {
        final List<String> inputValues = List.of(inputMoney, inputWinningNumbers, inputBonusNumber);
        winningNumbersAndBonusNumberExceptionTest(
                inputValues, generatedTickets, BallNumberExceptionStatus.BALL_CANNOT_BE_OUT_OF_RANGE);
    }

    @DisplayName("보너스 볼 번호 입력, 보너스 볼은 당첨 번호와 중복될 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 보너스 볼 입력 : \"{2}\"")
    @MethodSource(
            "lotto.controller.provider.LottoControllerTestProvider#provideForBonusNumberDuplicatedExceptionTest")
    void bonusNumberDuplicatedExceptionTest(final String inputMoney,
                                            final String inputWinningNumbers,
                                            final String inputBonusNumber,
                                            final List<TicketDto> generatedTickets) {
        final List<String> inputValues = List.of(inputMoney, inputWinningNumbers, inputBonusNumber);
        winningNumbersAndBonusNumberExceptionTest(
                inputValues, generatedTickets, TicketNumbersExceptionStatus.TICKET_NUMBERS_CANNOT_BE_DUPLICATED);
    }

}

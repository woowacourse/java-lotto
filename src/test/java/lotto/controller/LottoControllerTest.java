package lotto.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.AppConfig;
import lotto.domain.ticket.Analysis;
import lotto.domain.ticket.Tickets;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.domain.ticket.WinningTicket;
import lotto.domain.rank.Rank;
import lotto.dto.TicketDto;
import lotto.dto.WinningTicketDto;
import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;
import lotto.exception.ball.BallNumberExceptionStatus;
import lotto.exception.money.MoneyExceptionStatus;
import lotto.exception.ticket.TicketNumbersExceptionStatus;
import lotto.service.LottoService;
import lotto.view.LottoView;
import lotto.view.input.reader.CustomReader;

class LottoControllerTest {

    private static final AppConfig APP_CONFIG = AppConfig.getInstance();

    private final LottoController lottoController = APP_CONFIG.lottoController;
    private final LottoService lottoService = APP_CONFIG.lottoService;
    private final LottoView lottoView = APP_CONFIG.lottoView;
    private final CustomTicketGenerator customTicketGenerator = APP_CONFIG.ticketGenerator;
    private final CustomReader customReader = APP_CONFIG.reader;

    private void purchaseTicketsExceptionTest(final String inputText, final MoneyExceptionStatus exceptionStatus) {
        customReader.initText(inputText);

        assertThatThrownBy(lottoController::purchaseTickets)
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(exceptionStatus.getMessage());
    }

    @DisplayName("구입 금액으로 숫자 이외의 값은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"", "100a", "1 1"})
    void purchaseTicketsNotNumericExceptionTest(final String inputText) {
        purchaseTicketsExceptionTest(inputText, MoneyExceptionStatus.MONEY_MUST_BE_NUMERIC);
    }

    @DisplayName("구입 금액은 1000원 단위로 입력해야 합니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"10", "1010", "100001"})
    void purchaseTicketsNotDivisibleExceptionTest(final String inputText) {
        purchaseTicketsExceptionTest(inputText, MoneyExceptionStatus.MONEY_MUST_BE_DIVISIBLE);
    }

    @DisplayName("구입 금액으로 0은 입력할 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 입력 : \"{0}\"")
    @ValueSource(strings = {"0"})
    void purchaseTicketsNotZeroExceptionTest(final String inputText) {
        purchaseTicketsExceptionTest(inputText, MoneyExceptionStatus.MONEY_CANNOT_BE_ZERO);
    }

    @DisplayName("로또는 입력한 구입 금액만큼 구매되어야 합니다.")
    @ParameterizedTest(name = "[{index}] {1}원어치 로또 구매")
    @MethodSource("lotto.controller.provider.LottoControllerTestProvider#provideForPurchaseTicketsTest")
    void purchaseTicketsTest(final List<TicketDto> generatedTickets, final String inputText) {
        customTicketGenerator.initTickets(generatedTickets);
        customReader.initText(inputText);

        final Tickets tickets = lottoController.purchaseTickets();
        assertThat(tickets.getSize()).isEqualTo(generatedTickets.size());
    }

    private void winningNumbersAndBonusNumberExceptionTest(final List<String> inputValues,
                                                           final List<TicketDto> generatedTickets,
                                                           final LottoExceptionStatus exceptionStatus) {
        customReader.initText(inputValues);
        customTicketGenerator.initTickets(generatedTickets);

        final Tickets tickets = lottoController.purchaseTickets();
        assertThatThrownBy(() -> lottoController.calculateAnalysis(tickets))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(exceptionStatus.getMessage());
    }

    @DisplayName("당첨 번호를 구성하는 볼 번호로, 숫자 이외의 값은 입력할 수 없습니다.")
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

    @DisplayName("당첨 번호로 6개의 볼 번호를 입력해야 합니다.")
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

    @DisplayName("당첨 번호를 구성하는 볼 번호로, 범위 밖의 값을 입력할 수 없습니다.")
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

    @DisplayName("당첨 번호를 구성하는 볼 번호로, 중복되는 값은 입력할 수 없습니다.")
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

    @DisplayName("보너스 볼 번호로, 숫자 이외의 값은 입력할 수 없습니다.")
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

    @DisplayName("보너스 볼 번호로, 범위 밖의 값은 입력할 수 없습니다.")
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

    @DisplayName("보너스 볼 번호로, 당첨 번호와 중복되는 값은 입력할 수 없습니다.")
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

    @DisplayName("당첨 통계의 당첨 등수 개수는 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 : {4}")
    @MethodSource("lotto.controller.provider.LottoControllerTestProvider#provideForCheckOutAnalysisTest")
    void checkOutAnalysisRankCountsTest(final String inputMoney,
                                        final String inputWinningNumbers,
                                        final String inputBonusNumber,
                                        final List<TicketDto> generatedTickets,
                                        final Map<Rank, Integer> expectedRankCounts,
                                        final String expectedProfitRate) {
        customReader.initText(List.of(inputMoney, inputWinningNumbers, inputBonusNumber));
        customTicketGenerator.initTickets(generatedTickets);

        final Tickets tickets = lottoController.purchaseTickets();
        final WinningTicketDto winningTicketDto = lottoView.requestWinningTicket();
        final WinningTicket winningTicket = winningTicketDto.toWinningTicket();
        final Analysis analysis = lottoService.generateAnalysis(tickets, winningTicket);

        final Map<Rank, Integer> actualRankCounts = analysis.getRankCounts();
        assertThat(actualRankCounts).isEqualTo(expectedRankCounts);
    }

    @DisplayName("당첨 통계의 수익률은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 수익률 : {5}")
    @MethodSource("lotto.controller.provider.LottoControllerTestProvider#provideForCheckOutAnalysisTest")
    void checkOutAnalysisProfitRateTest(final String inputMoney,
                                        final String inputWinningNumbers,
                                        final String inputBonusNumber,
                                        final List<TicketDto> generatedTickets,
                                        final Map<Rank, Integer> expectedRankCounts,
                                        final String expectedProfitRate) {
        customReader.initText(List.of(inputMoney, inputWinningNumbers, inputBonusNumber));
        customTicketGenerator.initTickets(generatedTickets);

        final Tickets tickets = lottoController.purchaseTickets();
        final WinningTicketDto winningTicketDto = lottoView.requestWinningTicket();
        final WinningTicket winningTicket = winningTicketDto.toWinningTicket();
        final Analysis analysis = lottoService.generateAnalysis(tickets, winningTicket);

        final String actualProfitRate = String.format("%.2f", analysis.getProfitRate());
        assertThat(actualProfitRate).isEqualTo(expectedProfitRate);
    }

}

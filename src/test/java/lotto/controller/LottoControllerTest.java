package lotto.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.AppConfig;
import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.dto.TicketDto;
import lotto.exception.LottoException;
import lotto.exception.money.MoneyExceptionStatus;
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
    @MethodSource("provideForPurchaseTicketsTest")
    void purchaseTicketsTest(final List<TicketDto> generatedTickets, final String inputText) {
        customTicketGenerator.initNumbers(generatedTickets);
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


    public static Stream<Arguments> provideForPurchaseTicketsTest() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6))
                        ), "1000"
                ),
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6)),
                                new TicketDto(List.of(11, 12, 13, 14, 15, 16)),
                                new TicketDto(List.of(21, 22, 23, 24, 25, 26))
                        ), "3000"
                ),
                Arguments.of(
                        List.of(
                                new TicketDto(List.of(1, 2, 3, 4, 5, 6)),
                                new TicketDto(List.of(11, 12, 13, 14, 15, 16)),
                                new TicketDto(List.of(21, 22, 23, 24, 25, 26)),
                                new TicketDto(List.of(31, 32, 33, 34, 35, 36)),
                                new TicketDto(List.of(40, 41, 42, 43, 44, 45))
                        ), "5000"
                )
        );
    }


}

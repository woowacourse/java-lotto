package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.result.UsersLottoTickets;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoMachine2Test {

    @ParameterizedTest
    @MethodSource("buyTickets_testcase")
    void buyTickets(String moneyValue, List<String> manualTicketsValue, int manualTicketsSize,
            int autoTicketsSize, int totalTicketsSize) {

        Money money = Money.valueOf(moneyValue);
        ManualBuyAmount manualBuyAmount = ManualBuyAmount.getInstance(Integer.toString(manualTicketsSize), money);
        LottoMachine2 lottoMachine2 = LottoMachine2.getInstance(money, manualBuyAmount);
        UsersLottoTickets usersLottoTickets = lottoMachine2.buyTickets(manualTicketsValue);

        assertThat(usersLottoTickets.getManualTicketsSize()).isEqualTo(manualTicketsSize);
        assertThat(usersLottoTickets.getAutoTicketsSize()).isEqualTo(autoTicketsSize);
        assertThat(usersLottoTickets.getTotalTickets().size()).isEqualTo(totalTicketsSize);
    }

    private static Stream<Arguments> buyTickets_testcase() {
        String ticketsValue = "1,2,3,4,5,6";
        return Stream.of(
                Arguments.of("0", Collections.emptyList(), 0, 0, 0),
                Arguments.of("1000", Arrays.asList(ticketsValue), 1, 0, 1),
                Arguments.of("1000", Collections.emptyList(), 0, 1, 1),
                Arguments.of("2000", Arrays.asList(ticketsValue, ticketsValue), 2, 0, 2),
                Arguments.of("2000", Arrays.asList(ticketsValue), 1, 1, 2),
                Arguments.of("2000", Collections.emptyList(), 0, 2, 2)
        );
    }
}
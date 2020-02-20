package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LottoTicketsTest {

    private static Stream<Arguments> lottoTicketSetUp() {
        return Stream.of(
                Arguments.of(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6)), 6),
                Arguments.of(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 7)), 15),
                Arguments.of(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 8)), 5),
                Arguments.of(new ArrayList(Arrays.asList(1, 2, 3, 4, 8, 9)), 4),
                Arguments.of(new ArrayList(Arrays.asList(1, 2, 3, 8, 9, 10)), 3)
        );
    }

    @DisplayName("당첨 티켓과 매칭 테스트")
    @ParameterizedTest
    @MethodSource("lottoTicketSetUp")
    void 모두_맞는지_확인하는_당첨_티켓_테스트(List<Integer> lottoTicket, int expected) {
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        winningLottoTicket.initializeBonusBall("7");

        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(new LottoTicket(lottoTicket)));
        LottoResults lottoResults = lottoTickets.match(winningLottoTicket);

//        Assertions.assertThat(lottoResults.size()).isEqualTo(1);

    }

}

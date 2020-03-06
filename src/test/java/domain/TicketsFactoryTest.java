package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketsFactoryTest {
    @DisplayName("티켓 생성 테스트")
    @Test
    void generateManualLottoTicketsTest() {
        List<String> numbers = new ArrayList(Arrays.asList(
           "1, 2, 3, 4, 5, 6",
           "10, 11, 12, 13, 14, 15"
        ));
        List<LottoTicket> manualLottoTickets = LottoTicketsGenerator.generateManualLottoTickets(numbers);
        LottoTickets input = TicketsFactory.getTickets(numbers, 3);

        Assertions.assertThat(input).isInstanceOf(LottoTickets.class);
        Assertions.assertThat(input.getLottoTickets()).containsSequence(manualLottoTickets);
    }
}

package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @DisplayName("로또 티켓들 생성하기")
    @Test
    public void create() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 7);

        LottoTickets lottoTickets = new LottoTickets(Arrays.asList
            (new LottoTicket(numbers),
                new LottoTicket(numbers2)));

        assertThat(lottoTickets.getLottoTickets())
            .contains(new LottoTicket(numbers), new LottoTicket(numbers2));
    }
}

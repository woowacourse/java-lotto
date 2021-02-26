package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    private LottoTickets lottoTickets;

    @BeforeEach
    void init() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 7);

        lottoTickets = new LottoTickets(Arrays.asList
            (new LottoTicket(numbers),
                new LottoTicket(numbers2)));
    }

    @DisplayName("로또 티켓들 생성하기")
    @Test
    public void create() {
        assertThat(lottoTickets.getLottoTickets())
            .contains(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 7)));
    }

    @DisplayName("새 로또 티켓(들)을 생성하면 기존 로또티켓 목록에 추가한다.")
    @Test
    void add() {
        List<LottoTicket> moreTickets = new ArrayList<>(
            Collections.singletonList(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 8))));
        lottoTickets.addTickets(moreTickets);
        assertThat(lottoTickets.getLottoTickets())
            .contains(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 7)),
                new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 8)));
    }

}

package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @DisplayName("LottoTickets 생성 테스트")
    @Test
    void create() {
        LottoTickets lottoTickets = new LottoTickets(14000);
        assertThat(lottoTickets).isEqualTo(new LottoTickets(14000));
    }

    @DisplayName("로또 번호 생생 테스트")
    @Test
    void getLottoTickets() {
        LottoTickets lottoTickets = new LottoTickets(14000);
        assertThat(lottoTickets.getLottoTickets()).isEqualTo(new ArrayList<>());
    }
}

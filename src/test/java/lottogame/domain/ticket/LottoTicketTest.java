package lottogame.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import lottogame.domain.number.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    @DisplayName("뽑힌 LottoNumber가 6개인지")
    void issueLottoTicket() {
        LottoTicket lottoTicket = new LottoTicket();
        assertThat(lottoTicket.getLottoNumbers().size()).isNotEqualTo(5);
        assertThat(lottoTicket.getLottoNumbers().size()).isEqualTo(6);
        assertThat(lottoTicket.getLottoNumbers().size()).isNotEqualTo(7);
    }

    @Test
    @DisplayName("뽑힌 LottoNumber에 중복이 없는지")
    void issueLottoTicketDuplicate() {
        LottoTicket lottoTicket = new LottoTicket();
        Set<LottoNumber> lottoNumberSet = new HashSet<>(lottoTicket.getLottoNumbers());
        assertThat(lottoNumberSet.size()).isEqualTo(lottoTicket.getLottoNumbers().size());
    }
}

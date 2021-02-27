package lottogame.domain.ticket;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {

    @Test
    @DisplayName("뽑힌 LottoNumber가 6개인지")
    void issueLottoTicket() {
        LottoTicket lottoTicket = LottoTicket.of();
        assertThat(lottoTicket.getLottoNumbers().size()).isNotEqualTo(5);
        assertThat(lottoTicket.getLottoNumbers().size()).isEqualTo(6);
        assertThat(lottoTicket.getLottoNumbers().size()).isNotEqualTo(7);
    }

    @Test
    @DisplayName("뽑힌 LottoNumber에 중복이 없는지")
    void issueLottoTicketDuplicate() {
        LottoTicket lottoTicket = LottoTicket.of();
        Set<LottoNumber> lottoNumberSet = new HashSet<>(lottoTicket.getLottoNumbers());
        assertThat(lottoNumberSet.size()).isEqualTo(lottoTicket.getLottoNumbers().size());
    }

    @Test
    @DisplayName("티켓을 뽑고 맞는 타입인지 확인하면 옳은 결과를 가져온다.")
    void checkTicketType() {
        LottoTicket lottoAutoTicket = LottoTicket.of();
        assertThat(lottoAutoTicket.isAutoTicket()).isTrue();

        List<LottoNumber> lottoNumberGroup = new ArrayList<>();
        for (int i = 1; i <= 6; ++i) {
            lottoNumberGroup.add(LottoNumber.of(i));
        }
        LottoTicket lottoManualTicket = LottoTicket.of(new LottoNumbers(lottoNumberGroup));
        assertThat(lottoManualTicket.isAutoTicket()).isFalse();
    }
}

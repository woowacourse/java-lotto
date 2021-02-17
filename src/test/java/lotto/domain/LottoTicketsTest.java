package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @DisplayName("로또 티켓들 생성하기")
    @Test
    public void create() {
        LottoNumber lotto1 = new LottoNumber(1);
        LottoNumber lotto2 = new LottoNumber(2);
        LottoNumber lotto3 = new LottoNumber(3);
        LottoNumber lotto4 = new LottoNumber(4);
        LottoNumber lotto5 = new LottoNumber(5);
        LottoNumber lotto6 = new LottoNumber(6);
        LottoNumber lotto7 = new LottoNumber(7);

        Set<LottoNumber> lottoNumbers = new HashSet<>(
            Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6));
        Set<LottoNumber> lottoNumbers2 = new HashSet<>(
            Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto7));
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList
            (new LottoTicket(lottoNumbers),
                new LottoTicket(lottoNumbers2)));

        assertThat(lottoTickets.toList())
            .contains(new LottoTicket(lottoNumbers), new LottoTicket(lottoNumbers2));
    }
}

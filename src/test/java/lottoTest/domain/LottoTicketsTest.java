package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketsTest {

    @Test
    void 구입한_로또_개수만큼_로또를_생성하는_기능() {
        List<LottoNumber> lottoNumbers =
                IntStream.rangeClosed(1, 6)
                        .boxed()
                        .map(LottoNumber::valueOf)
                        .collect(Collectors.toList());

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            lottoTickets.add(new LottoTicket(lottoNumbers));
        }

        LottoTickets result = new LottoTickets(lottoTickets);

        assertThat(result.getLottoTickets().get(0).getNumbers()).isEqualTo(lottoNumbers);
    }
}

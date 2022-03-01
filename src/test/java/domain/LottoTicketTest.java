package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import util.ShuffleNumberGenerator;

public class LottoTicketTest {

    @Test
    public void createLottoTicket() {
        int lottoCount = 14;
        LottoTicket lottoTicket = new LottoTicket(lottoCount, Collections.emptyList(), new ShuffleNumberGenerator());
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }

    @Test
    public void createLottoTicketWithPassiveLotto() {
        Lotto lotto = new PassiveLotto(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5)));
        List<Lotto> passiveLotto = List.of(lotto);
        LottoTicket lottoTicket = new LottoTicket(14, passiveLotto, new ShuffleNumberGenerator());
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }

    @Test
    public void checkPassiveLottoCount() {
        Lotto lotto = new PassiveLotto(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5)));
        List<Lotto> passiveLotto = List.of(lotto);
        LottoTicket lottoTicket = new LottoTicket(14, passiveLotto, new ShuffleNumberGenerator());
        assertThat(lottoTicket.getLottos().size() - lottoTicket.getAutoLottoCount()).isEqualTo(1);
    }

    @Test
    public void checkAutoLottoCount() {
        Lotto lotto = new PassiveLotto(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5)));
        List<Lotto> passiveLotto = List.of(lotto);
        LottoTicket lottoTicket = new LottoTicket(14, passiveLotto, new ShuffleNumberGenerator());
        assertThat(lottoTicket.getAutoLottoCount()).isEqualTo(14);
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> list) {
        return list.stream()
                .map(LottoNumber :: of)
                .collect(Collectors.toList());
    }
}

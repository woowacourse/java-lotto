package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    void numbers_hasSizeOfSix() {
        LottoTicket lottoTicket = LottoTicket.createAutoLotto();
        assertThat(lottoTicket.getNumbers().size()).isEqualTo(6);
    }

    @Test
    void numbers_isSortedAsc() {
        LottoTicket lottoTicket = LottoTicket.createAutoLotto();
        assertThat(lottoTicket.getNumbers()).isSorted();
    }

    @Test
    void manualLotto_passesOnSizeOfSix() {
        LottoTicket lottoTicket = createNewLotto(1, 2, 3, 4, 5, 6);
        assertThat(lottoTicket.getNumbers().size()).isEqualTo(6);
    }

    @Test
    void manualLotto_failOnSizeNotOfSix() {
        assertThatThrownBy(() -> createNewLotto(1, 2, 3, 4, 5, 6, 7));
    }

    @Test
    void manualLotto_isSorted() {
        LottoTicket lottoTicket = createNewLotto(6, 5, 4, 3, 2, 1);
        assertThat(lottoTicket.getNumbers()).isSorted();
    }

    private LottoTicket createNewLotto(int... value) {
        List<LottoNumber> lottoNumbers = Arrays.stream(value)
                .boxed()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return LottoTicket.createManualLotto(lottoNumbers);
    }
}

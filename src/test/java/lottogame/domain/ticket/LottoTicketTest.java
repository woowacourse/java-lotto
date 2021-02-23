package lottogame.domain.ticket;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lottogame.domain.number.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)
            .stream()
            .map(LottoNumber::new)
            .collect(Collectors.toSet())
        );
        this.lottoTicket = new LottoTicket(lottoNumberSet);
    }

    @Test
    @DisplayName("뽑힌 LottoNumber가 6개인지인 확인")
    void testIssuedLottoNumberCount() {
        assertThat(lottoTicket.getLottoNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("LottoNumber가 LottoTicket에 있는지 없는지 확인")
    void testIssueLottoTicketDuplicate() {
        assertThat(lottoTicket.contains(new LottoNumber(6))).isTrue();
        assertThat(lottoTicket.contains(new LottoNumber(7))).isFalse();
    }
}

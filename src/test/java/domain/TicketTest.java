package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {
    private Ticket ticket;

    @BeforeEach
    void initialize() {
        ticket = new Ticket(() -> Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));
    }

    @Test
    void 티켓생성() {
        assertThat(ticket.getLottoNumbers()).isEqualTo(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6))
        );
    }

    @Test
    void 로또번호_등수확인_1등() {
        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6));
        LottoNumber bonusNumber = new LottoNumber(8);
        assertThat(ticket.getRank(lottoNumbers, bonusNumber)).isEqualTo(Rank.FIRST);
    }

    @Test
    void 로또번호_등수확인_2등() {
        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(16));
        LottoNumber bonusNumber = new LottoNumber(6);
        assertThat(ticket.getRank(lottoNumbers, bonusNumber)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또번호_등수확인_3등() {
        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(16));
        LottoNumber bonusNumber = new LottoNumber(17);
        assertThat(ticket.getRank(lottoNumbers, bonusNumber)).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또번호_등수확인_4등() {
        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(15),
            new LottoNumber(16));
        LottoNumber bonusNumber = new LottoNumber(17);
        assertThat(ticket.getRank(lottoNumbers, bonusNumber)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 로또번호_등수확인_5등() {
        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(14),
            new LottoNumber(15),
            new LottoNumber(16));
        LottoNumber bonusNumber = new LottoNumber(17);
        assertThat(ticket.getRank(lottoNumbers, bonusNumber)).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 로또번호_등수확인_꼴등() {
        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(11),
            new LottoNumber(12),
            new LottoNumber(13),
            new LottoNumber(14),
            new LottoNumber(15),
            new LottoNumber(16));
        LottoNumber bonusNumber = new LottoNumber(17);
        assertThat(ticket.getRank(lottoNumbers, bonusNumber)).isEqualTo(Rank.OTHER);
    }
}

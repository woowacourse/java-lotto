package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
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
    void 당첨번호_보너스번호_중복검사_실패테스트() {
        Set<LottoNumber> winNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6));
        assertThatThrownBy(() -> new WinningNumbers(new Ticket(winNumbers), new LottoNumber(6)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_보너스번호_중복검사_성공테스트() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));
        assertThatCode(() -> new WinningNumbers(winTicket, new LottoNumber(7)))
            .doesNotThrowAnyException();
    }

    @Test
    void 로또번호_등수확인_1등() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winTicket, bonusNumber);
        assertThat(winningNumbers.getTicketRank(ticket)).isEqualTo(Rank.FIRST);
    }

    @Test
    void 로또번호_등수확인_2등() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(16)));
        LottoNumber bonusNumber = new LottoNumber(6);
        WinningNumbers winningNumbers = new WinningNumbers(winTicket, bonusNumber);
        assertThat(winningNumbers.getTicketRank(ticket)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또번호_등수확인_3등() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(16)));
        LottoNumber bonusNumber = new LottoNumber(17);
        WinningNumbers winningNumbers = new WinningNumbers(winTicket, bonusNumber);
        assertThat(winningNumbers.getTicketRank(ticket)).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또번호_등수확인_4등() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(15),
            new LottoNumber(16)));
        LottoNumber bonusNumber = new LottoNumber(17);
        WinningNumbers winningNumbers = new WinningNumbers(winTicket, bonusNumber);
        assertThat(winningNumbers.getTicketRank(ticket)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 로또번호_등수확인_5등() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(14),
            new LottoNumber(15),
            new LottoNumber(16)));
        LottoNumber bonusNumber = new LottoNumber(17);
        WinningNumbers winningNumbers = new WinningNumbers(winTicket, bonusNumber);
        assertThat(winningNumbers.getTicketRank(ticket)).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 로또번호_등수확인_꼴등() {
        Ticket winTicket = new Ticket(Set.of(new LottoNumber(11),
            new LottoNumber(12),
            new LottoNumber(13),
            new LottoNumber(14),
            new LottoNumber(15),
            new LottoNumber(16)));
        LottoNumber bonusNumber = new LottoNumber(17);
        WinningNumbers winningNumbers = new WinningNumbers(winTicket, bonusNumber);
        assertThat(winningNumbers.getTicketRank(ticket)).isEqualTo(Rank.OTHER);
    }
}

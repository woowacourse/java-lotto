package lotto.domain.ticket;

import lotto.domain.ticket.exception.InvalidDuplicatedNumberException;
import lotto.domain.ticket.exception.InvalidNumberCountException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketTest {
    @Test
    public void 로또_번호_갯수_초과_아래_예외() {
        assertThrows(InvalidNumberCountException.class, () -> {
            new LottoTicket(Arrays.asList(LottoNumber.of(1)));
        });
    }

    @Test
    public void 로또_번호_갯수_초과_위_예외() {
        assertThrows(InvalidNumberCountException.class, () -> {
            new LottoTicket(Arrays.asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6), LottoNumber.of(7)));
        });
    }


    @Test
    public void 로또_번호_중복_예외() {
        assertThrows(InvalidDuplicatedNumberException.class, () -> {
            new LottoTicket(Arrays.asList(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(5)));
        });
    }

    @Test
    public void 숫자_6개_겹침(){
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        LottoTicket anotherTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(lottoTicket.countSameNumber(anotherTicket)).isEqualTo(6);
    }

    @Test public void 숫자_5개_겹침(){
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        LottoTicket anotherTicket = new LottoTicket(Arrays.asList(LottoNumber.of(11),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(lottoTicket.countSameNumber(anotherTicket)).isEqualTo(5);
    }

    @Test
    public void 숫자_4개_겹침(){
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        LottoTicket anotherTicket = new LottoTicket(Arrays.asList(LottoNumber.of(11),LottoNumber.of(12),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(lottoTicket.countSameNumber(anotherTicket)).isEqualTo(4);
    }

    @Test
    public void 숫자_3개_겹침(){
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        LottoTicket anotherTicket = new LottoTicket(Arrays.asList(LottoNumber.of(11),LottoNumber.of(12),LottoNumber.of(13),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(lottoTicket.countSameNumber(anotherTicket)).isEqualTo(3);
    }

    @Test
    public void 숫자_2개_겹침(){
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        LottoTicket anotherTicket = new LottoTicket(Arrays.asList(LottoNumber.of(11),LottoNumber.of(12),LottoNumber.of(13),LottoNumber.of(14),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(lottoTicket.countSameNumber(anotherTicket)).isEqualTo(2);
    }

    @Test
    public void 숫자_1개_겹침(){
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        LottoTicket anotherTicket = new LottoTicket(Arrays.asList(LottoNumber.of(11),LottoNumber.of(12),LottoNumber.of(13),LottoNumber.of(14),LottoNumber.of(15),LottoNumber.of(6)));
        assertThat(lottoTicket.countSameNumber(anotherTicket)).isEqualTo(1);
    }

    @Test
    public void 숫자_0개_겹침(){
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        LottoTicket anotherTicket = new LottoTicket(Arrays.asList(LottoNumber.of(11),LottoNumber.of(12),LottoNumber.of(13),LottoNumber.of(14),LottoNumber.of(15),LottoNumber.of(16)));
        assertThat(lottoTicket.countSameNumber(anotherTicket)).isEqualTo(0);
    }

}
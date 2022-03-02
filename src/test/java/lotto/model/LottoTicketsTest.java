package lotto.model;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> injoon2019
import lotto.model.money.Money;
import lotto.model.ticket.LottoTickets;
import lotto.model.ticket.number.LottoNumber;
import lotto.model.utils.RandomNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
    
    @Test
    @DisplayName("로또 티켓들이 정상적으로 생성되는지 확인")
    void createLottoTickets() {
        // given
        Money money = Money.of(3000);
        RandomNumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER);
        // when
<<<<<<< HEAD
        LottoTickets lottoTickets = LottoTickets.buyManualTickets(List.of(List.of(1, 2, 3, 4, 5, 6)), money);
=======
        LottoTickets lottoTickets = LottoTickets.buy(generator, money);
>>>>>>> injoon2019
        // then
        Assertions.assertThat(lottoTickets).isNotNull();
    }
}

package lotto.domain.lotto;

<<<<<<< HEAD
import lotto.domain.money.LottoMoney;
import org.junit.jupiter.api.DisplayName;
||||||| merged common ancestors
import lotto.domain.money.LottoMoney;
=======
import lotto.domain.count.Count;
>>>>>>> feat: 로또 개수를 입력 받는 count 객체 추가
import org.junit.jupiter.api.Test;

<<<<<<< HEAD
class LottoTicketFactoryTest {
||||||| merged common ancestors
import static lotto.domain.money.LottoMoneyTest.getLottoMoneyFixture;

public class LottoTicketFactoryTest {
=======
import static lotto.domain.count.CountTest.getCountFixture;

public class LottoTicketFactoryTest {
>>>>>>> feat: 로또 개수를 입력 받는 count 객체 추가

    @Test
<<<<<<< HEAD
    @DisplayName("LottoTicketFactory는 money를 받아 lottoTicket을 발행")
    void lottoTicketFactoryPublishsLottoTicketsBasedOnMoney() {
        LottoMoney lottoMoney = new LottoMoney(1000);
        LottoTickets lottoTickets = LottoTicketFactory.publishLottoTickets(lottoMoney);
||||||| merged common ancestors
    void testLottoFactoryTest() {
        LottoMoney lottoMoney = getLottoMoneyFixture();
        LottoTickets lottoTickets = LottoTicketFactory.publishLottoTickets(lottoMoney);
=======
    void testLottoFactoryTest() {
        Count count = getCountFixture();
        LottoTickets lottoTickets = LottoTicketFactory.publishLottoTickets(count);
>>>>>>> feat: 로또 개수를 입력 받는 count 객체 추가
    }
}

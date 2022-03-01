package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
    private static final Set<Integer> dummyLottoNumber = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));

    @Test
    @DisplayName("음수의 금액으로 로또 티켓을 생성하려할 시 에러를 발생시키는지 검사한다.")
    void checkNegativeMoney() {
        int purchaseMoney = -17000;
        GenerateStrategy generateStrategy = () -> new HashSet<>(dummyLottoNumber);
        assertThatThrownBy(() -> new LottoTickets(purchaseMoney, generateStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoTickets.NOT_POSITIVE_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("티켓 가격의 배수가 아닌 금액으로 로또 티켓을 생성하려할 시 에러를 발생시키는지 검사한다.")
    void checkNotMultiplesOfPrice() {
        int purchaseMoney = 1700;
        GenerateStrategy generateStrategy = () -> new HashSet<>(dummyLottoNumber);
        assertThatThrownBy(() -> new LottoTickets(purchaseMoney, generateStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoTickets.NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("로또 티켓이 정상적으로 생성됐는지 검사한다.")
    void createLottoTicketsTest() {
        int purchaseMoney = 17000;
        GenerateStrategy generateStrategy = () -> new HashSet<>(dummyLottoNumber);
        LottoTickets lottoTickets = new LottoTickets(purchaseMoney, generateStrategy);
        lottoTickets.getTickets()
                .forEach(lottoTicket -> assertThat(lottoTicket.getLottoNumberValues()).isEqualTo(dummyLottoNumber));
    }

    @Test
    @DisplayName("로또 티켓이 가격에 맞게 입력된 금액에 맞춰 티켓을 만드는지 검사한다.")
    void checkLottoTicketsCount() {
        int purchaseMoney = 10000;
        GenerateStrategy generateStrategy = () -> new HashSet<>(dummyLottoNumber);
        LottoTickets lottoTickets = new LottoTickets(purchaseMoney, generateStrategy);
        assertThat(lottoTickets.getTickets().size()).isEqualTo(10);
    }
}
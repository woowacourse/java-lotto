package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoFactory;
import lotto.view.dto.BettingMoneyRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    @DisplayName("잘못된 갯수의 로또 번호를 입력받은 티켓은 Exception 발생")
    @Test
    void test1() {
        int[] ballNumbers = {1, 2, 3, 4, 5, 5};
        Set<LottoBall> lottoBalls = Arrays.stream(ballNumbers)
                .mapToObj(LottoFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());
        int wrongSize = lottoBalls.size();

        assertThatThrownBy(() -> new LottoTicket(lottoBalls))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 갯수가 %d개로 올바르지 않습니다.", wrongSize);
    }


    @DisplayName("로또 티켓이 지정 번호를 포함하는지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1,true", "7,false"})
    void name(int number, boolean expectedResult) {
        LottoCompany testLottoCompany = new TestLottoCompany();
        LottoTicket ticket = testLottoCompany.buyTicket(new BettingMoneyRequestDTO(1000)).get(0);

        LottoBall ballNumber = LottoFactory.getLottoBallByNumber(number);

        assertThat(ticket.has(ballNumber)).isEqualTo(expectedResult);
    }
}

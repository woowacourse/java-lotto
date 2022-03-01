import domain.*;
import domain.strategy.LottoNumberStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class LottoMachineTest {

    private static LottoNumberStrategy strategy;
    private static final List<List<Integer>> lottoNumbers = List.of();

    @BeforeAll
    static void lottoNumbersInit() {
        strategy = () -> IntStream.of(1, 2, 3, 4, 5, 6)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());
    }

    @Test
    @DisplayName("입력 금액이 1000원 미만일 때 예외")
    void insertAmountBelowThousand() {
        LottoMachine lottoMachine = new LottoMachine();

        assertThatThrownBy(() -> lottoMachine.purchaseLottoTickets(new Money(900), lottoNumbers, strategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 1000원 이상");
    }

    @Test
    @DisplayName("수동 로또 티켓 구매")
    void purchaseLottoTicketsManually() {
        LottoMachine lottoMachine = new LottoMachine();
        List<List<Integer>> input = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16),
                List.of(21, 22, 23, 24, 25, 26));

        assertThat(lottoMachine.purchaseLottoTicketsManually(input).size()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 10030, 10300, 10500, 10900, 10950})
    @DisplayName("입력 금액에 따라 알맞은 개수의 로또 생성 검증")
    void createLottoTicketsByAmount(int amount) {
        LottoMachine lottoMachine = new LottoMachine();
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(new Money(amount), lottoNumbers, strategy);

        assertThat(lottoTickets.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 당첨 통계 확인")
    void calculateWinningStat() {
        LottoMachine lottoMachine = new LottoMachine();
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(new Money(2000), lottoNumbers, strategy);
        System.out.println(lottoTickets.size());
        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 4, 3, 5, 6)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());
        LottoTicketNumbers winningNumbers = new LottoTicketNumbers(inputWinningNumbers);
        WinningStat actual = lottoMachine.createWinningStat(lottoTickets, winningNumbers, LottoNumber.getInstance(7));

        Map<LottoRank, Integer> ranks = new HashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            ranks.put(lottoRank, 0);
        }
        ranks.put(LottoRank.FIRST, 2);
        WinningStat expected = new WinningStat(ranks);

        assertThat(actual).isEqualTo(expected);
    }
}

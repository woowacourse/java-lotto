import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.LottoMachine;
import domain.LottoNumber;
import domain.LottoNumberStrategy;
import domain.LottoRank;
import domain.LottoTicket;
import domain.LottoTicketNumbers;
import domain.Money;
import domain.WinningStat;


class LottoMachineTest {

    private LottoMachine lottoMachine = new LottoMachine(new FixedLottoNumberStrategy());

    @Test
    @DisplayName("입력 금액에 따라 알맞은 개수의 로또 자동 생성 검증")
    void createLottoTicketsByAuto() {
         List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTicketsByAuto(Money.from(10000));
         assertThat(lottoTickets.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("수동 로또 번호 생성 검증")
    void createLottoTicketsByManual() {
        List<List<Integer>> lottoNumbers = List.of(List.of(1, 2, 3, 4, 5, 6),
            List.of(7, 8, 9, 10, 11, 12));
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTicketsByManual(lottoNumbers);

        Iterator<List<Integer>> iterator = lottoNumbers.iterator();
        for (LottoTicket lottoTicket : lottoTickets) {
            List<Integer> ticketNumbers = lottoTicket.getTicketNumbers();
            assertThat(ticketNumbers).isEqualTo(iterator.next());
        }
    }

    @Test
    @DisplayName("로또 당첨 통계 확인")
    void calculateWinningStat() {
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTicketsByAuto(Money.from(2000));

        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 4, 3, 5, 6)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());

        LottoTicketNumbers winningNumbers = new LottoTicketNumbers(inputWinningNumbers);
        WinningStat winningStat = lottoMachine.createWinningStat(
                lottoTickets, winningNumbers, LottoNumber.getInstance(7));

        Map<LottoRank, Integer> result = winningStat.getStat();
        assertThat(result.get(LottoRank.FIRST)).isEqualTo(2);
    }

    static class FixedLottoNumberStrategy implements LottoNumberStrategy {

        @Override
        public List<LottoNumber> generate() {
            return IntStream.of(1, 2, 3, 4, 5, 6)
                    .mapToObj(LottoNumber::getInstance)
                    .collect(Collectors.toList());
        }
    }
}

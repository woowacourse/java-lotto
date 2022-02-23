import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class LottoMachineTest {

    @Test
    @DisplayName("입력 금액이 1000원 미만일 때 예외")
    void insertAmountBelowThousand() {
        LottoMachine lottoMachine = new LottoMachine();

        assertThatThrownBy(() -> {
            lottoMachine.purchaseLottoTickets(Money.from(900), new FixedLottoNumberStrategy());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력 금액에 따라 알맞은 개수의 로또 생성 검증")
    void createLottoTicketsByAmount() {
        LottoMachine lottoMachine = new LottoMachine();

        int size = lottoMachine.purchaseLottoTickets(Money.from(10000), new FixedLottoNumberStrategy());
        assertThat(size).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 당첨 통계 확인")
    void calculateWinningStat() {
        LottoMachine lottoMachine = new LottoMachine();

        lottoMachine.purchaseLottoTickets(Money.from(2000), new FixedLottoNumberStrategy());

        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 4, 3, 5, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());

        LottoNumbers winningNumbers = new LottoNumbers(inputWinningNumbers);
        WinningStat winningStat = lottoMachine.createWinningStat(winningNumbers, LottoNumber.from(7));

        Map<LottoRank, Integer> result = winningStat.getStat();
        assertThat(result.get(LottoRank.FIRST)).isEqualTo(2);
    }

    static class FixedLottoNumberStrategy implements LottoNumberStrategy {

        @Override
        public List<LottoNumber> generate() {
            return IntStream.of(1, 2, 3, 4, 5, 6)
                    .mapToObj(LottoNumber::from)
                    .collect(Collectors.toList());
        }
    }
}

package domain;

import domain.strategy.ManualLottoNumberStrategy;
import domain.strategy.AutoLottoNumberStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @Test
    @DisplayName("수동 로또 티켓 구매")
    void purchaseLottoTicketsManually() {
        LottoMachine lottoMachine = new LottoMachine();
        List<List<Integer>> input = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16),
                List.of(21, 22, 23, 24, 25, 26));

        assertThat(lottoMachine.purchase(input,
                new PurchaseCount(new Money(3000), 3)).getLottos().size()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 10030, 10300, 10500, 10900, 10950})
    @DisplayName("입력 금액에 따라 알맞은 개수의 로또 생성 검증")
    void createLottoTicketsByAmount(int amount) {
        PurchaseCount purchaseCount = new PurchaseCount(new Money(amount), 0);
        Lottos lottos = new Lottos(new AutoLottoNumberStrategy(), purchaseCount);

        assertThat(lottos.getLottos().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 당첨 통계 확인")
    void calculateWinningStat() {
        LottoMachine lottoMachine = new LottoMachine();
        Lottos lottos = new Lottos(new ManualLottoNumberStrategy(List.of(List.of(1, 2, 3, 4, 5, 6))),
                new PurchaseCount(new Money(1000), 1));
        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 4, 3, 5, 6)
                .mapToObj(LottoNumber::getInstance)
                .collect(Collectors.toList());
        LottoNumbers winningNumbers = new LottoNumbers(inputWinningNumbers);
        WinningStat actual = lottoMachine.createWinningStat(lottos,
                new WinLotto(winningNumbers, LottoNumber.getInstance(7)));

        Map<LottoRank, Integer> ranks = new HashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            ranks.put(lottoRank, 0);
        }
        ranks.put(LottoRank.FIRST, 1);
        WinningStat expected = new WinningStat(ranks);

        assertThat(actual).isEqualTo(expected);
    }
}

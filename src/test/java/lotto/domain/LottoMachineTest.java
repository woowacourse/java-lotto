package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @DisplayName("수동 로또, 자동 로또 생성 테스트")
    @Test
    void makeLottoTicketsByLottoMachine() {
        PurchasingPrice purchasingPrice = new PurchasingPrice(5000);
        PurchasingCounts purchasingCounts = PurchasingCounts.of(purchasingPrice, 2);
        List<List<Integer>> numberGroup = new ArrayList<>();
        numberGroup.add(Arrays.asList(1, 2, 3, 4, 5, 6));
        numberGroup.add(Arrays.asList(7, 8, 9, 10, 11, 12));
        LottoMachine lottoMachine = new LottoMachine(new ManualLottoNumberGenerator(numberGroup),
                new RandomLottoNumberGenerator());

        LottoTickets lottoTickets = lottoMachine.buyLottoTickets(purchasingCounts);

        assertThat(lottoTickets.size()).isEqualTo(5);
    }
}
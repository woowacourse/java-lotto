package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.lottonumber.Lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    @DisplayName("투입금액으로 totalPurchaseMoney객체를 생성하는지 확인한다.")
    void insertTotalPurchaseMoney() {
        LottoMachine lottoMachine = new LottoMachine();
        lottoMachine.insertTotalPurchaseMoney(30000, 3);

        assertThat(lottoMachine.sendTotalPurchaseLottoCount()).isEqualTo(30);
        assertThat(lottoMachine.sendAutoLottoCount()).isEqualTo(27);
        assertThat(lottoMachine.sendManualLottoCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("로또를 생성하는지 확인한다.")
    void purchaseLottos() {
        final LottoMachine lottoMachine = new LottoMachine();
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(Arrays.asList(1, 7, 3, 4, 32, 6)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));

        lottoMachine.insertTotalPurchaseMoney(30000, 0);
        lottoMachine.purchaseLottos(lottos);

        assertThat(lottoMachine.sendLottosInMachine().size()).isEqualTo(32);
    }
}
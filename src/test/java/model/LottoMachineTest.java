package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    @DisplayName("투입금액으로 totalPurchaseMoney객체를 생성하는지 확인한다.")
    void insertTotalPurchaseMoney() {
        LottoMachine lottoMachine = new LottoMachine();
        lottoMachine.makeTotalLottoCountForPurchase(30000, 3);

        assertThat(lottoMachine.bringAutoLottoCountForPurchase()).isEqualTo(27);
        assertThat(lottoMachine.bringManualLottoCountForPurchase()).isEqualTo(3);
    }
}
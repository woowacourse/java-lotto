package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @Test
    @DisplayName("입력 금액에 맞는 로또 개수 반환")
    void get_lotto_count() {
        LottoMachine lottoMachine = new LottoMachine();
        int count = lottoMachine.getLottoCount(new PurchaseAmount("9000"));

        assertEquals(count, 9);
    }
}

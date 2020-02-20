package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDummyTest {

    @Test
    void 구매금액으로_로또더미의_사이즈_확인(){
        PurchaseAmount amount = new PurchaseAmount("10800");
        int lottoCount = amount.getCount();
        LottoDummy lottoDummy = new LottoDummy(lottoCount);
        assertThat(lottoDummy.getDummySize()).isEqualTo(10);
    }
}

package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    void 구매금액으로_로또더미의_사이즈_확인(){
        Money amount = new Money("10800");
        int lottoCount = amount.getLottoCount();
//        Lottos lottos = LottosFactory.createAutoLottos(lottoCount);
//        assertThat(lottos.getDummySize()).isEqualTo(10);
    }
}

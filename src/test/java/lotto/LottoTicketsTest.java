package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {

    @Test
    void 구매금액으로_로또더미의_사이즈_확인(){
        PurchaseAmount amount = new PurchaseAmount("10800");
        int lottoCount = amount.getCount();
        LottoTickets lottoTickets = new LottoTickets();
        for (int i = 0; i < lottoCount; i++) {
            Lotto oneLotto = LottoFactory.createOneLotto();
            lottoTickets.addLotto(oneLotto);
        }
        assertThat(lottoTickets.getTicketsSize()).isEqualTo(10);
    }
}

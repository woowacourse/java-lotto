package domain.lottonumber;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoTicketFactoryTest {
    @Test
    void 팩토리_생성_테스트() {
        LottoTicket lottoTicket = LottoTicketFactory.createLottoNumbers(new TestLottoGenerator());
        for(int i = 1; i<= 6; i++) {
            Assertions.assertThat(lottoTicket.contains(LottoNumberFactory.getInstance(i))).isTrue();
        }
    }
}

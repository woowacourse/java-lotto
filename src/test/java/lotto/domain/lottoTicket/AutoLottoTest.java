package lotto.domain.lottoTicket;

import org.junit.jupiter.api.Test;

public class AutoLottoTest {
    @Test
    void 로또_자동생성_테스트() {
        AutoLotto lotto = new AutoLotto();
        System.out.println(lotto.toString());
    }
}

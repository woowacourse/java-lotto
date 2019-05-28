package lotto.domain;

import org.junit.jupiter.api.Test;


public class LottoTest {
    @Test
    void 로또_자동생성_테스트() {
        Lotto lotto = new Lotto();
        System.out.println(lotto.toString());
    }
}

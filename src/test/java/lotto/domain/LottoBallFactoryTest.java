package lotto.domain;

import org.junit.jupiter.api.Test;

public class LottoBallFactoryTest {
    @Test
    void 로또값_생성_확인() {
        System.out.println(LottoBallFactory.getInstance());
    }
}

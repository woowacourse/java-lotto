package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoFactoryTest {

    @Test
    void createRandomLotto() {
        for (int i = 0; i < 10; i++) {
            LottoFactory.createRandomLotto();
        }
    }
}
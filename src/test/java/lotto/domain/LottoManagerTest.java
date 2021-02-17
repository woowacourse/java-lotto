package lotto.domain;

import lotto.domain.lottomachine.RandomLottoMachine;
import org.junit.jupiter.api.BeforeEach;

public class LottoManagerTest {

    LottoManager lottoManager;

    @BeforeEach
    void setup() {
        lottoManager = new LottoManager(new RandomLottoMachine());
    }
}

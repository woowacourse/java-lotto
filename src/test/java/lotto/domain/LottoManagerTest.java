package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.domain.lottomachine.RandomLottoMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoManagerTest {

    LottoManager lottoManager;

    @BeforeEach
    void setup() {
        lottoManager = new LottoManager(new RandomLottoMachine());
    }

    @Test
    @DisplayName("티켓 개수만큼 로또 생성")
    void generateLottoByTicket() {
        List<Lotto> lottos = lottoManager.generateLottoByTicket(5);
        assertThat(lottos.size()).isEqualTo(5);
    }
}

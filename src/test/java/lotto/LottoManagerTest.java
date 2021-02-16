package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoManagerTest {

    LottoManager lottoManager;

    @BeforeEach
    void setup() {
        lottoManager = new LottoManager(new LottoSupplier());
    }

    @Test
    @DisplayName("티켓 개수만큼 로또 생성")
    void generateLottoByTicket() {
        List<Lotto> lottos = lottoManager.generateLottoByTicket(5);
        assertThat(lottos.size()).isEqualTo(5);
    }

    @Test
    void calculateMoneyToTicket() {
        int expected = 10;
        int actual = lottoManager.calculateMoneyToTicket(10000);
        assertThat(actual).isEqualTo(expected);
    }

}

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LottosTest {

    @Test
    @DisplayName("구입 금액에 따른 개수만큼 로또 생성")
    public void generateLottosTest() {
        int count = 14;
        Lottos lottos = Lottos.generateLottos(count);
        lottos.generateLottos(count);

        assertEquals(lottos.size(), 14);
    }
}

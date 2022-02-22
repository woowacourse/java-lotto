import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("구입 금액에 따른 개수만큼 로또 생성")
    public void generateLottosTest(){
        int count = 14;
        Lottos lottos = new Lottos();
        lottos.generateLottos(count);
        assertThat(lottos.size()).isEqualTo(14);
    }

}

import static org.assertj.core.api.Assertions.assertThat;

import model.lotto.Lotto;
import model.lotto.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.LottoMaker;


public class LottoMakerTest {
    @DisplayName("로또생성기_테스트")
    @Test
    void 로또생성기_테스트() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        LottoMaker lottoMaker = new LottoMaker(randomNumberGenerator);
        Lotto lotto = lottoMaker.generateLotto();

        assertThat(lotto.getNumbers()).isSorted();
    }
}
import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.LottoMaker;
import service.LottoNumberGenerator;


public class LottoMakerTest {
    @DisplayName("로또생성기_테스트")
    @Test
    void 로또생성기_테스트() {
        LottoMaker lottoMaker = LottoMaker.getInstance();
        Lotto lotto = lottoMaker.createLotto();

        assertThat(lotto.getNumbers()).isSorted();
    }
}
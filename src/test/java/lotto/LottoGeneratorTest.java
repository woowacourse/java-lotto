package lotto;

import lotto.model.Lotto;
import lotto.model.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    LottoGenerator lottoGenerator = new LottoGenerator();

    @Test
    @DisplayName("6개의 랜덤한 로또 번호를 생성하는지 확인")
    void shuffleTest() {
        Lotto lotto = lottoGenerator.generateLotto();

        assertThat(lotto.getSize()).isEqualTo(6);
    }

    @Test
    @DisplayName("3개의 로또 생성하는지 확인")
    void GenerateLottos() {
        List<Lotto> lottos = lottoGenerator.generateLottos(3);

        assertThat(lottos.size()).isEqualTo(3);
    }
}

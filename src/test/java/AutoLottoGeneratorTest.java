import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoGenerator;
import domain.AutoLottoGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoLottoGeneratorTest {

    @Test
    @DisplayName("로또 amount만큼 로또 생성에 성공한다.")
    void lotto_generate() {

        LottoGenerator lottoGenerator = new AutoLottoGenerator(3);

        List<Lotto> lottos = lottoGenerator.generate();

        assertThat(lottos.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("로또 amount가 0이면 빈 리스트를 반환한다.")
    void lotto_generate_empty() {

        LottoGenerator lottoGenerator = new AutoLottoGenerator(0);

        List<Lotto> lottos = lottoGenerator.generate();

        assertThat(lottos.size()).isEqualTo(0);
    }

}

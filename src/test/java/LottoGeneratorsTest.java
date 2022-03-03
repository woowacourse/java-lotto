import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.LottoGenerator;
import domain.LottoGenerators;
import domain.ManualLottoGenerator;
import domain.AutoLottoGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorsTest {

    @Test
    @DisplayName("로또 생성 성공")
    void generate_lottos() {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(1,
            List.of(List.of(1, 2, 3, 4, 5, 6)));
        AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator(1);

        LottoGenerator lottoGenerators = new LottoGenerators(
            manualLottoGenerator,
            autoLottoGenerator);

        assertThat(lottoGenerators.generate().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("수동 1장, 자동 0장 생성 성공")
    void generate_manual_lottos() {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(1,
            List.of(List.of(1, 2, 3, 4, 5, 6)));
        AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator(0);

        LottoGenerator lottoGenerators = new LottoGenerators(
            manualLottoGenerator,
            autoLottoGenerator);

        assertThat(lottoGenerators.generate().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("수동 0장, 자동 1장 생성 성공")
    void generate_auto_lottos() {
        ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(1,
            List.of(List.of(1, 2, 3, 4, 5, 6)));
        AutoLottoGenerator autoLottoGenerator = new AutoLottoGenerator(0);

        LottoGenerator lottoGenerators = new LottoGenerators(
            manualLottoGenerator,
            autoLottoGenerator);

        assertThat(lottoGenerators.generate().size()).isEqualTo(1);
    }

}

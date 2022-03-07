import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.LottoGenerator;
import domain.LottoNumber;
import domain.ManualLottoGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManualLottoGeneratorTest {

    @Test
    @DisplayName("입력 받은 로또 장수만큼 수동 로또를 생성한다")
    void generate_lottos() {
        LottoGenerator manualLottoGenerator = new ManualLottoGenerator(
            List.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6)));

        assertThat(manualLottoGenerator.generate().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 로또 생성 확인")
    void check_lottos_generated() {
        LottoGenerator manualLottoGenerator = new ManualLottoGenerator(
            List.of(List.of(1, 2, 3, 4, 5, 6)));

        assertThat(manualLottoGenerator.generate()
            .get(0)
            .getLottoNumbers().size())
            .isEqualTo(6);
    }

    @Test
    @DisplayName("입력 받은 각각의 로또에 대하여 로또 번호에 중복이 있을 경우 예외 발생")
    void no_unique_fail() {
        LottoGenerator manualLottoGenerator = new ManualLottoGenerator(
            List.of(List.of(1, 2, 3, 4, 5, 5),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6)));

        assertThatThrownBy(
            manualLottoGenerator::generate
        ).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("입력받은 로또 개수가 0인 경우 빈 로또 리스트 반환")
    void no_maualLotto() {
        LottoGenerator manualLottoGenerator = new ManualLottoGenerator(new ArrayList<>());

        assertThat(manualLottoGenerator.generate().size())
            .isEqualTo(0);

    }


}

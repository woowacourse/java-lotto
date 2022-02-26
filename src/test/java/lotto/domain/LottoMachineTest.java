package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @DisplayName("로또 번호 6개를 생성할 수 있다.")
    @Test
    void createRandomLottoNumbers() {
        final Lotto randomLotto = LottoMachine.createRandomLotto();

        assertThat(randomLotto.getLottoNumbers()).hasSize(6);
    }

    @DisplayName("남은 번호로 랜덤 로또 생성 요청 시 음수가 들어오면 예외가 발생한다.")
    @Test
    void createLottoExceptionByNegativeCount() {
        final List<List<Integer>> automaticNumbers = new ArrayList<>();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoMachine.buyLotto(automaticNumbers, -1))
                .withMessage("[ERROR] 랜덤 로또 구매 갯수는 음수가 들어올 수 없습니다.");
    }

    @DisplayName("수동 구매 로또가 null이 들어오면 예외가 발생한다.")
    @Test
    void createLottoExceptionByNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> LottoMachine.buyLotto(null, 0))
                .withMessage("[ERROR] 수동 구매 로또는 null이 들어올 수 없습니다.");
    }

    @DisplayName("로또를 구매할 수 있다.")
    @Test
    void createLotto() {
        final List<List<Integer>> manualLottos = Stream.of(Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(2, 3, 4, 5, 6, 7))
                .collect(Collectors.toList());
        final Lottos lottos = LottoMachine.buyLotto(manualLottos, 3);

        assertThat(lottos.getLottos()).hasSize(5);
    }
}

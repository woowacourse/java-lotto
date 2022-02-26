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

    @DisplayName("랜덤한 로또 번호를 생성할 수 있다.")
    @Test
    void createRandomLottoNumbers() {
        final PurchaseLottoCounts counts = new PurchaseLottoCounts(0, 1);
        final Lottos lottos = LottoMachine.buyLotto(new ArrayList<>(), counts);

        assertThat(lottos.getLottos()).hasSize(1);
    }

    @DisplayName("수동 구매 로또가 null이 들어오면 예외가 발생한다.")
    @Test
    void createLottoExceptionByNull() {
        final PurchaseLottoCounts counts = new PurchaseLottoCounts(0, 1);
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> LottoMachine.buyLotto(null, counts))
                .withMessage("[ERROR] 수동 구매 로또는 null이 들어올 수 없습니다.");
    }

    @DisplayName("로또 구매 뭉치가 수동구매 로또 갯수와 다르면 예외가 발생한다.")
    @Test
    void createLottoExceptionByNotEqualsSize() {
        final List<List<Integer>> manualLottos = Stream.of(Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(2, 3, 4, 5, 6, 7))
                .collect(Collectors.toList());
        final PurchaseLottoCounts counts = new PurchaseLottoCounts(3, 1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoMachine.buyLotto(manualLottos, counts))
                .withMessage("[ERROR] 수동 로또 번호 갯수가 입력된 수동 구매 갯수와 다릅니다.");
    }

    @DisplayName("로또를 구매할 수 있다.")
    @Test
    void createLotto() {
        final List<List<Integer>> manualLottos = Stream.of(Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(2, 3, 4, 5, 6, 7))
                .collect(Collectors.toList());
        final PurchaseLottoCounts counts = new PurchaseLottoCounts(2, 3);
        final Lottos lottos = LottoMachine.buyLotto(manualLottos, counts);

        assertThat(lottos.getLottos()).hasSize(5);
    }
}

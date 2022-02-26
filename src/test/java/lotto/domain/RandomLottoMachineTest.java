package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomLottoMachineTest {

    @DisplayName("로또 번호 6개를 생성할 수 있다.")
    @Test
    void createRandomLottoNumbers() {
        final Lotto randomLotto = RandomLottoMachine.createRandomLotto();

        assertThat(randomLotto.getLottoNumbers()).hasSize(6);
    }

    @DisplayName("남은 번호로 랜덤 로또 생성 요청 시 음수가 들어오면 예외가 발생한다.")
    @Test
    void createLottoExceptionByNegativeCount() {
        final List<Lotto> automaticNumbers = new ArrayList<>();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> RandomLottoMachine.buyLotto(automaticNumbers, -1))
                .withMessage("[ERROR] 랜덤 로또 구매 갯수는 음수가 들어올 수 없습니다.");
    }
}

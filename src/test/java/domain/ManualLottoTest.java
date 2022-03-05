package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualLottoTest {

    @Test
    @DisplayName("수동 로또 생성 테스트")
    public void createManualLotto() {
        List<Integer> manualLottoNumbers = new ArrayList<>();
        ManualLotto manualLotto = new ManualLotto(manualLottoNumbers);
        assertThat(manualLotto).isInstanceOf(ManualLotto.class);
    }

    @Test
    @DisplayName("수동으로 만든 로또 번호 오름차순 정렬 테스트")
    public void checkLottoNumbersSort() {
        List<Integer> manualLottoNumbers = List.of(6, 5, 4, 3, 2, 1);
        ManualLotto manualLotto = new ManualLotto(manualLottoNumbers);
        for (int i = 0; i < 6; i++) {
            assertThat(manualLotto.getNumbers().get(i)).isEqualTo(i+1);
        }
    }
}

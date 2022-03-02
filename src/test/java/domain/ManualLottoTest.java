package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("정렬 되는지 테스트")
    public void checkLottoNumbersSort() {
        List<Integer> manualLottoNumbers = List.of(6, 5, 4, 3, 2, 1);
        ManualLotto manualLotto = new ManualLotto(manualLottoNumbers);
        assertThat(manualLotto.getManualLottoNumbers().get(0)).isEqualTo(1);
    }
}

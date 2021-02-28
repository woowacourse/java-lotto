package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoTest {

    @DisplayName("메뉴얼 로또 생성 테스트")
    @Test
    void create_manual_lotto() {

        String manualLottos = "1, 2, 3, 4, 5, 6";

        Lottos lottos = new Lottos();

        lottos.buyLotto(new LottoManualGenerator(), manualLottos);

        assertThat(lottos.getLottos()).isEqualTo(Arrays.asList(new Lotto(
                Arrays.asList(
                        new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)
        ))));

    }

    @DisplayName("유효하지 않는 번호 생성 테스트")
    @Test
    void wrong_manual_lottoNumbers() {
        assertThatThrownBy(() -> {
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 번호입니다.");
    }
}

package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottosTest {

    @DisplayName("수동, 자동 로또 생성 확인")
    @Test
    void createLottos() {
        Lottos.createManualLotto(Arrays.asList("1, 2, 3, 20, 21, 40", "1, 2, 20, 25, 29, 45"));
        Lottos.createAutoLotto(3);

        assertThat(Lottos.getPurchaseLottos()).hasSize(5);
    }

    @DisplayName("유효하지 않는 번호 생성 테스트")
    @Test
    void wrong_manual_lottoNumbers() {
        assertThatThrownBy(() -> {
            Lottos.createManualLotto(Arrays.asList("1, 2, 3, 4, 5, 46"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 번호입니다.");
    }

    @DisplayName("잘못된 입력시 에러 발생 확인")
    @Test
    void wrong_input_lottoNumbers() {
        assertThatThrownBy(() -> {
            Lottos.createManualLotto(Arrays.asList("a, 2, 3, 4, 5, 6"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자와 , 를 이용하여 입력해주세요.");
    }


}

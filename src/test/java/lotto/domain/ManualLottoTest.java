package lotto.domain;

import lotto.util.LottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoTest {

    @DisplayName("메뉴얼 로또 생성 테스트")
    @Test
    void create_manual_lotto() {
        List<String> manualLottoNumbers = Arrays.asList("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12");

        ManualLotto manualLotto = LottoFactory.createManualLotto(manualLottoNumbers);

        assertThat(manualLotto).isEqualTo(LottoFactory.createManualLotto(Arrays.asList("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12")));
    }

    @DisplayName("유효하지 않는 번호 생성 테스트")
    @Test
    void wrong_manual_lottoNumbers() {
        assertThatThrownBy(() -> {
            LottoFactory.createManualLotto(Arrays.asList("1, 2, 3, 4, 5, 46"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 번호입니다.");
    }

    @DisplayName("잘못된 입력시 에러 발생 확인")
    @Test
    void wrong_input_lottoNumbers() {
        assertThatThrownBy(() -> {
            LottoFactory.createManualLotto(Arrays.asList("a, 2, 3, 4, 5, 6"));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자와 , 를 이용하여 입력해주세요.");
    }
}

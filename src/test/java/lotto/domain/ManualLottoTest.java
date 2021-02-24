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
        List<Lotto> manualLottoNumbers = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12))
        );

        LottoFactory manualLotto = LottoFactory.of(manualLottoNumbers);

        assertThat(manualLotto).isEqualTo(LottoFactory.of(Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12))
        )));
    }

    @DisplayName("유효하지 않는 번호 생성 테스트")
    @Test
    void wrong_manual_lottoNumbers() {
        assertThatThrownBy(() -> {
            LottoFactory.of(Arrays.asList(
                    new Lotto(Arrays.asList(1, 2, 3, 4, 5, 46))
            ));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 로또 번호입니다.");
    }
}

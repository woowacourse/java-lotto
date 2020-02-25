package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class LottoFactoryTest {

    @Test
    @DisplayName("6자리 숫자가 생성되는지 확인")
    void createRandomLottoNumbers() {
        LottoFactory.getInstance();
        assertThat(LottoFactory.createRandomLottoNumbers().size()).isEqualTo(6);
    }
}

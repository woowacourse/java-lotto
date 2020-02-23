package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberBundleTest {
    @DisplayName("복권 번호가 들어있는지 확인하는 테스트")
    @Test
    public void containsTest() {
        LottoNumber input = new LottoNumber(10);

        Assertions.assertThat(LottoNumberBundle.contains(input)).isTrue();
    }
}

package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbersTest {
    @DisplayName("static 으로 생성한 숫자 풀의 유효성 검증")
    @Test
    void validateLottoNumbersTest() {
        List<LottoNumber> expected = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            expected.add(new LottoNumber(i));
        }

        Assertions.assertThat(LottoNumbers.getInstance()).containsSequence(expected);
    }
}

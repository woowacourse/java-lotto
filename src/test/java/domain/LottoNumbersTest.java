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

    @DisplayName("입력한 숫자에 맞는 로또 넘버를 반환하는지 테스트")
    @Test
    void getLottoNumberTest() {
        LottoNumber input = new LottoNumber(1);
        LottoNumber expected = LottoNumbers.getLottoNumber(1);

        Assertions.assertThat(input).isEqualTo(expected);
    }
}

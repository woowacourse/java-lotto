package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoNumberSplitTest {
    @DisplayName("입력한 문자열을 , 을 기준으로 나누는지 테스트")
    @Test
    void initializeLottoNumbersTest() {
        List<LottoNumber> lottoNumbers = LottoNumberSplit.initializeLottoNumbers("1, 2, 3, 4, 5, 6");

        Assertions.assertThat(lottoNumbers).containsExactly(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
    }
}

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("일치하는 숫자 갯수 구하기 테스트")
    void getMatchedNumberCountTest() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1, 2, 3, 4, 5, 6);
        LottoNumbers winnerNumbers = LottoNumbers.withSixNumbers(1, 10, 100, 1000, 10000, 6);
        int count = lottoNumbers.getMatchedNumberCountWith(winnerNumbers);
        assertThat(count).isEqualTo(2);
    }
}

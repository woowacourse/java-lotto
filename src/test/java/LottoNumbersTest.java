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

    @Test
    @DisplayName("로또 번호에 숫자 포함 여부 테스트")
    void contains() {
        LottoNumbers lottoNumbers = LottoNumbers.withSixNumbers(1, 2, 3, 4, 5, 6);
        assertThat(lottoNumbers.contains(4)).isTrue();
        assertThat(lottoNumbers.contains(9)).isFalse();
    }
}

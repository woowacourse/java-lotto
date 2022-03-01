package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @DisplayName("로또 번호 정상 생성 테스트")
    @Test
    void lottoNumbersTest() {
        LottoNumbers lottoNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        assertThat(lottoNumbers).isInstanceOf(LottoNumbers.class);
    }

    @DisplayName("로또 번호 5자리 생성 테스트")
    @Test
    void fiveLottoNumbersTest() {
        assertThatThrownBy(() ->
                makeLottoNumbers(new int[]{1, 2, 3, 4, 5}))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 번호 7자리 생성 테스트")
    @Test
    void sevenLottoNumbersTest() {
        assertThatThrownBy(() ->
                makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6, 7}))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("로또 번호 중복 테스트")
    @Test
    void duplicatedLottoNumbersTest() {
        assertThatThrownBy(() ->
                makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 5}))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }
}

package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    void 로또_번호_6자리_생성_테스트() {
        LottoNumbers lottoNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        assertThat(lottoNumbers).isInstanceOf(LottoNumbers.class);
    }

    @Test
    void 로또_번호_테스트_길이1() {
        assertThatThrownBy(() ->
                makeLottoNumbers(new int[]{1, 2, 3, 4, 5}))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 로또_번호_테스트_길이2() {
        assertThatThrownBy(() ->
                makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6, 7}))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 로또_번호_테스트_중복() {
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

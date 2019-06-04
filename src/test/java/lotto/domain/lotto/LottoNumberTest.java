package lotto.domain.lotto;

import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoNumberTest {
    @Test
    void 생성자_생성() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(LottoNumber.getNumber(i));
        }

        assertThat(LottoNumber.getLottoNumbers()).isEqualTo(lottoNumbers);
    }

    @Test
    void 생성자_오류_범위를_벗어남() {
        assertThatThrownBy(() -> LottoNumber.getNumber(0)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> LottoNumber.getNumber(46)).isInstanceOf(NullPointerException.class);
    }
}

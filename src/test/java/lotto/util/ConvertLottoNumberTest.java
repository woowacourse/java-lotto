package lotto.util;

import lotto.domain.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConvertLottoNumberTest {

    @Test
    void 로또_번호_리스트_변환() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.getInstance(1),
                LottoNumber.getInstance(2),
                LottoNumber.getInstance(3),
                LottoNumber.getInstance(4),
                LottoNumber.getInstance(5),
                LottoNumber.getInstance(6)
        );
        String lotto = "1,2,3,4,5,6";
        assertThat(lottoNumbers).isEqualTo(ConvertLottoNumber.run(lotto));
    }
}

package lotto.util;

import lotto.domain.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class IntConverterTest {

    @Test
    @DisplayName("정수를 로또 번호로 매핑한다")
    void testMapToLottoNumber() {
        List<LottoNumber> lottoNumbers = IntConverter.toLottoNumbers(List.of(1, 2, 3));
        Assertions.assertThat(lottoNumbers).contains(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3));
    }
}

package lotto.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersGeneratorTest {

    @DisplayName("getOriginLottoNumbers 메서드는 1 ~ 45의 로또 번호들의 리스트를 반환한다.")
    @Test
    void getOriginLottoNumbers() {
        List<LottoNumber> originLottoNumbers = LottoNumbersGenerator.getOriginLottoNumbers();
        List<LottoNumber> result = IntStream.rangeClosed(LottoNumber.LOTTO_MINIMUM, LottoNumber.LOTTO_MAXIMUM)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());

        Assertions.assertThat(originLottoNumbers).containsAll(result);
    }
}
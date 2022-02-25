package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumbersTest {

    private LottoNumbers lottoNumbers;

    @BeforeEach
    void 로또_번호_생성() {
        lottoNumbers = new LottoNumbers(
            Arrays.asList(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
    }

    @Test
    void get_불변_확인() {
        List<LottoNumber> lottoNumbers = this.lottoNumbers.get();
        assertThatThrownBy(() -> lottoNumbers.add(LottoNumber.valueOf(1))).isInstanceOf(
            Exception.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void contains_있는지_확인(int value) {
        assertThat(lottoNumbers.contains(LottoNumber.valueOf(value))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {9, 10, 11})
    void contains_없는지_확인(int value) {
        assertThat(lottoNumbers.contains(LottoNumber.valueOf(value))).isFalse();
    }
}
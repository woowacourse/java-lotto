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

    private LottoTicket lottoTicket;

    @BeforeEach
    void 로또_번호_생성() {
        lottoTicket = LottoTicket.of(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 로또_자동_생성_확인() {
        LottoTicket testLottoTicket = LottoTicket.ofAuto();
        assertThat(testLottoTicket.get().size()).isEqualTo(6);
    }

    @Test
    void get_불변_확인() {
        List<LottoNumber> lottoNumbers = this.lottoTicket.get();
        assertThatThrownBy(() -> lottoNumbers.add(LottoNumber.valueOf(1))).isInstanceOf(
            Exception.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void contains_있는지_확인(int value) {
        assertThat(lottoTicket.contains(LottoNumber.valueOf(value))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {9, 10, 11})
    void contains_없는지_확인(int value) {
        assertThat(lottoTicket.contains(LottoNumber.valueOf(value))).isFalse();
    }
}
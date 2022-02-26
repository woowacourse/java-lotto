package lotto.domain.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoLottoTicketGeneratorTest {

    @DisplayName("생성된 숫자는 6자리임을 보장한다.")
    @Test
    void checkLottoNumberCount() {
        // given
        LottoTicketGenerator lottoTicketGenerator = new AutoLottoTicketGenerator();

        // when
        List<LottoNumber> lottoNumbers = lottoTicketGenerator.generate();

        // then
        assertThat(lottoNumbers.size()).isEqualTo(6);
    }

    @DisplayName("생성된 숫자는 1 ~ 45의 범위를 가진다.")
    @Test
    void checkLottoNumberBoundary() {
        // given
        LottoTicketGenerator lottoTicketGenerator = new AutoLottoTicketGenerator();

        // when
        List<LottoNumber> lottoNumbers = lottoTicketGenerator.generate();

        // then
        assertThat(lottoNumbers).isNotIn(0, 46);
    }
}
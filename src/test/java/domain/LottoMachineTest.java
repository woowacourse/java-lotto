package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {
    @DisplayName("로또머신이 정상적인 로또 번호를 발행하는지 테스트")
    @Test
    void 로또_머신_로또_발행_테스트() {
        // given
        LottoMachine lottoMachine = new LottoMachine();

        // when
        LottoTicket lottoTicket = lottoMachine.generateLottoTicket(new FixedIntegerGenerator());

        // then
        Assertions.assertThat(lottoTicket.getSize()).isEqualTo(LottoNumbers.LOTTO_SIZE);
        for (int number : lottoTicket.getLottoNumbers()) {
            Assertions.assertThat(number).isGreaterThanOrEqualTo(LottoNumbers.LOTTO_MIN_NUMBER);
            Assertions.assertThat(number).isLessThanOrEqualTo(LottoNumbers.LOTTO_MAX_NUMBER);
        }
        Assertions.assertThat(lottoTicket.getLottoNumbers().stream().distinct()).hasSize(LottoNumbers.LOTTO_SIZE);
    }
}
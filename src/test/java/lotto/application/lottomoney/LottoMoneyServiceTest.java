package lotto.application.lottomoney;

import lotto.domain.lottomoney.dto.LottoMoneyDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMoneyServiceTest {
    @Test
    void DTO_생성() {
        LottoMoneyService lottoMoneyService = LottoMoneyService.getInstance();
        LottoMoneyDTO lottoMoneyDto = lottoMoneyService.makeLottoMoneyDto("3500");
        assertThat(lottoMoneyDto.getNumOfLotto()).isEqualTo(3);
        assertThat(lottoMoneyDto.getChange()).isEqualTo(500);
    }
}
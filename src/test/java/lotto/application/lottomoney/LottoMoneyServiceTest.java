package lotto.application.lottomoney;

import lotto.domain.lottomoney.dto.LottoMoneyDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMoneyServiceTest {
    @Test
    void DTO_생성() {
        LottoMoneyDto lottoMoneyDto = LottoMoneyService.makeLottoMoneyDto("3500");
        assertThat(lottoMoneyDto.getNumOfLotto()).isEqualTo(3);
        assertThat(lottoMoneyDto.getChange()).isEqualTo(500);
    }
}
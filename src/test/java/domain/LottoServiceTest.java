package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import dto.LottoDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.LottoService;

class LottoServiceTest {

    LottoService lottoService = new LottoService();

    @DisplayName("구입 금액만큼 발급 받은 자동 로또의 갯수를 확인한다.")
    @Test
    void issueLotto_count_correct() {
        lottoService.issueLotto(100000000, new ArrayList<>());
        final List<LottoDto> lottoDto = lottoService.getIssuedLotto();

        assertThat(lottoDto.size()).isEqualTo(100000);
    }
}

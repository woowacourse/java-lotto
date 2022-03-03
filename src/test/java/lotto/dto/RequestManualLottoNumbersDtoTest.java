package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RequestManualLottoNumbersDtoTest {

    @DisplayName("수동 로또 번호 리스트로 로또 리스트를 가지는 dto를 생성한다.")
    @Test
    void response_manual_lotto_numbers_dto_test() {
        // given
        List<LottoNumber> lottoNumbers = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6));
        List<List<LottoNumber>> manualLottoNumbers = new ArrayList<>();
        manualLottoNumbers.add(lottoNumbers);
        Lotto lotto = new Lotto(lottoNumbers);

        // when
        RequestManualLottoNumbersDto dto = new RequestManualLottoNumbersDto(manualLottoNumbers);

        // than
        assertThat(dto.getManualLottos()).hasSize(1);
        assertThat(dto.getManualLottos().get(0)).isEqualTo(lotto);
    }
}

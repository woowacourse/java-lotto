package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RequestManualLottoNumbersTest {

    @DisplayName("수동 로또 번호 리스트로 로또 리스트를 가지는 dto를 생성한다.")
    @Test
    void response_manual_lotto_numbers_dto_test() {
        List<List<LottoNumber>> manualLottoNumbers = new ArrayList<>();
        List<LottoNumber> lottoNumbers = List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        manualLottoNumbers.add(lottoNumbers);

        RequestManualLottoNumbers dto = new RequestManualLottoNumbers(manualLottoNumbers);

        assertThat(dto.getManualLottos()).hasSize(1);
        assertThat(dto.getManualLottos().get(0)).isEqualTo(new Lotto(lottoNumbers));
    }
}

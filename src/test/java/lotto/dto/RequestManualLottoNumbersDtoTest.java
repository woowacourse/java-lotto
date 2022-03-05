package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RequestManualLottoNumbersDtoTest {

    @DisplayName("수동 로또 번호 리스트로 로또 리스트를 가지는 dto를 생성한다.")
    @Test
    void response_manual_lotto_numbers_dto_test() {
        // given
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        List<List<LottoNumber>> manualLottoNumbers = new ArrayList<>();
        manualLottoNumbers.add(lottoNumbers);
        Lotto lotto = new Lotto(lottoNumbers);

        // when
        RequestManualLottoNumbersDto dto = new RequestManualLottoNumbersDto(manualLottoNumbers);

        // then
        assertThat(dto.getManualLottos()).hasSize(1);
        assertThat(dto.getManualLottos().get(0)).isEqualTo(lotto);
    }
}

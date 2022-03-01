package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResponsePurchaseResultsDtoTest {

    @DisplayName("구매한 모든 로또를 가지는 dto를 생성한다.")
    @Test
    void response_purchase_results_dto_test() {
        int manualLottoCount = 1;
        int autoLottoCount = 2;
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto0 = new Lotto(List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
        Lotto lotto1 = new Lotto(List.of(LottoNumber.valueOf(7), LottoNumber.valueOf(8), LottoNumber.valueOf(9),
                LottoNumber.valueOf(10), LottoNumber.valueOf(11), LottoNumber.valueOf(12)));
        Lotto lotto2 = new Lotto(List.of(LottoNumber.valueOf(13), LottoNumber.valueOf(14), LottoNumber.valueOf(15),
                LottoNumber.valueOf(16), LottoNumber.valueOf(17), LottoNumber.valueOf(18)));
        lottos.add(lotto0);
        lottos.add(lotto1);
        lottos.add(lotto2);

        ResponsePurchaseResultsDto dto = new ResponsePurchaseResultsDto(lottos, manualLottoCount, autoLottoCount);

        assertThat(dto.getLottos()).hasSize(lottos.size());
        assertThat(dto.getLottos().get(0)).isEqualTo(lotto0);
        assertThat(dto.getLottos().get(1)).isEqualTo(lotto1);
        assertThat(dto.getLottos().get(2)).isEqualTo(lotto2);
        assertThat(dto.getManualLottoCount()).isEqualTo(manualLottoCount);
        assertThat(dto.getAutoLottoCount()).isEqualTo(autoLottoCount);
    }
}

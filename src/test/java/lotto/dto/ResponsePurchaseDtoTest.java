package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResponsePurchaseDtoTest {

    @DisplayName("로또 구매 결과로 객체를 생성한다.")
    @Test
    void response_purchase_dto_test() {
        int manualLottoCount = 1;
        int autoLottoCount = 2;
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto0 = new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        Lotto lotto1 = new Lotto(List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9),
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)));
        Lotto lotto2 = new Lotto(List.of(new LottoNumber(13), new LottoNumber(14), new LottoNumber(15),
                new LottoNumber(16), new LottoNumber(17), new LottoNumber(18)));
        lottos.add(lotto0);
        lottos.add(lotto1);
        lottos.add(lotto2);

        ResponsePurchaseDto dto = new ResponsePurchaseDto(lottos, manualLottoCount, autoLottoCount);

        assertThat(dto.getLottos()).hasSize(lottos.size());
        assertThat(dto.getLottos().get(0)).isEqualTo(lotto0);
        assertThat(dto.getLottos().get(1)).isEqualTo(lotto1);
        assertThat(dto.getLottos().get(2)).isEqualTo(lotto2);
        assertThat(dto.getManualLottoCount()).isEqualTo(manualLottoCount);
        assertThat(dto.getAutoLottoCount()).isEqualTo(autoLottoCount);
    }
}

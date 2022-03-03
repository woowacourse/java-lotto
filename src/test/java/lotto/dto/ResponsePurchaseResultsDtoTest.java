package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.LottoGame;
import lotto.domain.generator.CustomLottoGenerator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResponsePurchaseResultsDtoTest {

    @DisplayName("구매한 모든 로또를 가지는 dto를 생성한다.")
    @Test
    void response_purchase_results_dto_test() {
        // given
        Money money = new Money(3000);
        int manualLottoCount = 1;
        int autoLottoCount = 2;
        Lotto lotto = new Lotto(List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));
        List<Lotto> lottos = List.of(lotto);

        CustomLottoGenerator generator = new CustomLottoGenerator();
        Lotto autoLotto = generator.generate();

        LottoGame lottoGame = new LottoGame();
        ResponsePurchaseResults manualResults = lottoGame.purchaseManual(lottos, money);
        ResponsePurchaseResults autoResults =
                lottoGame.purchaseAuto(generator, manualResults.getChanges());

        // when
        ResponsePurchaseResultsDto dto = new ResponsePurchaseResultsDto(manualResults, autoResults);

        // then
        assertThat(dto.getManualLottos().get(0)).isEqualTo(lotto);
        assertThat(dto.getAutoLottos().get(0)).isEqualTo(autoLotto);
        assertThat(dto.getAutoLottos().get(1)).isEqualTo(autoLotto);
        assertThat(dto.getManualLottoCount()).isEqualTo(manualLottoCount);
        assertThat(dto.getAutoLottoCount()).isEqualTo(autoLottoCount);
    }
}

package lotto;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoBundleTest {

    @Test
    @DisplayName("구매 금액으로 자동구매만 할 경우 로또의 사이즈 확인")
    void lottoCountByPurchaseAmountTest() {
        Money money = new Money("10800");
        LottoCount lottoCount = new LottoCount(money.getLottoCount());
        String[] inputs = {};
        LottoBundle lottoBundle = LottoBundleFactory.generate(lottoCount, new ManualCount("0", lottoCount), inputs);
        assertThat(lottoBundle.getLottoBundle().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("수동으로 로또 번호 입력시 정상적으로 LottoBundle 에 추가되는지 확인")
    void addLottoWithManualStringArrayInputTest() {
        String[] inputLottoNumbers = {"1", "2", "3", "4", "5", "6"};
        LottoFactory lottoFactory = new ManualLottoFactory(inputLottoNumbers);
        Lotto lotto = lottoFactory.createOneLotto();
        LottoBundle lottoBundle = new LottoBundle();
        lottoBundle.addLotto(lotto);
        assertThat(lottoBundle.getLottoBundle().size()).isEqualTo(1);
    }
}

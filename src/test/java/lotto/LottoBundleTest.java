package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoBundleTest {

    @Test
    void 구매금액으로_자동구매만_할_경우_로또의_사이즈_확인() {
        Money money = new Money("10800");
        int lottoCount = money.getLottoCount();
        LottoBundle lottoBundle = new LottoBundle();
        lottoBundle.addLottoBundle(LottoBundleFactory.createAutoLottoBundle(lottoCount));
        assertThat(lottoBundle.getLottoBundle().size()).isEqualTo(10);
    }

    @Test
    void 로또_한_개_추가_테스트() {
        String[] inputLottoNumbers = {"1", "2", "3", "4", "5", "6"};
        Lotto lotto = LottoFactory.createOneManualLotto(inputLottoNumbers);
        LottoBundle lottoBundle = new LottoBundle();
        lottoBundle.addLotto(lotto);
        assertThat(lottoBundle.getLottoBundle().size()).isEqualTo(1);
    }
}

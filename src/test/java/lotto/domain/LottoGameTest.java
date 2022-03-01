package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.domain.generator.CustomLottoGenerator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.dto.ResponsePurchaseResultsDto;
import lotto.dto.ResponseWinningResultsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    @DisplayName("로또를 구매하고 당첨 확인을 하는 LottoGame 객체를 생성한다")
    @Test
    void lottoGame_constructor_test() {
        assertThatNoException().isThrownBy(LottoGame::new);
    }

    @DisplayName("주어진 금액으로 로또를 수동을 최대한 구매 후 남은 금액만큼 자동으로 구매한다")
    @Test
    void purchase_test() {
        LottoGame lottoGame = new LottoGame();
        List<LottoNumber> manualLottoNumber = List
                .of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6));
        List<Lotto> manualLottos = new ArrayList<>();
        manualLottos.add(new Lotto(manualLottoNumber));

        ResponsePurchaseResultsDto dto =
                lottoGame.purchase(new Money(10000), manualLottos, new CustomLottoGenerator());

        Lotto lotto = new Lotto(List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));

        assertThat(dto.getLottos()).hasSize(10);
        assertThat(dto.getLottos().get(0)).isEqualTo(lotto);
        assertThat(dto.getManualLottoCount()).isEqualTo(1);
        assertThat(dto.getAutoLottoCount()).isEqualTo(9);
    }

    @DisplayName("로또를 수동으로 1회 구매한다")
    @Test
    void purchase_manual_test() {
        LottoGame lottoGame = new LottoGame();
        List<LottoNumber> manualLottoNumber = List
                .of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6));
        List<Lotto> manualLottos = new ArrayList<>();
        manualLottos.add(new Lotto(manualLottoNumber));

        ResponsePurchaseResultsDto dto =
                lottoGame.purchase(new Money(1000), manualLottos, new CustomLottoGenerator());

        Lotto lotto = new Lotto(List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));

        assertThat(dto.getLottos()).hasSize(1);
        assertThat(dto.getLottos().get(0)).isEqualTo(lotto);
        assertThat(dto.getManualLottoCount()).isEqualTo(1);
        assertThat(dto.getAutoLottoCount()).isEqualTo(0);
    }

    @DisplayName("로또를 자동으로 1회 구매한다")
    @Test
    void purchase_auth_test() {
        LottoGame lottoGame = new LottoGame();
        List<Lotto> emptyManualLottos = new ArrayList<>(new ArrayList<>());
        ResponsePurchaseResultsDto dto =
                lottoGame.purchase(new Money(1000), emptyManualLottos, new CustomLottoGenerator());

        Lotto lotto = new Lotto(List.of(LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)));

        assertThat(dto.getLottos()).hasSize(1);
        assertThat(dto.getLottos().get(0)).isEqualTo(lotto);
        assertThat(dto.getManualLottoCount()).isEqualTo(0);
        assertThat(dto.getAutoLottoCount()).isEqualTo(1);
    }

    @DisplayName("당첨 결과를 계산하여 결과를 반환한다")
    @Test
    void confirmWinnings_test() {
        List<LottoNumber> lottoNumbers;
        LottoNumber bonusNumber = LottoNumber.valueOf(30);
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(LottoNumber.valueOf(i));
        }
        List<Lotto> emptyManualLottos = new ArrayList<>(new ArrayList<>());

        LottoGame lottoGame = new LottoGame();
        lottoGame.purchase(new Money(10000), emptyManualLottos, new CustomLottoGenerator());
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(lottoNumbers), bonusNumber);
        ResponseWinningResultsDto dto = lottoGame.confirmWinnings(winningNumbers);
        Map<LottoPrize, Integer> results = dto.getResults();

        assertThat(results.get(LottoPrize.FIRST)).isEqualTo(10);
    }

    @DisplayName("1000원으로 로또를 살 수 있다.")
    @Test
    void canBuyLotto_true_test() {
        LottoGame lottoGame = new LottoGame();
        Money money = new Money(1000);

        assertThat(lottoGame.canBuyLotto(money)).isTrue();
    }

    @DisplayName("1000원 미만으로 로또를 살 수 없다.")
    @Test
    void canBuyLotto_false_test() {
        LottoGame lottoGame = new LottoGame();
        Money money = new Money(999);

        assertThat(lottoGame.canBuyLotto(money)).isFalse();
    }
}

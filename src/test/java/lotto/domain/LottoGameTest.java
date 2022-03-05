package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.generator.CustomLottoGenerator;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;
import lotto.dto.ResponsePurchaseResults;
import lotto.dto.ResponseWinningResultsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    @DisplayName("로또를 구매하고 당첨 확인을 하는 객체를 생성한다")
    @Test
    void lottoGame_constructor_test() {
        assertThatNoException().isThrownBy(LottoGame::new);
    }

    @DisplayName("로또를 수동으로 1회 구매한다")
    @Test
    void purchase_manual_test() {
        // given
        Money money = new Money(1000);

        LottoGame lottoGame = new LottoGame();
        List<Lotto> manualLottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList())));
        Lotto compareLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList()));

        // when
        ResponsePurchaseResults results = lottoGame.purchaseManual(manualLottos, money);

        // then
        assertThat(results.getLottos()).hasSize(1);
        assertThat(results.getLottos().get(0)).isEqualTo(compareLotto);
        assertThat(results.getChanges().get()).isEqualTo(0);
    }

    @DisplayName("로또를 자동으로 1회 구매한다")
    @Test
    void purchase_auto_test() {
        // given
        Money money = new Money(1000);
        CustomLottoGenerator generator = new CustomLottoGenerator();

        LottoGame lottoGame = new LottoGame();
        Lotto compareLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList()));

        // when
        ResponsePurchaseResults results = lottoGame.purchaseAuto(generator, money);

        // then
        assertThat(results.getLottos()).hasSize(1);
        assertThat(results.getLottos().get(0)).isEqualTo(compareLotto);
        assertThat(results.getChanges().get()).isEqualTo(0);
    }

    @DisplayName("당첨 결과를 계산하여 결과를 반환한다")
    @Test
    void confirmWinnings_test() {
        // given
        Money money = new Money(10000);
        List<LottoNumber> lottoNumbers = List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        LottoNumber bonusNumber = LottoNumber.valueOf(30);
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(lottoNumbers), bonusNumber);

        LottoGame lottoGame = new LottoGame();
        List<Lotto> emptyManualLottos = new ArrayList<>(new ArrayList<>());
        ResponsePurchaseResults manualResults = lottoGame.purchaseManual(emptyManualLottos, money);
        lottoGame.purchaseAuto(new CustomLottoGenerator(), manualResults.getChanges());

        // when
        ResponseWinningResultsDto dto = lottoGame.confirmWinnings(winningNumbers);

        // then
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

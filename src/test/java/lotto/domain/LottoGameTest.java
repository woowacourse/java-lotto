package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {

    @DisplayName("LottoGame 생성자는 입력한 money로 내부값을 초기화한다.")
    @Test
    void constructor() {
        assertThatNoException().isThrownBy(() -> new LottoGame(new Money(10000)));
    }

    @DisplayName("purchase 메서드는 lottoGame에 입력한 money만큼의 Lotto를 구매하여 Lottos에 추가한다")
    @Test
    void purchase() {
        LottoGame lottoGame = new LottoGame(new Money(10000));
        lottoGame.purchase(new ArrayList<>());

        List<Lotto> lottoResults = lottoGame.getLottos();

        assertThat(lottoResults.size()).isEqualTo(10);
    }

    @DisplayName("purchase 메서드는 수동입력 번호들을 입력받아 Lottos에 추가한다.")
    @Test
    void purchase_manual() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> integers1 = List.of(10, 20, 30, 33, 43, 45);

        List<List<Integer>> manualNumbers = new ArrayList<>(List.of(integers, integers1));
        LottoGame lottoGame = new LottoGame(new Money(2000));
        lottoGame.purchase(manualNumbers);

        List<Lotto> lottos = lottoGame.getLottos();

        List<LottoNumber> lottoNumbers = List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        List<LottoNumber> lottoNumbers2 = List.of(new LottoNumber(10), new LottoNumber(20), new LottoNumber(30),
                new LottoNumber(33), new LottoNumber(43), new LottoNumber(45));

        List<Lotto> result = new ArrayList<>(
                List.of(new Lotto(lottoNumbers), new Lotto(lottoNumbers2)));

        assertThat(lottos).isEqualTo(result);
    }

    @DisplayName("confirmWinnings 메서드는 당첨 번호를 입력받아 당첨 결과인 LottoResults를 반환한다.")
    @Test
    void confirmWinnings() {
        List<LottoNumber> lottoNumbers;
        LottoNumber bonusNumber = new LottoNumber(30);
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }

        LottoGame lottoGame = new LottoGame(new Money(10000));
        lottoGame.purchase(new ArrayList<>());
        WinningLotto winningLotto = new WinningLotto(new Lotto(lottoNumbers), bonusNumber);
        assertThat(lottoGame.confirmWinnings(winningLotto)).isInstanceOf(LottoResults.class);
    }
}

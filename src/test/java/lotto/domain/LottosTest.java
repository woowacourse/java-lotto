package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @DisplayName("Lottos 생성자는 인자를 받지 않는다.")
    @Test
    void constructor() {
        assertThatNoException().isThrownBy(Lottos::new);
    }

    @DisplayName("purchase 메서드는 입력한 Money 만큼의 로또를 생성하여 저장한다.")
    @Test
    void purchase() {
        Lottos lottos = new Lottos();
        lottos.purchase(new Money(10000));

        assertThat(lottos.getLottos().size()).isEqualTo(10);
    }

    @DisplayName("confirmWinnings 메서드는 WinningNumbers를 입력받아 당첨결과를 맵으로 반환한다.")
    @Test
    void confirmWinnings() {
        Lottos lottos = new Lottos();
        lottos.purchase(new Money(10000));

        WinningNumbers winningNumbers = getWinningNumbers();

        Map<LottoPrize, Integer> testMap = initTestMap(lottos, winningNumbers);
        Map<LottoPrize, Integer> lottoMatches = lottos.confirmWinnings(winningNumbers);

        assertThat(testMap).isEqualTo(lottoMatches);
    }

    private Map<LottoPrize, Integer> initTestMap(Lottos lottos, WinningNumbers winningNumbers) {
        Map<LottoPrize, Integer> myMap = new HashMap<>();
        for (LottoPrize prize : LottoPrize.values()) {
            myMap.put(prize, 0);
        }

        List<Lotto> myLottos = lottos.getLottos();
        for (Lotto lotto : myLottos) {
            LottoPrize prize = lotto.confirmWinning(winningNumbers);
            myMap.put(prize, myMap.get(prize) + 1);
        }

        return myMap;
    }

    private WinningNumbers getWinningNumbers() {
        List<LottoNumber> lottoNumbers;
        LottoNumber bonusNumber = new LottoNumber(30);
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        return new WinningNumbers(new Lotto(lottoNumbers), bonusNumber);
    }
}

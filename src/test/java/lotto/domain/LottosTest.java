package lotto.domain;

import static lotto.utils.LottoGenerator.generateLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @DisplayName("Lottos 생성자는 Lotto 리스트를 입력받아 값을 초기화한다.")
    @Test
    void constructor() {
        List<Lotto> testLottos = getTestLottos();

        assertThatNoException().isThrownBy(() -> new Lottos(testLottos));
    }

    @DisplayName("Lottos 생성자는 null을 입력받으면 예외를 발생한다.")
    @Test
    void constructor_errorOnNull() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new Lottos(null))
                .withMessage("입력된 값이 null이면 안됩니다.");
    }

    @DisplayName("confirmWinnings 메서드는 WinningNumbers를 입력받아 당첨결과를 맵으로 반환한다.")
    @Test
    void confirmWinnings() {
        List<Lotto> lottoPurchased = getTestLottos();

        Lottos lottos = new Lottos(lottoPurchased);

        WinningNumbers winningNumbers = getWinningNumbers();

        Map<LottoPrize, Integer> testMap = initTestMap(lottos, winningNumbers);
        Map<LottoPrize, Integer> lottoMatches = lottos.confirmWinnings(winningNumbers);

        assertThat(testMap).isEqualTo(lottoMatches);
    }

    private List<Lotto> getTestLottos() {
        List<Lotto> lottoPurchased = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lottoPurchased.add(new Lotto(generateLottoNumbers()));
        }
        return lottoPurchased;
    }

    private Map<LottoPrize, Integer> initTestMap(Lottos lottos, WinningNumbers winningNumbers) {
        Map<LottoPrize, Integer> myMap = new EnumMap<>(LottoPrize.class);
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

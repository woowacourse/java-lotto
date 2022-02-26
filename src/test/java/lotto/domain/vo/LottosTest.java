package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoPrize;
import lotto.domain.generator.CustomLottoGenerator;
import lotto.domain.generator.Generator;
import lotto.domain.generator.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @DisplayName("Lottos 생성자 테스트")
    @Test
    void lottos_constructor_test() {
        List<Lotto> randomLottos = new ArrayList<>();
        Generator lottoGenerator = new CustomLottoGenerator();
        randomLottos.add(lottoGenerator.generate());
        Lottos lottos = new Lottos(randomLottos);

        assertThat(lottos)
                .extracting("lottos")
                .asList()
                .hasSize(1);
    }

    @DisplayName("confirmWinnings 메서드 테스트")
    @Test
    void confirmWinnings_test() {
        Generator lottoGenerator = new CustomLottoGenerator();
        Lottos lottos = new Lottos(new ArrayList<>(){{
            add(lottoGenerator.generate());
        }});

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
        WinningNumbers winningNumbers = new WinningNumbers(new Lotto(lottoNumbers), bonusNumber);
        return winningNumbers;
    }
}

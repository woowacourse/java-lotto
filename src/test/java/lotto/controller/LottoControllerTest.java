package lotto.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoControllerTest {

    Lottos lottos;
    WinningLotto winningLotto;

    @BeforeEach
    void init() {
        lottos = new Lottos(new Money(0));
        Lotto lotto1 = makeLotto(new int[]{1, 2, 3, 4, 5, 6});
        Lotto lotto2 = makeLotto(new int[]{3, 4, 5, 6, 7, 8});
        lottos.insert(lotto1);
        lottos.insert(lotto2);
        winningLotto = makeWinningLotto(new int[]{2, 3, 4, 5, 6, 7}, 8);
    }

    @Test
    void test() {
        assertThatCode(() ->
                new LottoMachine(lottos, winningLotto)).doesNotThrowAnyException();
    }

    private Lotto makeLotto(int[] numbers) {
        return new Lotto((minimumNumber, maximumNumber, lottoLength) -> makeLottoNumbers(numbers));
    }

    private WinningLotto makeWinningLotto(int[] numbers, int bonusNumber) {
        return new WinningLotto(makeLottoNumbers(numbers), new LottoNumber(bonusNumber));
    }

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }
}

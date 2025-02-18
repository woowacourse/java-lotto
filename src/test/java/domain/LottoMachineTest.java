package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {
    @DisplayName("로또를 원하는 개수만큼 정상적으로 발행한다")
    @Test
    void issueLottos() {
        List<List<Integer>> preDefinedNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 5, 7),
                Arrays.asList(8, 9, 10, 11, 12, 13),
                Arrays.asList(40, 41, 42, 43, 44, 45)
        );

        MockNumberGenerator mockNumberGenerator = new MockNumberGenerator(preDefinedNumbers);
        LottoMachine lottoMachine = new LottoMachine(mockNumberGenerator);

        List<Lotto> issuedLotto = lottoMachine.issueLotto(4);

        assertEquals(4, issuedLotto.size());
        assertEquals(issuedLotto.get(0).getNumbers(), Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(issuedLotto.get(1).getNumbers(), Arrays.asList(1, 2, 3, 4, 5, 7));
        assertEquals(issuedLotto.get(2).getNumbers(), Arrays.asList(8, 9, 10, 11, 12, 13));
        assertEquals(issuedLotto.get(3).getNumbers(), Arrays.asList(40, 41, 42, 43, 44, 45));
    }

    @DisplayName("만약 로또가 같은 번호를 가질 시, 제외하고 원하는 개수만큼의 로또를 발행한다")
    @Test
    void issueLottosInDuplication() {
        List<List<Integer>> preDefinedNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(8, 9, 10, 11, 12, 13),
                Arrays.asList(40, 41, 42, 43, 44, 45)
        );

        MockNumberGenerator mockNumberGenerator = new MockNumberGenerator(preDefinedNumbers);
        LottoMachine lottoMachine = new LottoMachine(mockNumberGenerator);

        List<Lotto> issuedLotto = lottoMachine.issueLotto(3);

        assertEquals(3, issuedLotto.size());
        assertEquals(issuedLotto.get(0).getNumbers(), Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(issuedLotto.get(1).getNumbers(), Arrays.asList(8, 9, 10, 11, 12, 13));
        assertEquals(issuedLotto.get(2).getNumbers(), Arrays.asList(40, 41, 42, 43, 44, 45));
    }
}

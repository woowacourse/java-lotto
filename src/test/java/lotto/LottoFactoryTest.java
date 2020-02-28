package lotto;

import domain.*;
import org.assertj.core.api.HamcrestCondition;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    @Test
    void 생성된_로또가_6개의_숫자로_이루어져있는지_테스트() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        List<Lotto> myLotto = LottoFactory.createLottoTickets(1, randomNumberGenerator);
        assertThat(myLotto.get(0).getSize()).isEqualTo(6);
    }

    @Test
    void 생성된_로또가_입력한_값을_돌려주는지_테스트() {
        Generator manualNumberGenerator = new ManualNumberGenerator();
        List<Lotto> myLotto = LottoFactory.createLottoTickets(1, manualNumberGenerator);

        List<LottoNumber> lotto = new ArrayList<>();
        lotto.add(LottoNumber.valueOf("1"));
        lotto.add(LottoNumber.valueOf("2"));
        lotto.add(LottoNumber.valueOf("3"));
        lotto.add(LottoNumber.valueOf("4"));
        lotto.add(LottoNumber.valueOf("5"));
        lotto.add(LottoNumber.valueOf("6"));

        assertThat(myLotto.get(0).getLotto()).isEqualTo(lotto);
    }

    @Test
    void 수동으로_입력한_로또가_생성되는지_테스트() {
        Set<LottoNumber> testSet = new HashSet<>();
        testSet.add(LottoNumber.valueOf("1"));
        testSet.add(LottoNumber.valueOf("2"));
        testSet.add(LottoNumber.valueOf("3"));
        testSet.add(LottoNumber.valueOf("4"));
        testSet.add(LottoNumber.valueOf("5"));
        testSet.add(LottoNumber.valueOf("6"));
        Lotto manualLotto = new Lotto(testSet);
        List<String> manualLottoNumbers = Arrays.asList("1","2","3","4","5","6");

        assertThat(LottoFactory.createManualLotto(manualLottoNumbers)).isEqualTo(manualLotto);
    }
}

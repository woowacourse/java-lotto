package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}

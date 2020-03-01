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
        List<List<String>> manualLottoNumbers = new ArrayList<>();
        List<String> manualLottoNumber = Arrays.asList("1","2","3","4","5","6");
        manualLottoNumbers.add(manualLottoNumber);

        List<Lotto> myLotto = LottoFactory.createLottoTickets(new LottoCount(1, "1"), randomNumberGenerator, manualLottoNumbers);
        assertThat(myLotto.get(0).getSize()).isEqualTo(6);
    }

    @Test
    void 생성된_로또가_입력한_값을_돌려주는지_테스트() {
        Generator manualNumberGenerator = new ManualNumberGenerator();
        List<List<String>> numbers = new ArrayList<>();
        List<Lotto> myLotto = LottoFactory.createLottoTickets(new LottoCount(1, "0"), manualNumberGenerator, numbers);

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
    void 전체_로또_티켓들이_생성되는지_테스트() {
        Set<LottoNumber> testSet = new HashSet<>();
        testSet.add(LottoNumber.valueOf("11"));
        testSet.add(LottoNumber.valueOf("12"));
        testSet.add(LottoNumber.valueOf("13"));
        testSet.add(LottoNumber.valueOf("14"));
        testSet.add(LottoNumber.valueOf("15"));
        testSet.add(LottoNumber.valueOf("16"));
        Lotto manualLotto1 = new Lotto(testSet);

        Set<LottoNumber> testSet2 = new HashSet<>();
        testSet2.add(LottoNumber.valueOf("21"));
        testSet2.add(LottoNumber.valueOf("22"));
        testSet2.add(LottoNumber.valueOf("23"));
        testSet2.add(LottoNumber.valueOf("24"));
        testSet2.add(LottoNumber.valueOf("25"));
        testSet2.add(LottoNumber.valueOf("26"));
        Lotto manualLotto2 = new Lotto(testSet2);

        Set<LottoNumber> testSet3 = new HashSet<>();
        testSet3.add(LottoNumber.valueOf("1"));
        testSet3.add(LottoNumber.valueOf("2"));
        testSet3.add(LottoNumber.valueOf("3"));
        testSet3.add(LottoNumber.valueOf("4"));
        testSet3.add(LottoNumber.valueOf("5"));
        testSet3.add(LottoNumber.valueOf("6"));
        Lotto autoLotto1 = new Lotto(testSet3);

        List<Lotto> testLottoTickets = new ArrayList<>();
        testLottoTickets.add(manualLotto1);
        testLottoTickets.add(manualLotto2);
        testLottoTickets.add(autoLotto1);

        LottoCount lottoCount = new LottoCount(3, "2");
        Generator manualNumberGenreator = new ManualNumberGenerator();
        List<String> manualLottoNumber1 = Arrays.asList("11","12","13","14","15","16");
        List<String> manualLottoNumber2 = Arrays.asList("21","22","23","24","25","26");
        List<List<String>> manualLottoNumbers = new ArrayList<>();
        manualLottoNumbers.add(manualLottoNumber1);
        manualLottoNumbers.add(manualLottoNumber2);

        assertThat(LottoFactory.createLottoTickets(lottoCount, manualNumberGenreator, manualLottoNumbers)).isEqualTo(testLottoTickets);
    }
}

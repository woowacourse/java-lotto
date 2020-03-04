package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoFactoryTest {
    @Test
    @DisplayName("Lotto가 자동으로 정확하게 생성되는지 테스트")
    void makeLottoTest() {
        assertThat(LottoFactory.create());
    }

    @ParameterizedTest
    @DisplayName("Number Pool에서 정확한 Number를 가져오는지 테스트")
    @ValueSource(strings = {"1", "2", "44", "45"})
    void getNumberTest(String input) {
        assertThat(LottoFactory.of(input)).isEqualTo(new Number(input));
    }

    @ParameterizedTest
    @DisplayName("로또 수동 생성 테스트")
    @CsvSource(value = {"1,2,3,4,5,6:4", "4,7,44,42,33,13:2"}, delimiter = ':')
    void makeLottoManually(String input, int expected) {
        Lotto lotto = LottoFactory.create(input);
        List<Number> numbers = Arrays.asList(
                LottoFactory.of("3"),
                LottoFactory.of("4"),
                LottoFactory.of("5"),
                LottoFactory.of("6"),
                LottoFactory.of("7"),
                LottoFactory.of("45")
        );
        Lotto winningLotto = new Lotto(numbers);
        assertThat(lotto.compare(winningLotto)).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 장수 만큼 로또리스트를 정확하게 생성하는지 테스트")
    void makeLottosTest() {
        Lottos lottos = LottoFactory.create(3);
        assertThat(lottos.getSize()).isEqualTo(3);

    }

    @Test
    void makeManualLottosTest(){
        List<String> manual = Arrays.asList("1,2,3,4,5,6", "3,4,5,6,7,8");
        Lottos lottos = LottoFactory.create(manual, 10);
        Lotto firstLotto = LottoFactory.create("1,2,3,4,5,6");
        Lotto secondLotto = LottoFactory.create("3,4,5,6,7,8");
        Iterator<Lotto> lottoIterator = lottos.iterator();
        assertThat(lottoIterator.next().compare(firstLotto)).isEqualTo(6);
        assertThat(lottoIterator.next().compare(secondLotto)).isEqualTo(6);
    }
}
package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosGeneratorTest {

    @Test
    @DisplayName("로또티켓 중복 검사")
    void checkDuplicated() {
        //given
        int lottosSize = 14;
        LottoNumberShuffler lottoNumberShuffler = new CollectionLottoNumberShuffler();
        LottosGenerator lottosGenerator = new LottosGenerator(lottoNumberShuffler);
        //when
        Lottos lottos = lottosGenerator.generate(lottosSize);
        Set<Lotto> lottoNumbersSet = new HashSet<>(lottos.getLottos());
        assertThat(lottos.getLottos().size()).isEqualTo(lottoNumbersSet.size());
    }

    @Test
    @DisplayName("로또갯수를 가격으로 치환")
    void LottoTicketToPrice() {
        //given
        Money money = new Money(1000);
        Lotto lotto = new Lotto(
            Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::create).collect(Collectors.toSet()));

        Set<Lotto> lottoSet = new HashSet<>(Arrays.asList(lotto));
        //when
        Lottos lottos = new Lottos(lottoSet);
        //then
        assertThat(lottos.toPrice()).isEqualTo(money);
    }
}

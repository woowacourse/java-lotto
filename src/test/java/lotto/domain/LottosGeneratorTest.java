package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

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


}

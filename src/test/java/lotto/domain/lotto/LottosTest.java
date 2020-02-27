package lotto.domain.lotto;

import lotto.domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    private static Lottos lottos;
    private static Lotto lottoFirst;
    private static Lotto lottoSecond;

    @BeforeAll
    static void name() {
        Set<LottoNumber> lottoNumbers = new HashSet<>(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)
        ));
        Set<LottoNumber> lottoNumbers2 = new HashSet<>(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)
        ));
        lottoFirst = new Lotto(lottoNumbers);
        lottoSecond = new Lotto(lottoNumbers2);
        lottos = new Lottos(Arrays.asList(lottoFirst, lottoSecond));
    }

    @DisplayName("Lottos 생성자 매개변수에 올바른 Lotto 리스트가 들어오면 정상적으로 Lottos객체 생성")
    @Test
    void constructor_validLottoList_createLottos() {
        assertThat(lottos).isInstanceOf(Lottos.class);
    }

    @DisplayName("add 메서드를 이용해 lottosFirst에 lottosSeccond를 더하는 기능을 테스트 ")
    @Test
    void add_inputLottoList_addLottoList() {
        Lottos lottosFirst = new Lottos(Arrays.asList(lottoFirst));
        Lottos lottosSeccond = new Lottos(Arrays.asList(lottoSecond));
        assertThat(lottosFirst.add(lottosSeccond)).isEqualTo(lottos);
    }
}

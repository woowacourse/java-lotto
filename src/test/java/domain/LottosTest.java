package domain;

import generator.FakeGenerator;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottosTest {
    private static LottosFactory lottosFactory;

    @BeforeAll
    static void setUp(){
        lottosFactory = new LottosFactory(new FakeGenerator());
    }

    @Test
    void 로또_결과를_계산할_수_있다() {
        //given
        Lottos lottos = lottosFactory.from(1);
        WinningLotto winningLotto = new WinningLotto(List.of(1,2,3,4,5,6), "7");

        //when-then
        assertThat(lottos.calculateWinningResult(winningLotto).get(Rank.FIRST)).isEqualTo(1);
    }
}
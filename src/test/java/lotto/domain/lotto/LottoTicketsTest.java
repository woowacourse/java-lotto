package lotto.domain.lotto;

import lotto.domain.Rank;
import lotto.domain.Result;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketsTest {
    @Test
    void 생성자_확인() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));

        assertThat(lottoRepository.createLottoTickets())
                .isEqualTo(new LottoTickets(
                        Arrays.asList(LottoGenerator.create(
                                new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))))));
    }

    @Test
    void 생성자_확인_null이_들어왔을_경우() {
        assertThatThrownBy(() -> new LottoTickets(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void 생성자_확인_비어있는_로또들이_들어왔을_경우() {
        assertThatThrownBy(() -> new LottoTickets(Collections.emptyList())).isInstanceOf(NullPointerException.class);
    }

    @Test
    void 로또번호와_당첨번호_비교할_때_오류_확인_당첨번호가_null인_경우() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
        LottoTickets lottoTickets = lottoRepository.createLottoTickets();

        assertThatThrownBy(() -> lottoTickets.match(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void 로또번호와_당첨번호_비교_확인() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 7)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 8)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 7, 8, 9)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 7, 8, 9, 10)));
        LottoTickets lottoTickets = lottoRepository.createLottoTickets();

        Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.getNumber(7));

        Result result = lottoTickets.match(winningLotto);

        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.get(Rank.MISS)).isEqualTo(1);
    }
}

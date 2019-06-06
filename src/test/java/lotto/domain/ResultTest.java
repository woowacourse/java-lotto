package lotto.domain;

import lotto.domain.lotto.*;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ResultTest {
    private LottoTickets lottoTickets;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 7)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 8)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 7, 8, 9)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 7, 8, 9, 10)));

        lottoTickets = lottoRepository.createLottoTickets();
        Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
        winningLotto = new WinningLotto(lotto, LottoNumber.getNumber(7));
    }

    @Test
    void 생성자_확인() {
        Map<Rank, Integer> lottoScore = new EnumMap<>(Rank.class);
        lottoScore.put(Rank.FIRST, 1);
        lottoScore.put(Rank.SECOND, 1);
        lottoScore.put(Rank.THIRD, 1);
        lottoScore.put(Rank.FOURTH, 0);
        lottoScore.put(Rank.FIFTH, 1);
        lottoScore.put(Rank.MISS, 1);

        assertThat(lottoTickets.match(winningLotto)).isEqualTo(new Result(lottoScore));
    }

    @Test
    void 생성자_확인_당첨결과_정보가_null인_경우() {
        assertThatThrownBy(() -> new Result(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void 생성자_확인_당첨결과_정보가_비어있는_경우() {
        assertThatThrownBy(() -> new Result(Collections.emptyMap()))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void Rank에_따른_당첨_개수_확인() {
        Result result = lottoTickets.match(winningLotto);
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 해당_Rank가_당첨되지_않은_경우() {
        Result result = lottoTickets.match(winningLotto);

        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
    }

    @Test
    void 수익률_계산() {
        Result result = lottoTickets.match(winningLotto);

        assertThat(result.calculateEarningsRate(new Payment("5000"))).isEqualTo(406_301);
    }

    @Test
    void 수익률_계산_오류확인_지불금액이_null인_경우() {
        Result result = lottoTickets.match(winningLotto);

        assertThatThrownBy(() -> result.calculateEarningsRate(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void 당첨로또_결과를_잘_생성하는지_확인() {
        Result result = lottoTickets.match(winningLotto);

        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.get(Rank.MISS)).isEqualTo(1);
    }
}

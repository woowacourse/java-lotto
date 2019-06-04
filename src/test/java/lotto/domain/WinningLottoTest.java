package lotto.domain;

import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class WinningLottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(6)));
    }

    @Test
    void 생성자_확인() {
        BonusNumber bonusNumber = new BonusNumber(7);
        assertThat(new WinningLotto(lotto, bonusNumber))
                .isExactlyInstanceOf((WinningLotto.class));
    }

    @Test
    void 생성자_확인_보너스_숫자와_이미_입력된_로또숫자가_중복되는지_확인() {
        BonusNumber bonusNumber = new BonusNumber(1);
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨로또_결과를_잘_생성하는지_확인() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 7)));
        lottoRepository.register(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 9)));

        LottoTickets lottoTickets = new LottoTickets(lottoRepository);

        Map<Rank, Integer> lottoScore = new HashMap<>();
        lottoScore.put(Rank.FIRST, 1);
        lottoScore.put(Rank.SECOND, 1);
        lottoScore.put(Rank.THIRD, 1);

        WinningLotto winningLotto = new WinningLotto(lotto, new BonusNumber(7));

        assertThat(new Result(lottoScore)).isEqualTo(winningLotto.produceResult(lottoTickets));
    }
}
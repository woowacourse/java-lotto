package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import lotto.creator.ManualLottoCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {
    LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        Number.valueOf(1),
                        Number.valueOf(2),
                        Number.valueOf(3),
                        Number.valueOf(4),
                        Number.valueOf(5),
                        Number.valueOf(6)
                )), Number.valueOf(7)
        );
        ManualLottoCreator lottoCreator = new ManualLottoCreator(Arrays.asList(
                "1, 2, 3, 4, 5, 6",
                "1, 2, 3, 4, 5, 7",
                "7, 8, 9, 10, 11, 12"
        ));
        List<Lotto> lottos = lottoCreator.create();

        lottoResult = new LottoResult(lottos, winningLotto);
    }

    @Test
    void 로또_결과_확인() {
        assertThat(lottoResult.getWinnerTypeValue(WinningType.FIRST)).isEqualTo(1);
        assertThat(lottoResult.getWinnerTypeValue(WinningType.SECOND)).isEqualTo(1);
        assertThat(lottoResult.getWinnerTypeValue(WinningType.MISS)).isEqualTo(1);
    }

    @Test
    void 우승상금_확인() {
        assertThat(lottoResult.getRewardAll()).isEqualTo(2030000000);
    }
}
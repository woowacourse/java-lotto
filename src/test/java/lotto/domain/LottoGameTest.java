package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {
    @Test
    void 로또_당첨_확인이_올바르게_진행되는지_확인() {
        LottoNumbers winningLottoNumbers = new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));
        WinningInformation winningInformation = new WinningInformation(winningLottoNumbers, LottoNumber.valueOf(10));
        LottoGame lottoGame = new LottoGame(winningInformation);

        LottoNumbers lottoFirst = new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));
        LottoNumbers lottoFifth = new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(11),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(15),
                LottoNumber.valueOf(16)));
        LottoNumbers lottoMiss = new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(12),
                LottoNumber.valueOf(13),
                LottoNumber.valueOf(14),
                LottoNumber.valueOf(15),
                LottoNumber.valueOf(16)));
        Lottos lottos = new Lottos();
        lottos.add(lottoFirst);
        lottos.add(lottoFifth);
        lottos.add(lottoMiss);

        LottoResult lottoResult = new LottoResult();
        lottoResult.add(Rank.FIRST);
        lottoResult.add(Rank.FIFTH);
        lottoResult.add(Rank.MISS);

        assertThat(lottoGame.play(lottos)).isEqualTo(lottoResult);
    }
}

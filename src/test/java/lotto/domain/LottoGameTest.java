package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {
    @Test
    void 로또_당첨_확인이_올바르게_진행되는지_확인() {
        LottoNumbers winningLottoNumbers = new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)));
        WinningInformation winningInformation = new WinningInformation(winningLottoNumbers, new LottoNumber(10));
        LottoGame lottoGame = new LottoGame(winningInformation);

        LottoNumbers lottoFirst = new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)));
        LottoNumbers lottoFifth = new LottoNumbers(Arrays.asList(
                new LottoNumber(11),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(15),
                new LottoNumber(16)));
        LottoNumbers lottoMiss = new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(12),
                new LottoNumber(13),
                new LottoNumber(14),
                new LottoNumber(15),
                new LottoNumber(16)));
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

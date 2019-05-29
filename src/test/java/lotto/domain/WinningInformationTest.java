package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningInformationTest {
    @Test
    void 일등_당첨() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6))));

        WinningInformation winningInformation = new WinningInformation(new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6))));

        assertThat(winningInformation.match(lotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    void 당첨_실패() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6))));

        WinningInformation winningInformation = new WinningInformation(new LottoNumbers(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(21),
                new LottoNumber(31),
                new LottoNumber(14),
                new LottoNumber(15),
                new LottoNumber(16))));

        assertThat(winningInformation.match(lotto)).isEqualTo(Rank.MISS);
    }
}

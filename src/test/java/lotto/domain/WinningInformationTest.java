package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningInformationTest {
    @Test
    void 일등_당첨() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))));

        WinningInformation winningInformation = new WinningInformation(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))),
                LottoNumber.valueOf(10));

        assertThat(winningInformation.match(lotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    void 이등_당첨() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))));

        WinningInformation winningInformation = new WinningInformation(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(10))),
                LottoNumber.valueOf(6));

        assertThat(winningInformation.match(lotto)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 삼등_당첨() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))));

        WinningInformation winningInformation = new WinningInformation(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(10))),
                LottoNumber.valueOf(36));

        assertThat(winningInformation.match(lotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    void 사등_당첨() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))));

        WinningInformation winningInformation = new WinningInformation(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(11),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(31),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))),
                LottoNumber.valueOf(10));

        assertThat(winningInformation.match(lotto)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 오등_당첨() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))));

        WinningInformation winningInformation = new WinningInformation(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(14),
                LottoNumber.valueOf(15),
                LottoNumber.valueOf(16))),
                LottoNumber.valueOf(10));

        assertThat(winningInformation.match(lotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 당첨_실패() {
        Lotto lotto = new Lotto(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(6))));

        WinningInformation winningInformation = new WinningInformation(new LottoNumbers(Arrays.asList(
                LottoNumber.valueOf(11),
                LottoNumber.valueOf(12),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(41),
                LottoNumber.valueOf(15),
                LottoNumber.valueOf(26))),
                LottoNumber.valueOf(10));

        assertThat(winningInformation.match(lotto)).isEqualTo(Rank.MISS);
    }
}

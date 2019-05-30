package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class WinningLottoTest {
    List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = Arrays.asList(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9),
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12));
    }

    @Test
    void 일치하는_번호_개수가_2개인_경우_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        lottoNumbers.set(0, new LottoNumber(1));
        lottoNumbers.set(1, new LottoNumber(2));
        assertThat(winningLotto.matchNumbersOfLotto(lottoNumbers)).isEqualTo(2);
    }

    @Test
    void 일치하는_번호_개수가_0개인_경우_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(winningLotto.matchNumbersOfLotto(lottoNumbers)).isEqualTo(0);
    }

    @Test
    void 잘못된_당첨번호가_들어온_경우_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new WinningLotto(Arrays.asList(1, 2, 3, 4, 5));
        }).withMessage("당첨 번호의 개수는 6개 입니다.");
    }
}

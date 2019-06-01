package lotto.domain.lottoTicket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class WinningLottoTest {
    List<Integer> lottoNumbers;
    int bonus;

    @BeforeEach
    void setUp() {
        lottoNumbers = Arrays.asList(7, 8, 9, 10, 11, 12);
        bonus = 13;
    }

    @Test
    void 일치하는_번호_개수가_2개인_경우_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), bonus);
        lottoNumbers.set(0, 1);
        lottoNumbers.set(1, 2);
        Lotto lotto = new ManualLotto(lottoNumbers);
        assertThat(winningLotto.matchLottoNumbers(lotto)).isEqualTo(2);
    }

    @Test
    void 일치하는_번호_개수가_0개인_경우_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), bonus);
        Lotto lotto = new ManualLotto(lottoNumbers);
        assertThat(winningLotto.matchLottoNumbers(lotto)).isEqualTo(0);
    }

    @Test
    void 잘못된_당첨번호가_들어온_경우_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new WinningLotto(Arrays.asList(1, 2, 3, 4, 5), bonus);
        }).withMessage("로또 번호의 개수는 6개 입니다.");
    }

    @Test
    void 보너스_번호가_당첨번호에_포함되어_있는_경우_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new WinningLotto(lottoNumbers, 7);
        }).withMessage("당첨 번호와 중복된 번호 입니다.");
    }
}

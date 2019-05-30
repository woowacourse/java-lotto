package lotto.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
    private Set<LottoNumber> numbers;
    private LottoNumber bonusNumber;

    @BeforeEach
    public void setUp() {
        numbers = new HashSet<>(Arrays.asList(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5),
                LottoNumber.get(6)
        ));
        bonusNumber = LottoNumber.get(7);
    }

    @Test
    public void 당첨_번호_로또_생성_테스트() {
        assertThat(new WinningLotto(new Lotto(numbers), bonusNumber)).isEqualTo(new WinningLotto(new Lotto(numbers), bonusNumber));
    }

    @Test
    public void 로또_당첨_결과_계산_테스트() {
        assertThat(new WinningLotto(new Lotto(numbers), bonusNumber).calculateRank(new Lotto(numbers)))
                .isEqualTo(LottoRank.FIRST);
    }

    @AfterEach
    public void tearDown() {
        numbers = null;
    }
}

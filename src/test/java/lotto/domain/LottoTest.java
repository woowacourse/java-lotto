package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(new LottoNumber("1"), new LottoNumber("3"),
            new LottoNumber("4"), new LottoNumber("5"), new LottoNumber("6"), new LottoNumber("7")));
    Lotto lotto = new Lotto(lottoNumbers);

    @DisplayName("로또에 있는 번호가 왔을 때 테스트")
    @Test
    void testContainNumber() {
        LottoNumber number = new LottoNumber("1");
        assertTrue(lotto.containNumber(number));
    }

    @DisplayName("로또에 없는 번호가 왔을 때 테스트")
    @Test
    void testNotContainNumber() {
        LottoNumber number = new LottoNumber("2");
        assertFalse(lotto.containNumber(number));
    }

    @DisplayName("다른 로또와 번호가 몇 개 일치하는지 테스")
    @Test
    void testCountOfMatchNumber() {
        LottoNumbers otherLottoNumbers = new LottoNumbers(Arrays.asList(new LottoNumber("1"), new LottoNumber("2"), new LottoNumber("3"),
                new LottoNumber("4"), new LottoNumber("5"), new LottoNumber("6")));
        Lotto otherLotto = new Lotto(otherLottoNumbers);

        assertThat(otherLotto.countOfMatchNumber(lotto)).isEqualTo(5);
    }
}

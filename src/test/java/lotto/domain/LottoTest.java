package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {

    Lotto lotto = new Lotto(Arrays.asList(
            new LottoNumber(1), new LottoNumber(3),
            new LottoNumber(4), new LottoNumber(5),
            new LottoNumber(6), new LottoNumber(7)
    ));


    @DisplayName("로또 생성자 테스트")
    @Test
    void createLotto() {
        Lotto newLotto = new Lotto(Arrays.asList(
                new LottoNumber(1), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5),
                new LottoNumber(6), new LottoNumber(7)
        ));

        assertThat(newLotto).isEqualTo(lotto);
    }

    @DisplayName("올바르지 않은 로또 번호들이 들어왔을 경우 예외를 출력하는지 테스트")
    @Test
    void testValidateLotto() {
        List<LottoNumber> wrongLotto1 = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5)
        );

        List<LottoNumber> wrongLotto2 = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(5)
        );


        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(wrongLotto1));
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(wrongLotto2));
    }

    @DisplayName("로또가 번호를 포함하는지 테스트")
    @Test
    void testContainNumber() {
        LottoNumber number = new LottoNumber(1);

        assertTrue(lotto.containNumber(number));
    }

    @DisplayName("로또가 번호를 포함하지 않는지 테스트")
    @Test
    void testNotContainNumber() {
        LottoNumber number = new LottoNumber(2);

        assertFalse(lotto.containNumber(number));
    }

    @DisplayName("다른 로또와 일치하는 번호의 개수를 구하는 테스트")
    @Test
    void testCountOfMatchNumber() {
        Lotto otherLotto = new Lotto(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));

        assertThat(otherLotto.countOfMatchNumber(lotto)).isEqualTo(5);
    }
}

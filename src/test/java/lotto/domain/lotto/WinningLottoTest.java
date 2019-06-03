package lotto.domain.lotto;

import lotto.domain.InvalidLottoException;
import lotto.domain.InvalidLottoNumberException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {

    private List<LottoNumber> lotto;

    @BeforeEach
    void setUp() {
        lotto = new ArrayList<>();
        lotto.add(new LottoNumber(1));
        lotto.add(new LottoNumber(2));
        lotto.add(new LottoNumber(3));
        lotto.add(new LottoNumber(4));
        lotto.add(new LottoNumber(5));
        lotto.add(new LottoNumber(6));
    }

    @Test
    void 당첨번호_로또_객체에_잘들어가는지_테스트() {
        String[] numbers = {"1", "2", "3", "4", "5", "6"};
        WinningLotto winningLotto = new WinningLotto(numbers);
        assertThat(winningLotto.getWinningLotto()).isEqualTo(new Lotto(lotto));
    }

    @Test
    void 당첨번호가_6개_아닐때() {
        String[] numbers = {"1", "2", "3", "4", "5"};
        assertThrows(InvalidLottoException.class, () -> {
            new WinningLotto(numbers);
        });
    }

    @Test
    void 당첨번호가_중복일때() {
        String[] numbers = {"1", "2", "3", "4", "5", "4"};
        assertThrows(InvalidLottoException.class, () -> {
            new WinningLotto(numbers);
        });
    }

    @Test
    void 당첨번호에_문자를_입력할때() {
        String[] numbers = {"a", "2", "3", "4", "5", "6"};
        assertThrows(InputMismatchException.class, () -> {
            new WinningLotto(numbers);
        });
    }

    @Test
    void 당첨번호가_1에서_45의_정수가_아닐때() {
        String[] numbers = {"46", "2", "3", "4", "5", "6"};
        assertThrows(InvalidLottoNumberException.class, () -> {
            new WinningLotto(numbers);
        });
    }

    @AfterEach
    void tearDown() {
        lotto = null;
    }
}

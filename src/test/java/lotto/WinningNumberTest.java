package lotto;

import domain.Lotto;
import domain.LottoNumber;
import domain.WinningNumber;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {
    @Test
    void 입력된_번호가_문자인지_확인(){
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "4", "d", "6"};
            new WinningNumber(numbers, "30");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("로또 넘버는 숫자여야 합니다. 입력한 문자 : %s", "d"));
    }

    @Test
    void 당첨_로또_번호가_로또의_범위_안_인지_확인(){
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "4", "55", "6"};
            new WinningNumber(numbers, "30");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자 범위를 넘어섰습니다.");
    }

    @Test
    void 당첨_로또_번호의_개수가_6개인지_확인(){
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "4", "6"};
            new WinningNumber(numbers, "30");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 번호는 6개의 숫자로 이루어져 있어야 합니다.");
    }

    @Test
    void 보너스_번호_유효성검증(){
        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "5", "4", "6"};
            String bonusNumber = "가";
            new WinningNumber(numbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("로또 넘버는 숫자여야 합니다. 입력한 문자 : %s", "가"));

        assertThatThrownBy(() -> {
            String[] numbers = {"1", "2", "3", "5", "4", "6"};
            String bonusNumber = "50";
            new WinningNumber(numbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자 범위를 넘어섰습니다.");
    }

    @Test
    void 당첨번호_갯수_계산_테스트(){
        String[] winningNumbers = {"1", "2", "3", "5", "4", "6"};
        String bonusNumber = "7";
        WinningNumber winningNumber = new WinningNumber(winningNumbers, bonusNumber);

        Set<LottoNumber> myLotto = new HashSet<>();
        myLotto.add(new LottoNumber(1));
        myLotto.add(new LottoNumber(2));
        myLotto.add(new LottoNumber(3));
        myLotto.add(new LottoNumber(4));
        myLotto.add(new LottoNumber(5));
        myLotto.add(new LottoNumber(7));
        Lotto myLottoNumbers = new Lotto(myLotto);

        int winningMatchCount = winningNumber.countWinningMatch(myLottoNumbers);
        boolean isBonusMatch = winningNumber.isBonusMatch(myLottoNumbers);
        assertThat(winningMatchCount).isEqualTo(5);
        assertThat(isBonusMatch).isTrue();
    }
}

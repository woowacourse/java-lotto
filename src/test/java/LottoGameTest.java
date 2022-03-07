import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoGame;
import domain.AutoLottoGenerator;
import domain.LottoNumber;
import domain.WinningChecker;
import domain.WinningNumbers;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {


    @Test
    @DisplayName("입력받은 금액 로또로 바꿔주는 기능 테스트")
    void buyLottoTest() {
        LottoGame lottoGame = new LottoGame(new AutoLottoGenerator(14));
        assertThat(lottoGame.getLottos().getSize()).isEqualTo(14);
    }


    @Test
    @DisplayName("수익률 계산하는 기능 테스트")
    void calculateYield() {
        Lotto lotto1 = new Lotto(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).collect(
            Collectors.toList()));
        Lotto lotto2 = new Lotto(Stream.of(4, 5, 6, 7, 8, 9).map(LottoNumber::new).collect(
            Collectors.toList()));
        Lotto lotto3 = new Lotto(Stream.of(11, 12, 13, 14, 15, 16).map(LottoNumber::new).collect(
            Collectors.toList()));

        LottoGame lottoGame = new LottoGame(
            () -> List.of(lotto1, lotto2, lotto3));

        WinningNumbers winningNumbers = new WinningNumbers(
            Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).collect(
                Collectors.toList()), new LottoNumber(9));

        WinningChecker winningChecker = lottoGame.makeResult(winningNumbers);

        double yield = lottoGame.getYield(winningChecker);

        assertThat(yield).isEqualTo((double) 2000005000 / (double) 3000);
    }

}

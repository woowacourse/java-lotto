import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    @Test
    @DisplayName("입력받은 금액 로또로 바꿔주는 기능 테스트")
    void buyLottoTest() {
        LottoGame lottoGame = new LottoGame();

        assertThat(lottoGame.buyLotto(14000).size()).isEqualTo(14);
    }

    @Test
    @DisplayName("수익률 계산하는 기능 테스트")
    void calculateYield() {


        Lotto lotto1 = new Lotto(Arrays.asList(1,2,3,4,5,6));
        Lotto lotto2 = new Lotto(Arrays.asList(4,5,6,7,8,9));
        Lotto lotto3 = new Lotto(Arrays.asList(11,12,13,14,15,16));


        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2, lotto3));
        LottoGame lottoGame = new LottoGame(Arrays.asList(lotto1, lotto2, lotto3));

        List<Integer> winningNumbers = Arrays.asList(1,2,3,4,5,6);
        int bonusNumber = 9;
        double yield = lottoGame.getYield(winningNumbers, bonusNumber);

        assertThat(yield).isEqualTo(2000005000/3000);

//        3개 일치 (5000원)- 1개
//        4개 일치 (50000원)- 0개
//        5개 일치 (1500000원)- 0개
//        5개 일치, 보너스 볼 일치(30000000원) - 0개
//        6개 일치 (2000000000원)- 0개
//        총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)

    }
}

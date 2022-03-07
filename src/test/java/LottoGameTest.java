//import static org.assertj.core.api.Assertions.assertThat;
//
//import domain.Lotto;
//import domain.LottoGame;
//import domain.LottoGenerator;
//import domain.LottoNumber;
//import domain.Lottos;
//import domain.WinningChecker;
//import domain.WinningNumbers;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//public class LottoGameTest {
//
//    @Test
//    @DisplayName("수익률 계산하는 기능 테스트")
//    void calculateYield() {
//
//        List<List<Integer>> lottoList = new ArrayList<>();
//
//        lottoList.add(List.of(1,2,3,4,5,6));
//        lottoList.add(List.of(4, 5, 6, 7, 8, 9));
//        lottoList.add(List.of(11, 12, 13, 14, 15, 16));
//
//
//        LottoGenerator lottoGenerator = new LottoGenerator(lottoList, 0);
//
//        LottoGame lottoGame = new LottoGame(lottoGenerator);
//
//        WinningNumbers winningNumbers = new WinningNumbers(
//            Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).collect(
//                Collectors.toList()), new LottoNumber(9));
//
//        WinningChecker winningChecker = new WinningChecker(new Lottos(lottoList), winningNumbers);
//
//        double yield = lottoGame.getYield(winningChecker);
//
//        assertThat(yield).isEqualTo((double) 2000005000 / (double) 3000);
//    }
//
//}

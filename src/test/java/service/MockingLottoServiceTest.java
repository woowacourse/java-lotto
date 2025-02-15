package service;

import static org.assertj.core.api.Assertions.*;

import domain.Lotto;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import repository.BonusNumberRepository;
import repository.LottoRepository;
import repository.WinningNumberRepository;

public class MockingLottoServiceTest {

  private final LottoRepository lottoRepository = new LottoRepository();
  private final WinningNumberRepository winningNumberRepository = new WinningNumberRepository();
  private final BonusNumberRepository bonusNumberRepository = new BonusNumberRepository();

  private final MockingLottoService mockingLottoService = new MockingLottoService(
      lottoRepository,
      winningNumberRepository,
      bonusNumberRepository
  );

  @ParameterizedTest
  @DisplayName("당첨_통계_계산_및_출력_테스트")
  @MethodSource("calculateWinningResult")
  public void 당첨_통계_계산_및_출력_테스트(List<List<Integer>> testLottoNumbers) {
    String winningNumber = "1, 2, 3, 4, 5, 6";
    String bonusNumber = "7";

    System.out.println(testLottoNumbers);

    List<Lotto> lottos = testLottoNumbers.stream().map(Lotto::new).toList();

    String result = mockingLottoService.winningCalculate(lottos, winningNumber, bonusNumber);

    assertThat(result)
        .isEqualTo("""
            
            당첨 통계
            --------
            3개 일치 (5000원)- 1개
            4개 일치 (50000원)- 0개
            5개 일치 (1500000원)- 0개
            5개 일치, 보너스 볼 일치 (30000000원) - 0개
            6개 일치 (2000000000원)- 0개
            총 수익률은 0.357143입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)"""
        );
  }

  private static Stream<Arguments> calculateWinningResult() {
    return Stream.of(
        Arguments.arguments(
            List.of(
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(23, 25, 33, 36, 39, 41),
                List.of(1, 3, 5, 14, 22, 45),
                List.of(5, 9, 38, 41, 43, 44),
                List.of(2, 8, 9, 18, 19, 21),
                List.of(13, 14, 18, 21, 23, 35),
                List.of(17, 21, 29, 37, 42, 45),
                List.of(3, 8, 27, 30, 35, 44)
            )
        )
    );
  }


}

package view;

import static domain.Rank.FIFTH;
import static domain.Rank.FIRST;
import static domain.Rank.FOURTH;
import static domain.Rank.SECOND;
import static domain.Rank.THIRD;

import dto.LottoResultDto;

public class ResultView {
    public static void printResult(LottoResultDto resultDto) {
        System.out.print(System.lineSeparator());
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원) - " + resultDto.getWinningCountByRank(FIFTH) + "개");
        System.out.println("4개 일치 (50000원) - " + resultDto.getWinningCountByRank(FOURTH) + "개");
        System.out.println("5개 일치 (1500000원) - " + resultDto.getWinningCountByRank(THIRD) + "개");
        System.out.println("5개 일치 보너스 볼 일치(30000000원) - " + resultDto.getWinningCountByRank(SECOND) + "개");
        System.out.println("6개 일치 (2000000000원) - " + resultDto.getWinningCountByRank(FIRST) + "개");
        System.out.println(
                "총 수익률은 " + resultDto.getRateOfReturn() + "입니다.(기준이 1이기 때문에 결과적으로 " +
                        getWinOrLoseByLottoResultDto(resultDto) + "라는 의미임)");
    }

    private static String getWinOrLoseByLottoResultDto(LottoResultDto resultDto) {
        if (resultDto.getRateOfReturn() > 1) {
            return "이익";
        }

        return "손해";
    }
}


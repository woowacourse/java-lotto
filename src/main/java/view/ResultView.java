package view;

import domain.Rank;
import dto.LottoResultDto;

public class ResultView {
    public static void printResult(LottoResultDto lottoResultDto) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원) - " + lottoResultDto.getWinningCountByRank(Rank.FIFTH).getCount() + "개");
        System.out.println("4개 일치 (50000원) - " + lottoResultDto.getWinningCountByRank(Rank.FOURTH).getCount() + "개");
        System.out.println("5개 일치 (1500000원) - " + lottoResultDto.getWinningCountByRank(Rank.THIRD).getCount() + "개");
        System.out.println(
                "5개 일치, 보너스 볼 일치(30000000원) - " + lottoResultDto.getWinningCountByRank(Rank.SECOND).getCount() + "개");
        System.out.println(
                "6개 일치 (2000000000원) - " + lottoResultDto.getWinningCountByRank(Rank.FIRST).getCount() + "개");
        System.out.println(
                "총 수익률은 " + lottoResultDto.getProfitRatio() + "입니다.(기준이 1이기 때문에 결과적으로 " + getWinOrLoseByLottoResultDto(
                        lottoResultDto) + "라는 의미임)");
    }

    private static String getWinOrLoseByLottoResultDto(LottoResultDto lottoResultDto) {
        if (lottoResultDto.getProfitRatio() > 1) {
            return "이익";
        }

        return "손해";
    }
}


package view;

import dto.LottoDto;
import dto.LottosDto;
import dto.RankDto;
import dto.RanksDto;

public class OutputView {

    private static final String PURCHASE_QUANTITY_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final int SECOND_RANK = 2;
    private static final String RESULT_MESSAGE = "당첨 통계";
    private static final String DELIMITER = "---------";
    private static final String RESULT_FORMAT = "%d개 일치%s (%d원)- %d개";
    private static final String BONUS_FORMAT = ", 보너스 볼 일치";
    private static final String EMPTY_FORMAT = "";
    private static final String INCOME_RATE_FORMAT = "총 수익률은 %.2f입니다.";

    public static void printPurchasedLotto(LottosDto lottosDto) {
        System.out.println(String.format(PURCHASE_QUANTITY_FORMAT, lottosDto.getManualQuantity(), lottosDto.getAutoQuantity()));
        for (LottoDto lottoDto : lottosDto.getLottoDtos()) {
            printLottoNumbers(lottoDto);
        }
        System.out.print(System.lineSeparator());
    }

    public static void printLottoNumbers(LottoDto lottoDto) {
        System.out.println(lottoDto.getLottoNumber());
    }

    public static void printResult(RanksDto ranksDto) {
        System.out.print(System.lineSeparator());
        System.out.println(RESULT_MESSAGE);
        System.out.println(DELIMITER);
        for (RankDto rankDto : ranksDto.getRankDtos()) {
            printByRank(rankDto);
        }
        printIncomeRate(ranksDto);
    }

    private static void printByRank(RankDto rankDto) {
        int criteria = rankDto.getCriteria();
        int reward = rankDto.getReward();
        int hitCount = rankDto.getHitCount();

        if (rankDto.getRankNumber() == SECOND_RANK) {
            System.out.println(String.format(RESULT_FORMAT, criteria, BONUS_FORMAT, reward, hitCount));
            return;
        }
        System.out.println(String.format(RESULT_FORMAT, criteria, EMPTY_FORMAT, reward, hitCount));
    }

    private static void printIncomeRate(RanksDto ranksdto) {
        System.out.println(String.format(INCOME_RATE_FORMAT, ranksdto.getIncomeRate()));
    }
}

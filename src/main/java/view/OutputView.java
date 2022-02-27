package view;

import dto.LottoDto;
import dto.LottosDto;
import dto.RankDto;
import dto.RanksDto;

public class OutputView {

    public static final String PURCHASE_QUANTITY_FORMAT = "%s개를 구매했습니다.%n";
    private static final int SECOND_RANK = 2;
    private static final String RESULT_MESSAGE = "당첨 통계";
    private static final String DELIMITER = "---------";
    private static final String RESULT_FORMAT = "%d개 일치%s (%d원)- %d개%n";
    private static final String BONUS_FORMAT = ", 보너스 볼 일치";
    private static final String EMPTY_FORMAT = "";
    private static final String INCOME_RATE_FORMAT = "총 수익률은 %.2f입니다.%n";

    private static final OutputView outputView = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return outputView;
    }

    public void printPurchasedLotto(LottosDto lottosDto) {
        System.out.printf((PURCHASE_QUANTITY_FORMAT), lottosDto.getQuantity());
        for (LottoDto lottoDto : lottosDto.getLottoDtos()) {
            printLottoNumbers(lottoDto);
        }
        System.out.print(System.lineSeparator());
    }

    public void printLottoNumbers(LottoDto lottoDto) {
        System.out.println(lottoDto.getLottoNumber());
    }

    public void printResult(RanksDto ranksDto) {
        System.out.print(System.lineSeparator());
        System.out.println(RESULT_MESSAGE);
        System.out.println(DELIMITER);
        for (RankDto rankDto : ranksDto.getRankDtos()) {
            printByRank(rankDto, rankDto.getCriteria(), rankDto.getReward(), rankDto.getHitCount());
        }
        printIncomeRate(ranksDto);
    }

    private void printByRank(RankDto rankDto, int criteria, int reward, int hitCount) {
        if (rankDto.getRankNumber() == SECOND_RANK) {
            System.out.printf((RESULT_FORMAT), criteria, BONUS_FORMAT, reward, hitCount);
            return;
        }
        System.out.printf((RESULT_FORMAT), criteria, EMPTY_FORMAT, reward, hitCount);
    }

    private void printIncomeRate(RanksDto ranksdto) {
        System.out.printf((INCOME_RATE_FORMAT), ranksdto.getIncomeRate());
    }
}

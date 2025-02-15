package lotto.view;

import static lotto.common.Constants.LINE_SEPARATOR;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.dto.LottoGroupDto;
import lotto.dto.ProfitDto;

public class OutputView {

    private OutputView() {
    }

    public static void printError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void printLottoGroup(LottoGroupDto lottoGroupDto) {
        System.out.printf("%d개를 구매했습니다." + LINE_SEPARATOR, lottoGroupDto.lottoGroup().size());

        for (Lotto lotto : lottoGroupDto.lottoGroup()) {
            System.out.println(lotto.toString());
        }

        System.out.println();
    }

    public static void printResult(ProfitDto profitDto, String profitRate) {
        printNoticeResultMessage();
        printMatchCounts(profitDto);
        printProfitRate(profitRate);
    }

    private static void printNoticeResultMessage() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void printMatchCounts(ProfitDto profitDto) {
        profitDto.rankCounts()
                .forEach((rank, matchCount) -> {
                    if (rank.equals(Rank.NO_REWARD)) {
                        return;
                    }
                    System.out.printf("%s%s개" + LINE_SEPARATOR, rank.getMessage(), matchCount);
                });
    }

    private static void printProfitRate(String profitRate) {
        System.out.printf(LINE_SEPARATOR + "총 수익률은 %s입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", profitRate);
    }
}


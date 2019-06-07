package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.WinningAggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleOutputView {
    public static void printLottos(List<Lotto> lottos, int numOfManuals) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.",
            numOfManuals, lottos.size() - numOfManuals));
        lottos.forEach(l -> System.out.println(getLottoString(l)));
    }

    private static String getLottoString(Lotto l) {
        List<LottoNumber> nums = new ArrayList<>();
        l.forEachNums(nums::add);
        return "[" + nums.stream()
            .map(LottoNumber::toInt)
            .map(String::valueOf)
            .collect(Collectors.joining(", ")) + "]";
    }

    public static void printResultTitle() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printEarningRate(double earningRate) {
        System.out.println(String.format("총 수익률은 %.0f%%입니다.", earningRate * 100));
    }

    public static void printAggregationItem(LottoResult resultToPrint, int matchCount) {
        System.out.println(String.format("%d개 일치%s(%d원)- %d개",
            resultToPrint.getMatchCount(),
            resultToPrint.isUseBonus() ? ", 보너스 볼 일치" : " ",
            resultToPrint.getPrizeMoney(),
            matchCount));
    }

    public static void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}

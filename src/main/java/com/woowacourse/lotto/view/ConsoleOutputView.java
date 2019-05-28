package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.WinningAggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleOutputView {
    public static void printLottos(List<Lotto> lottos) {
        System.out.println(String.format("%d장을 구매했습니다.", lottos.size()));
        lottos.forEach(l -> System.out.println(getLottoString(l)));
    }

    private static String getLottoString(Lotto l) {
        List<Integer> nums = new ArrayList<>();
        l.forEachNums(nums::add);
        return "[" + nums.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "]";
    }

    public static void printAggregation(WinningAggregator aggregator) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        printAggregationItem(aggregator, LottoResult.FIFTH);
        printAggregationItem(aggregator, LottoResult.FOURTH);
        printAggregationItem(aggregator, LottoResult.THIRD);
        printAggregationItem(aggregator, LottoResult.SECOND);
        printAggregationItem(aggregator, LottoResult.FIRST);
        System.out.println(String.format("총 수익률은 %.0f%%입니다.", aggregator.calculateEarningRate(Lotto.UNIT_PRICE) * 100));
    }

    private static void printAggregationItem(WinningAggregator aggregator, LottoResult resultToPrint) {
        System.out.println(String.format("%d개 일치%s(%d원)- %d개",
            resultToPrint.getMatchCount(),
            resultToPrint.isUseBonus() ? ", 보너스 볼 일치" : " ",
            resultToPrint.getPrizeMoney(),
            aggregator.getResultCount(resultToPrint)));
    }
}

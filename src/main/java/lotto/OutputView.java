package lotto;

import java.text.MessageFormat;
import java.util.List;

public class OutputView {

    public static void printRanks(List<Rank> ranks) {

    }

    public static void printRate(Rate rate) {
        System.out.println(MessageFormat.format("총 수익률은 {0}입니다.", rate.getRate().toString()));
    }
}

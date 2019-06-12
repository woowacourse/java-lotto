package lotto.view;

import lotto.model.Lotto;
import lotto.model.Rank;
import lotto.model.WinStat;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConsoleOutput {
    private final static String SPACE;
    private final static String COMMA;
    private final static String TICKET;
    private final static String AUTO;
    private final static String MANUAL;
    private final static String PURCHASED;
    private static final String EMPTY;
    private static final String NEW_LINE;
    private static final String STATICS_TITLE;
    private static final String HORIZONTAL_BAR;
    private static final String ITEM_DELIMITER;
    private static final String QUANTITY;
    private static final String TOTAL_PROFIT_RATE;
    private static final String SENTENCE_LAST;
    private static final String HEADER;
    private static final DecimalFormat PERCENT;

    static {
        EMPTY = "";
        SPACE = " ";
        COMMA = ",";
        NEW_LINE = "\n";
        TOTAL_PROFIT_RATE = "총 수익률은 ";
        QUANTITY = "개";
        SENTENCE_LAST = "입니다.";
        ITEM_DELIMITER = " - ";
        STATICS_TITLE = "당첨 통계";
        HORIZONTAL_BAR = "----------";
        AUTO = "자동으로";
        MANUAL = "수동으로";
        TICKET = "장";
        PURCHASED = "을 구매하였습니다.";
        HEADER = STATICS_TITLE + NEW_LINE + HORIZONTAL_BAR + NEW_LINE;
        PERCENT = new DecimalFormat("#.#%"); // 소수점 아래 1자리까지 퍼센트
    }

    private static StringBuilder amountCount(final String autoOrManual, final int ticketCount) {
        final StringBuilder sb = new StringBuilder();
        sb.append(autoOrManual);
        sb.append(SPACE);
        sb.append(ticketCount);
        sb.append(TICKET);
        return sb;
    }

    public static void buyCount(final int manualAmount, final int autoAmount) {
        final StringBuilder manual = amountCount(MANUAL, manualAmount);
        final StringBuilder auto = amountCount(AUTO, autoAmount);
        final StringBuilder temp = new StringBuilder(manual);
        temp.append(COMMA);
        temp.append(SPACE);
        temp.append(auto);
        temp.append(PURCHASED);
        System.out.println(temp);
    }

    public static void lottoList(final List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    private static String getProfitRateMessage(WinStat stat) {
        return TOTAL_PROFIT_RATE + PERCENT.format(stat.getProfitRate()) + SENTENCE_LAST;
    }

    public static void statString(WinStat stat) {
        final StringBuilder result = new StringBuilder(HEADER);
        final Iterator<Map.Entry<Rank, Integer>> iterator = stat.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Rank, Integer> entry = iterator.next();
            Rank key = entry.getKey();
            int value = entry.getValue();
            String temp
                    = key.equals(Rank.MISS)
                    ? EMPTY // 낙첨은 당첨 통계에서 출력하지 않도록 함
                    : key.getRankDescription() + ITEM_DELIMITER + value + QUANTITY + NEW_LINE;
            result.append(temp);
        }
        result.append(getProfitRateMessage(stat));
        System.out.println(result);
    }
}

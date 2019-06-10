package lotto.view;

import lotto.model.Lotto;

import java.util.List;

public class Output {
    private final static String SPACE;
    private final static String COMMA;
    private final static String TICKET;
    private final static String AUTO;
    private final static String MANUAL;
    private final static String PURCHASED;

    static {
        SPACE = " ";
        COMMA = ",";
        TICKET = "장";
        AUTO = "자동으로";
        MANUAL = "수동으로";
        PURCHASED = "을 구매하였습니다.";
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
}

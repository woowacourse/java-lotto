package lotto.view;

import lotto.domain.LottoSeller;

public class OutputView {
    private static final String OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE = "장을 구매했습니다.";
    private static final String OUTPUT_CHANGE_MESSAGE_FRONT = " 잔돈은 ";
    private static final String OUTPUT_CHANGE_MESSAGE_BACK = "원입니다.";

    public static void showNumOfTicketsFrom(LottoSeller lottoSeller) {
        System.out.println(makeNumOfTicketsMessage(lottoSeller));
    }

    private static String makeNumOfTicketsMessage(LottoSeller lottoSeller) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lottoSeller.getNumOfLotto())
                .append(OUTPUT_NUM_OF_PURCHASED_TICKETS_MESSAGE);

        if (lottoSeller.hasChange()) {
            stringBuilder.append(OUTPUT_CHANGE_MESSAGE_FRONT)
                    .append(lottoSeller.getChange())
                    .append(OUTPUT_CHANGE_MESSAGE_BACK);
        }
        return stringBuilder.toString();
    }
}

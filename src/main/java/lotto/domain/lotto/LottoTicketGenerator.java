package lotto.domain.lotto;
import java.util.ArrayList;

import lotto.view.ErrorMessages;

public class LottoTicketGenerator {
    protected static final int PRICE_EACH_LOTTO = 1000;
    private static final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();

    public static LottoTicket createLottoTicket(int money) {
        int numberOfLottoLine = getNumberOfLottoLine(money);

        ArrayList<LottoLine> lottoLineList = new ArrayList<LottoLine>();
        for (int i = 0; i < numberOfLottoLine; i++) {
            lottoLineList.add(new LottoLine(randomLottoGenerator.createLottoLine()));
        }
        return new LottoTicket(lottoLineList);
    }

    private static int getNumberOfLottoLine(int money) {
        int numberOfLottoLine = money / PRICE_EACH_LOTTO;
        if (numberOfLottoLine <= 0) {
            throw new IllegalArgumentException(ErrorMessages.ERROR_LOTTO_MONEY_NOT_ENOUGH.getMessage());
        }
        return numberOfLottoLine;
    }
}

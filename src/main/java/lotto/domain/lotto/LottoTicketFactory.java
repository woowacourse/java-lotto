package lotto.domain.lotto;

import java.util.ArrayList;

public class LottoTicketFactory {
    private static final int PRICE_EACH_LOTTO = 1000;
    private static final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();

    public static LottoTicket createLottoTicket(int money) {
        int numberOfLottoLine = money / PRICE_EACH_LOTTO;

        if (numberOfLottoLine <= 0) {
            throw new IllegalArgumentException("[ERROR] 돈이 부족합니다.");
        }

        ArrayList<LottoLine> lottoLineList = new ArrayList<LottoLine>();
        for (int i = 0; i < numberOfLottoLine; i++) {
            lottoLineList.add(new LottoLine(randomLottoGenerator.createLottoLine()));
        }
        return new LottoTicket(lottoLineList);
    }
}

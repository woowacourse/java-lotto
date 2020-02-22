package domain.lottonumber;

import domain.lottonumber.generator.LottoGenerator;

public class LottoTicketFactory {
    private LottoTicketFactory() {
        throw new AssertionError();
    }

    public static LottoTicket createLottoNumbers(LottoGenerator lottoGenerator) {
        return new LottoTicket(lottoGenerator.create());
    }
}

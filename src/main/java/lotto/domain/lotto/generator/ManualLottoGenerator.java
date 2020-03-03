package lotto.domain.lotto.generator;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.purchase_info.PurchaseInfo;
import lotto.parser.GameParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {

    @Override
    public List<LottoTicket> createLottoTickets(PurchaseInfo purchaseInfo) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (String manualNumbersInput : purchaseInfo.getManualNumbersInputs()) {
            Set<Integer> numbers = GameParser.parseInputToNumbers(manualNumbersInput);
            Set<LottoNumber> lottoNumbers = numbers.stream()
                    .map(LottoNumber::from)
                    .collect(Collectors.toSet());
            lottoTickets.add(LottoTicket.from(lottoNumbers));
        }
        return lottoTickets;
    }
}

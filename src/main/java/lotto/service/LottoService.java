package lotto.service;

import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.purchase_info.Count;
import lotto.domain.purchase_info.LottoMoney;
import lotto.domain.purchase_info.PurchaseInfo;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;
import lotto.parser.GameParser;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoService {

    public LottoMoney createLottoMoney(String inputMoney) {
        int money = GameParser.parseInputToInt(inputMoney);
        return new LottoMoney(money);
    }

    public Count createCount(LottoMoney lottoMoney, String inputManualCount) {
        int manualCount = GameParser.parseInputToInt(inputManualCount);
        return new Count(lottoMoney.getLottoPurchaseCounts(), manualCount);
    }

    public LottoTickets createLottoTickets(List<String> lottoTicketInputs, Count count) {
        PurchaseInfo purchaseInfo = new PurchaseInfo(lottoTicketInputs, count.getAutoCounts());
        return LottoFactory.publishLottoTickets(purchaseInfo);
    }

    public WinningLotto createWinningLotto(String inputNumbers, String inputBonusNumber) {
        LottoTicket winningLottoNumbers = createLottoTicket(inputNumbers);
        LottoNumber bonusNumber = createLottoNumber(inputBonusNumber);
        return WinningLotto.from(winningLottoNumbers, bonusNumber);
    }

    private LottoTicket createLottoTicket(String inputNumbers) {
        Set<Integer> winningNumbers = GameParser.parseInputToNumbers(inputNumbers);
        Set<LottoNumber> winningLottoNumbers = winningNumbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
        return LottoTicket.from(winningLottoNumbers);
    }

    private LottoNumber createLottoNumber(String inputBonusNumber) {
        int number = GameParser.parseInputToInt(inputBonusNumber);
        return LottoNumber.from(number);
    }

    public LottoResult createLottoResult(LottoTickets lottoTickets, WinningLotto winningLotto) {
        Map<Rank, Integer> rankMap = new EnumMap<>(Rank.class);
        Arrays.stream(Rank.values()).forEach(rank -> rankMap.put(rank, 0));

        lottoTickets.getLottoTickets()
                .stream()
                .map(winningLotto::getRank)
                .forEach(rank -> rankMap.computeIfPresent(rank, (key, value) -> value + 1));

        return new LottoResult(rankMap);
    }
}

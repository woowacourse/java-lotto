package lotto.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.utils.LottoGenerator;

public class Machine {
    private final Money money;
    private final LottoTickets lottoTickets;

    public Machine(String moneyValue, LottoGenerator lottoGenerator) {
        this(moneyValue, new ArrayList<>(), lottoGenerator);
    }

    public Machine(String moneyValue, List<String> lottoNumbers, LottoGenerator lottoGenerator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (String lottoNumber : lottoNumbers) {
            lottoTickets.add(new LottoTicket(lottoNumber));
        }
        this.money = new Money(moneyValue, lottoTickets.size());
        this.lottoTickets = new LottoTickets(lottoTickets, money.getPossibleTicketCount(),
            lottoGenerator);
    }

    public List<LottoTicket> getTickets() {
        return lottoTickets.getLottoTickets();
    }

    public int getChange() {
        return money.getChange();
    }

    public Result getResult(String winningNumbersValue, String bonusBallValue) {
        final WinningNumbers winningNumbers = new WinningNumbers(winningNumbersValue,
            bonusBallValue);
        final Map<Rank, Integer> resultMap = setResultMap(winningNumbers, lottoTickets);
        final BigInteger earningRate = money.getEarningRate(resultMap);

        return new Result(resultMap, earningRate);
    }

    private Map<Rank, Integer> setResultMap(WinningNumbers winningNumbers,
        LottoTickets lottoTickets) {
        Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);

        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            Rank rank = winningNumbers.getRank(lottoTicket);
            resultMap.put(rank, resultMap.getOrDefault(rank, 0) + 1);
        }

        return resultMap;
    }


}

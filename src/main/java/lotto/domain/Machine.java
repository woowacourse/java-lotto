package lotto.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.utils.CustomException;
import lotto.utils.LottoGenerator;

public class Machine {
    private static final Money LOTTO_PRICE = Money.valueOf(1_000);
    private static final int EQUAL_CASE = 0;
    private final Money change;
    private final Money investment;
    private final LottoTickets lottoTickets;

    public Machine(String moneyValue, LottoGenerator lottoGenerator) {
        this(moneyValue, new ArrayList<>(), lottoGenerator);
    }

    public Machine(String moneyValue, List<String> lottoNumbers, LottoGenerator lottoGenerator) {
        validatePossiblePriceToBuy(moneyValue);
        final Money initialMoney = Money.valueOf(moneyValue);
        final int analogTicketsCount = lottoNumbers.size();
        validateAnalogNotOverMoney(initialMoney, analogTicketsCount);
        final int autoTicketsCount = getAutoTicketsCount(lottoNumbers, initialMoney);

        this.change = initialMoney.getChange(analogTicketsCount, autoTicketsCount, LOTTO_PRICE);
        this.investment = initialMoney.subtract(change);
        this.lottoTickets = new LottoTickets(getAnalogLottoTickets(lottoNumbers), autoTicketsCount,
            lottoGenerator);
    }

    private List<LottoTicket> getAnalogLottoTickets(List<String> lottoNumbers) {
        return lottoNumbers.stream()
            .map(LottoTicket::new)
            .collect(Collectors.toList());
    }

    private int getAutoTicketsCount(List<String> lottoNumbers, Money initialMoney) {
        return initialMoney.divide(LOTTO_PRICE).toInt() - lottoNumbers.size();
    }

    private void validatePossiblePriceToBuy(String moneyValue) {
        if (Money.valueOf(moneyValue).compareTo(LOTTO_PRICE) < EQUAL_CASE) {
            throw new CustomException("로또 금액보다 적은 가격이라 구매가 취소됩니다.");
        }
    }

    private void validateAnalogNotOverMoney(Money initialMoney, int analogTicketCount) {
        final Money analogTicketMoney = initialMoney
            .getMoneySpentForTicket(LOTTO_PRICE, analogTicketCount);
        if (initialMoney.compareTo(analogTicketMoney) < EQUAL_CASE) {
            throw new CustomException("수동발행이 구입가능금액을 넘어 발행이 취소됩니다.");
        }
    }

    public List<LottoTicket> getTickets() {
        return this.lottoTickets.getLottoTickets();
    }

    public int getChange() {
        return this.change.toInt();
    }

    public Result getResult(String winningNumbersValue, String bonusBallValue) {
        final WinningNumbers winningNumbers = new WinningNumbers(winningNumbersValue,
            bonusBallValue);
        final Map<Rank, Integer> resultMap = setResultMap(winningNumbers, lottoTickets);
        final BigInteger earningRate = investment.getEarningRate(resultMap);

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

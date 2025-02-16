package service;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import domain.Lotto;
import domain.Rank;
import domain.Ticket;
import domain.WinningInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import domain.LottoBundle;
import utils.InputParser;
import utils.RandomNumber;

public class LottoService {
    private final LottoBundle lottoBundle;

    public LottoService(LottoBundle lottoBundle) {
        this.lottoBundle = lottoBundle;
    }

    public Ticket createTicket(int price) {
        return Ticket.create(price);
    }

    public void createLottoBundleForTicket(Ticket ticket) {
        for (int i = 0; i < ticket.getQuantity(); i++) {
            List<Integer> numbers = RandomNumber.generateNumbers(WINNING_NUMBERS_REQUIRED);
            Lotto lotto = Lotto.from(numbers);

            lottoBundle.addLotto(lotto);
        }
    }

    public List<Lotto> getLottoBundle() {
        return lottoBundle.getLottoBundle();
    }

    public Lotto createLotto(String winningNumbers) {
        List<Integer> parsedNumbers = InputParser.parseAndCreateWinningNumbers(winningNumbers);
        return Lotto.from(parsedNumbers);
    }

    public WinningInfo createWinningNumber(Lotto winningNumbers, int bonusNumber) {
        return WinningInfo.of(winningNumbers, bonusNumber);
    }

    public Map<Rank, Integer> calculateMatchingRank(WinningInfo winningInfo, List<Lotto> lottoBundle) {
        Map<Rank, Integer> calculateResult = new LinkedHashMap<>();

        for (Rank value : Rank.values()) {
            calculateResult.put(value, 0);
        }

        for (Lotto lotto : lottoBundle) {
            Rank foundRank = findRank(winningInfo, lotto);
            calculateResult.put(foundRank, calculateResult.get(foundRank) + 1);
        }

        return calculateResult;
    }

    private Rank findRank(WinningInfo winningInfo, Lotto lotto) {
        int matchCount = lotto.calculateMatchCount(winningInfo.getWinningLotto());
        boolean matchBonus = lotto.hasBonusNumber( winningInfo.getBonusNumber());
        return Rank.findRank(matchCount, matchBonus);
    }


    public double calculateProfit(Map<Rank, Integer> calculateResult, int purchaseAmount) {
        double totalPrize = 0;

        for (Rank rank : calculateResult.keySet()) {
            totalPrize += rank.getPrize() * calculateResult.get(rank);
        }

        return Math.floor((totalPrize / purchaseAmount) * 100) / 100;
    }
}

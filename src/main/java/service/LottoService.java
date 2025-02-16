package service;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import domain.Lotto;
import domain.Rank;
import domain.Ticket;
import domain.WinningInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import repository.LottoRepository;
import utils.InputParser;
import utils.RandomNumber;

public class LottoService {
    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public Ticket createTicket(int price) {
        return Ticket.create(price);
    }

    public void createLottos(Ticket ticket) {
        for (int i = 0; i < ticket.getQuantity(); i++) {
            List<Integer> numbers = RandomNumber.generateNumbers(WINNING_NUMBERS_REQUIRED);
            Lotto lotto = Lotto.from(numbers);

            lottoRepository.addLotto(lotto);
        }
    }

    public List<Lotto> getLottos() {
        return lottoRepository.getLottos();
    }

    public Lotto createLotto(String winningNumbers) {
        List<Integer> parsedNumbers = InputParser.parseAndCreateWinningNumbers(winningNumbers);
        return Lotto.from(parsedNumbers);
    }

    public WinningInfo createWinningNumber(Lotto winningNumbers, int bonusNumber) {
        return WinningInfo.of(winningNumbers, bonusNumber);
    }

    public Map<Rank, Integer> calculateRank(WinningInfo winningInfo, List<Lotto> lottos) {
        Map<Rank, Integer> calculateResult = new LinkedHashMap<>();

        for (Rank value : Rank.values()) {
            calculateResult.put(value, 0);
        }

        List<Integer> winningNumbers = winningInfo.getWinningLotto().getNumbers();

        for (Lotto lotto : lottos) {
            Rank foundRank = findRank(winningInfo, lotto, winningNumbers);
            calculateResult.put(foundRank, calculateResult.get(foundRank) + 1);
        }

        return calculateResult;
    }

    private Rank findRank(WinningInfo winningInfo, Lotto lotto, List<Integer> winningNumbers) {
        int matchCount = 0;
        boolean matchBonus = false;
        List<Integer> lottoNumbers = lotto.getNumbers();

        for (Integer lottoNumber : lottoNumbers) {
            matchCount = checkMatchCount(winningNumbers, lottoNumber, matchCount);
            matchBonus = isMatchBonus(winningInfo, lottoNumber);
        }

        return Rank.findRank(matchCount, matchBonus);
    }

    private static boolean isMatchBonus(WinningInfo winningInfo, Integer lottoNumber) {
        int bonusNumber = winningInfo.getBonusNumber();

        return bonusNumber == lottoNumber;
    }

    private static int checkMatchCount(List<Integer> winningNumbers, Integer lottoNumber, int count) {
        if (winningNumbers.contains(lottoNumber)) {
            count++;
        }

        return count;
    }

    public double calculateProfit(Map<Rank, Integer> calculateResult, int purchaseAmount) {
        double totalPrize = 0;

        for (Rank rank : calculateResult.keySet()) {
            totalPrize += rank.getPrize() * calculateResult.get(rank);
        }

        return Math.floor((totalPrize / purchaseAmount) * 100) / 100;
    }
}

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
import utils.RandomNumber;

public class LottoService {

    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public void saveLotto(Ticket ticket) {
        for (int i = 0; i < ticket.getQuantity(); i++) {
            List<Integer> numbers = RandomNumber.generateNumbers(WINNING_NUMBERS_REQUIRED);
            Lotto lotto = Lotto.from(numbers);
            lottoRepository.addLotto(lotto);
        }
    }

    public List<Lotto> getLottos() {
        return lottoRepository.getLottos();
    }

    public Map<Rank, Integer> calculateRank(WinningInfo winningInfo, List<Lotto> lottos) {
        Map<Rank, Integer> calculateResult = new LinkedHashMap<>();
        for (Rank value : Rank.values()) {
            calculateResult.put(value, 0);
        }
        List<Integer> winningNumbers = winningInfo.getWinningLotto().getNumbers();
        for (Lotto lotto : lottos) {
            int count = 0;
            boolean isMatchBonusNumber = false;
            List<Integer> lottoNumbers = lotto.getNumbers();
            for (Integer lottoNumber : lottoNumbers) {
                if (winningNumbers.contains(lottoNumber)) {
                    count++;
                }
                if (winningInfo.getBonusNumber() == lottoNumber) {
                    isMatchBonusNumber = true;
                }

            }
            Rank foundRank = Rank.findRank(count, isMatchBonusNumber);
            calculateResult.put(foundRank, calculateResult.get(foundRank) + 1);
        }
        return calculateResult;
    }

    public double calculateProfit(Map<Rank, Integer> calculateResult, int purchaseAmount) {
        double totalPrize = 0;
        for (Rank rank : calculateResult.keySet()) {
            totalPrize += rank.getPrize() * calculateResult.get(rank);
        }
        return Math.floor((totalPrize / purchaseAmount) * 100) / 100;
    }
}

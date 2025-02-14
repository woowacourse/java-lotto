package service;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import creator.LottoCreator;
import domain.Lotto;
import domain.Profit;
import domain.Rank;
import domain.Ticket;
import domain.WinningNumber;
import java.util.List;
import java.util.Map;
import repository.LottoRepository;
import repository.LottoResultRepository;
import utils.RandomNumber;

public class LottoService {

    private final LottoRepository lottoRepository;
    private final LottoResultRepository lottoResultRepository;

    public LottoService(LottoRepository lottoRepository,
        LottoResultRepository lottoResultRepository) {
        this.lottoRepository = lottoRepository;
        this.lottoResultRepository = lottoResultRepository;
    }

    public Ticket makeTicket(int purchaseAmount) {
        return LottoCreator.createTicket(purchaseAmount);
    }

    public Lotto makeLotto(String winningNumbers) {
        return LottoCreator.createLotto(winningNumbers);
    }

    public WinningNumber makeWinningNumber(Lotto lotto, int bonusNumber) {
        return LottoCreator.createWinningNumber(lotto, bonusNumber);
    }

    public double calculateProfit(Map<Rank, Integer> rankResult, int purchaseAmount) {
        Profit profit = LottoCreator.createProfit(rankResult, purchaseAmount);
        return profit.getResult();
    }

    public void calculateRank(WinningNumber winningNumber, List<Lotto> lottos) {
        lottoResultRepository.add(winningNumber, lottos);
    }

    public Map<Rank, Integer> getRankResult() {
        return lottoResultRepository.getCalculateResult();
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

}

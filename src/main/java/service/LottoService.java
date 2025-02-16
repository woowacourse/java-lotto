package service;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import creator.LottoCreator;
import domain.Lotto;
import domain.Profit;
import domain.Rank;
import domain.Ticket;
import domain.WinningNumber;
import domain.dto.LottoDto;
import domain.dto.LottoResultDto;
import domain.dto.LottosDto;
import domain.dto.TicketDto;
import domain.dto.WinningNumberDto;
import java.util.ArrayList;
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

    public TicketDto makeTicket(int purchaseAmount) {
        Ticket ticket = LottoCreator.createTicket(purchaseAmount);
        return TicketDto.from(ticket);
    }

    public LottoDto makeLotto(String winningNumbers) {
        Lotto lotto = LottoCreator.createLotto(winningNumbers);
        return LottoDto.from(lotto.getNumbers());
    }

    public WinningNumberDto makeWinningNumber(LottoDto lottoDto, int bonusNumber) {
        Lotto lotto = Lotto.from(lottoDto.getNumbers());
        WinningNumber winningNumber = LottoCreator.createWinningNumber(lotto, bonusNumber);
        Lotto numbers = winningNumber.getNumbers();
        return WinningNumberDto.of(numbers.getNumbers(), winningNumber.getBonusNumber());
    }

    public double calculateProfit(LottoResultDto lottoResultDto,  int purchaseAmount) {
        Profit profit = LottoCreator.createProfit(lottoResultDto.getViewResult(), purchaseAmount);
        return profit.getResult();
    }

    public void calculateRank(WinningNumberDto winningNumberDto, LottosDto lottosDto) {
        Lotto lotto = Lotto.from(winningNumberDto.getNumbers());
        WinningNumber winningNumber = WinningNumber.of(lotto, winningNumberDto.getBonusNumber());
        List<List<Integer>> tmpLottos = lottosDto.getLottos();
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> tmpLotto : tmpLottos) {
            Lotto generateLotto = Lotto.from(tmpLotto);
            lottos.add(generateLotto);
        }
        lottoResultRepository.add(winningNumber, lottos);
    }

    public LottoResultDto getRankResult() {
        Map<Rank, Integer> calculateResult = lottoResultRepository.getCalculateResult();
        return LottoResultDto.from(calculateResult);
    }

    public void saveLotto(TicketDto ticketDto) {
        for (int i = 0; i < ticketDto.getTicket(); i++) {
            List<Integer> numbers = RandomNumber.generateNumbers(WINNING_NUMBERS_REQUIRED);
            Lotto lotto = Lotto.from(numbers);
            lottoRepository.addLotto(lotto);
        }
    }

    public LottosDto getLottos() {
        List<Lotto> lottos = lottoRepository.getLottos();
        return LottosDto.from(lottos);
    }

}

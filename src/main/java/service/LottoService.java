package service;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import domain.Lotto;
import domain.Ticket;
import domain.WinningNumber;
import java.util.List;
import repository.LottoRepository;
import utils.InputParser;
import utils.RandomNumber;

public class LottoService {
    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository){
        this.lottoRepository=lottoRepository;
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

    public List<Lotto> getLottos(){
        return lottoRepository.getLottos();
    }

    public Lotto createLotto(String winningNumbers) {
        List<Integer> parsedNumbers = InputParser.parseAndCreateWinningNumbers(winningNumbers);
        return Lotto.from(parsedNumbers);
    }

    public WinningNumber createWinningNumber(Lotto winningNumbers, int bonusNumber) {
        return WinningNumber.of(winningNumbers, bonusNumber);
    }
}

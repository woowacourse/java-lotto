package service;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import domain.Lotto;
import domain.Ticket;
import java.util.List;
import repository.LottoRepository;
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
            Lotto lotto = Lotto.create(numbers);

            lottoRepository.addLotto(lotto);
        }
    }

    public List<Lotto> getLottos(){
        return lottoRepository.getLottos();
    }
}

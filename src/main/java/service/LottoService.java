package service;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import domain.Lotto;
import domain.Rank;
import domain.Ticket;
import domain.WinningInfo;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    public WinningInfo createWinningNumber(Lotto winningNumbers, int bonusNumber) {
        return WinningInfo.of(winningNumbers, bonusNumber);
    }

    public Map<Rank, Integer> calculateRank(WinningInfo winningInfo, List<Lotto> lottos){
        Map<Rank,Integer> calculateResult = new LinkedHashMap<>();

        Rank[] values = Rank.values();
        for (Rank value : values) {
            calculateResult.put(value, 0);

        }

        List<Integer> winningNumbers = winningInfo.getWinningLotto().getNumbers();

        for (Lotto lotto:lottos){
            int count = 0;
            boolean isMatchBonusNumber = false;
            List<Integer> lottoNumbers = lotto.getNumbers();
            for (Integer lottoNumber : lottoNumbers) {
                if (winningNumbers.contains(lottoNumber)) {
                    count++;
                }
                if(winningInfo.getBonusNumber()== lottoNumber){
                    isMatchBonusNumber=true;
                }

            }
            Rank foundRank = Rank.findRank(count, isMatchBonusNumber);
            calculateResult.put(foundRank,calculateResult.get(foundRank)+1);
        }
        return calculateResult;
    }
}

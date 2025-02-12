package service;

import domain.Lotto;
import dto.IssuedLottoDto;
import dto.IssuedLottosDto;
import java.util.ArrayList;
import java.util.List;
import util.RandomNumberGenerator;

public class LottoIssue {

    public IssuedLottosDto issueLottos(int money){
        validateMoney(money);
        int count = money / 1000;
        List<IssuedLottoDto> issuedLottos = new ArrayList<>();
        while(issuedLottos.size()!= count) {
            Lotto lotto = new Lotto(RandomNumberGenerator.getRandomNumbers(1, 45));
            if(!isDuplicate(issuedLottos,lotto.getSortedNumbers())){
                issuedLottos.add(new IssuedLottoDto(lotto.getSortedNumbers()));
            }
        }
        return new IssuedLottosDto(issuedLottos);
    }

    private void validateMoney(int money){
        if(money % 1000 != 0){
            throw new IllegalArgumentException("금액 단위 오류");
        }
    }

    private boolean isDuplicate(List<IssuedLottoDto> lottos, List<Integer> numbers){
        IssuedLottoDto issuedLottoDto = new IssuedLottoDto(numbers);
        return lottos.contains(issuedLottoDto);
    }
}

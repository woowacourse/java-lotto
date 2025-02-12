package lotto.domain;

import static lotto.utill.RandomsWrapper.*;

import java.util.ArrayList;
import java.util.List;

import lotto.dto.MatchCountDto;

public class Wallet {
    private List<Lotto> lottos = new ArrayList<>();

    public Wallet(Amount amount){
        int lottoAmount = amount.getAmount();
        for(int i =0; i<lottoAmount; i++) {
            lottos.add(new Lotto(getRandomNumbers()));
        }
    }

    public List<MatchCountDto> matchCount(Lotto matchLotto, int bonus) {
        List<MatchCountDto> matchCountDtos = new ArrayList<>();

        for (Lotto lotto : lottos) {
            int count =0;
                for(int i :matchLotto.numbers()){

                    if(lotto.numbers().contains(i)){
                        count++;
                    }
                }

            matchCountDtos.add(new MatchCountDto(count,lotto.numbers().contains(bonus)));
        }

        return matchCountDtos;
    }

}

package lotto.domain;

import static lotto.common.utill.RandomsWrapper.*;

import java.util.ArrayList;
import java.util.List;

import lotto.dto.MatchCountDto;

public class Wallet {
    private final List<Lotto> lottos = new ArrayList<>();

    public Wallet(Amount amount) {
        int lottoAmount = amount.getAmount();
        for (int i = 0; i < lottoAmount; i++) {
            lottos.add(new Lotto(getRandomNumbers()));
        }
    }

    public List<MatchCountDto> matchCount(Lotto matchLotto, int bonus) {
        List<MatchCountDto> matchCountDtos = new ArrayList<>();

        for (Lotto lotto : lottos) {
            MatchCountDto dto = lotto.matchCount(matchLotto, bonus);
            matchCountDtos.add(dto);
        }
        return matchCountDtos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Lotto lotto : lottos) {
            sb.append(lotto.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}

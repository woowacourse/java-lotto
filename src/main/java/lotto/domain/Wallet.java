package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import lotto.dto.MatchCountDto;

public class Wallet {
    private final List<Lotto> lottoList = new ArrayList<>();

    public Wallet(Amount amount) {
        int lottoAmount = amount.getAmount();
        for (int i = 0; i < lottoAmount; i++) {
            lottoList.add(Lotto.generateLotto());
        }
    }

    public List<MatchCountDto> matchCount(Lotto matchLotto, int bonus) {
        List<MatchCountDto> list = new ArrayList<>();

        for (Lotto lotto : lottoList) {
            MatchCountDto dto = lotto.matchCount(matchLotto, bonus);
            list.add(dto);
        }

        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Lotto lotto : lottoList) {
            sb.append(lotto.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}

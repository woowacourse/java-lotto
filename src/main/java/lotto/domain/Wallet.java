package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import lotto.dto.MatchCountDto;

public class Wallet {
    private final List<Lotto> lottos;

    public Wallet(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<MatchCountDto> getMatchResults(Lotto matchLotto, int bonus) {
        List<MatchCountDto> matchCountDtos = new ArrayList<>();

        for (Lotto lotto : lottos) {
            MatchCountDto dto = lotto.countMatchingNumbers(matchLotto, bonus);
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

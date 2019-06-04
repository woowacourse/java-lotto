package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class UserLottos {
    private final List<Lotto> lottos;

    public UserLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public UserLottos(UserLottoDto dto) {
        int lottoCount = Integer.parseInt(dto.getInputLottoMoney()) / 1000;
        lottos = new ArrayList<>(manualLottos(dto.getManualLottoNumber()));
        while (lottos.size() != lottoCount) {
            lottos.add(LottoGenerator.lotto());
        }
    }

    private List<Lotto> manualLottos(List<String> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (String number : numbers) {
            lottos.add(new Lotto(splitNumbers(number)));
        }
        return lottos;
    }

    private List<Integer> splitNumbers(String numbers) {
        try {
            return Arrays.stream(numbers.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public LottoResult match(WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos) {
            lottoResult.plus(winningLotto.match(lotto));
        }
        return lottoResult;
    }

    public int size() {
        return lottos.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(size()).append("개 구매했습니다.\n");
        for (Lotto lotto : lottos) {
            stringBuilder.append(lotto.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}

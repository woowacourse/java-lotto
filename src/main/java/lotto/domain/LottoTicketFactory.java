package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketFactory {
    public static UserLottoTicket create() {
        List<Integer> temp = getRandomNumbers();
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lottoNumbers.add(temp.get(i));
        }
        return new UserLottoTicket(lottoNumbers);
    }

    private static List<Integer> getRandomNumbers() {
        List<Integer> temp = IntStream.range(1, 45)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(temp);
        return temp;
    }
}

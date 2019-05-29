package lotto.domain.factory;

import lotto.domain.LottoTicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RewardTicketFactory {
    private static final String DELIMITER = ",";

    public static LottoTicket create(final String input) {
        List<String> list = Arrays.asList(input.split(DELIMITER));
        List<Integer> temp = new ArrayList<>();
        for (String s : list) {
            temp.add(Integer.parseInt(s));
        }
        return new LottoTicket(temp);
    }
}

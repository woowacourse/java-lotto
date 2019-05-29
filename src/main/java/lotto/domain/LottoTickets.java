package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<UserLottoTicket> userLottoTickets;

    public LottoTickets(List<UserLottoTicket> userLottoTickets) {
        this.userLottoTickets = userLottoTickets;
    }

    public List<Integer> getRewards(UserLottoTicket rewardTicket) {
        List<Integer> rewards = new ArrayList<>();
        for (UserLottoTicket userLottoTicket : userLottoTickets) {
            rewards.add(userLottoTicket.getSameCount(rewardTicket));
        }
        return rewards;
    }
}

package lotto.domain.ticket;

import java.util.List;

public enum LottoType implements LottoTypeInterface {
    MANUAL {
        public List<LottoTicket> generate(int nums, List<List<Integer>> manualNumbers) {
            return new ManualGenaratingStarategy(nums, manualNumbers).generateTickets();
        }
    }, AUTOMATIC {
        public List<LottoTicket> generate(int nums) {
            return new AutomaticGenaratingStarategy(nums).generateTickets();
        }
    };

    LottoType() {
    }

}

interface LottoTypeInterface {
    default List<LottoTicket> generate(int nums, List<List<Integer>> manualNumbers) {
        return null;
    }

    default List<LottoTicket> generate(int nums) {
        return null;
    }
}


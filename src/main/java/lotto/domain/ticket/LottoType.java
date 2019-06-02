package lotto.domain.ticket;

import java.util.List;

public enum LottoType implements LottoTypeInterface {
    MANUAL {
        public List<LottoNumber> generate(List<Integer> manualNumbers) {
            return new ManualGenaratingStarategy(manualNumbers).generateNumber();
        }
    }, AUTOMATIC {
        public List<LottoNumber> generate() {
            return new AutomaticGenaratingStarategy().generateNumber();
        }
    };

    LottoType() {
    }

}

interface LottoTypeInterface {
    default List<LottoNumber> generate() {
        return null;
    }

    default List<LottoNumber> generate(List<Integer> manualNumbers) {
        return null;
    }
}


package lotto.domain;

import lotto.dto.GameResultDto;
import lotto.util.LottoDtoConverter;
import lotto.util.LottoParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {
    private static final List<Integer> WINNING_NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6);
    private static final List<Integer> FIFTH_NUMBERS = Arrays.asList(42, 2, 3, 4, 10, 11);
    private static final List<Integer> MISS_NUMBERS = Arrays.asList(1, 2, 17, 18, 19, 20);
    private static final int MONEY = 2000;
    private static final int BONUS_NUM = 11;
    private static final int ANSWER = 250;

    private LottoFactory lottoFactory;
    private LottoService service;

    @BeforeEach
    public void setUp() {
        lottoFactory = new LottoFactory();
        service = LottoService.getInstance();

        service.charge(MONEY);
        service.buy(lottoFactory.create(FIFTH_NUMBERS));
        while (service.canBuy()) {
            service.buy(lottoFactory.create(MISS_NUMBERS));
        }
    }

    @Test
    public void 구매를_제대로하고_당첨로또에_따른_결과를_제대로_반환해주는지() {
        Lotto lotto = lottoFactory.create(WINNING_NUMBERS);
        LottoDtoConverter converter = new LottoDtoConverter();
        GameResultMatcher gameResultMatcher = GameResultMatcher.of(converter.convertDtoToLottos(service.getLottos()));
        gameResultMatcher.match(WinningLotto.of(lotto, LottoNumber.of(BONUS_NUM)));
        assertThat(gameResultMatcher.profit(LottoMachine.LOTTO_MONEY)).isEqualTo(ANSWER);
    }

    @AfterEach
    public void tearDown() {
        service.deleteAll();
    }
}
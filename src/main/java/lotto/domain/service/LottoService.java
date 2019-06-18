package lotto.domain.service;

import lotto.domain.dao.LottoDao;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;

import java.sql.SQLDataException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class LottoService {
    private LottoService() {
    }

    private static class LottoServiceHolder {
        private static final LottoService INSTANCE = new LottoService();
    }

    public static LottoService getInstance() {
        return LottoServiceHolder.INSTANCE;
    }

    public List<String> createResponseInputTag(int countOfManualLotto) {
        return IntStream.rangeClosed(1, countOfManualLotto)
                .mapToObj(i -> "로또 번호 " + i)
                .collect(toList());
    }

    public LottoRepository addLottos(String[] inputLottos, int countOfLotto) {
        LottoRepository lottoRepository = new LottoRepository();
        List<Lotto> manualLotto = createManualLottos(inputLottos);
        lottoRepository.addAll(manualLotto);

        List<Lotto> randomLotto = createRandomLottos(countOfLotto - manualLotto.size());
        lottoRepository.addAll(randomLotto);
        return lottoRepository;
    }

    private List<Lotto> createManualLottos(String[] inputLottos) {
        return Arrays.stream(Optional.ofNullable(inputLottos).orElse(new String[]{}))
                .filter(this::hasContent)
                .map(this::splitInputLottoNumbers)
                .map(list -> LottoGenerator.create(new ManualLottoGeneratingStrategy(list)))
                .collect(toList());
    }

    private List<Integer> splitInputLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private boolean hasContent(String value) {
        return !Objects.isNull(value) && !value.isEmpty();
    }

    private List<Lotto> createRandomLottos(int countOfRandomLotto) {
        return IntStream.rangeClosed(1, countOfRandomLotto)
                .mapToObj(i -> LottoGenerator.create(new RandomLottoGeneratingStrategy()))
                .collect(toList());
    }

    public int insertLottoTicket(LottoRepository lottoRepository, int round) throws SQLDataException {
        return LottoDao.getInstance().insertLottoTicket(
                new LottoTickets(lottoRepository), round);
    }

    public int insertWinningLotto(WinningLotto winningLotto, int round) throws SQLDataException {
        return LottoDao.getInstance().insertWinningLotto(winningLotto, round);
    }

    public List<Lotto> selectAllLotto(int round) throws SQLDataException {
        return LottoDao.getInstance().selectAllLotto(round);
    }

    public WinningLotto selectWinningLotto(int round) throws SQLDataException {
        return LottoDao.getInstance().selectWinningLotto(round);
    }

    public long calculateCountOfAutoLotto(List<Lotto> lottoTickets) {
        return lottoTickets.stream().filter(Lotto::getIsAuto).count();
    }
}

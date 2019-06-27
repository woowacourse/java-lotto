package lotto.service;

import lotto.dao.LottoDao;
import lotto.domain.lotto.*;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import lotto.service.dto.ResultDto;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class LottoService {
    private static final LottoDao LOTTO_DAO = LottoDao.getInstance();
    private static final LottoService LOTTO_SERVICE = LottoService.getInstance();
    private static final LottoResultService LOTTO_RESULT_SERVICE = LottoResultService.getInstance();

    private LottoService() {
    }

    private static class LottoServiceHolder {
        private static final LottoService INSTANCE = new LottoService();
    }

    public static LottoService getInstance() {
        return LottoServiceHolder.INSTANCE;
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

    public int insertLottoAndResult(String[] inputLottos, int countOfLotto, String inputWinningLotto, String inputBonusBall, int round, String name) throws SQLException {
        LottoRepository lottoRepository = addLottos(inputLottos, countOfLotto);
        WinningLotto winningLotto = createWinningLotto(inputWinningLotto, inputBonusBall);
        LOTTO_SERVICE.insertWinningLotto(winningLotto, round);
        LOTTO_RESULT_SERVICE.insertLottoResult(createResultDto(winningLotto, lottoRepository, round, name));

        return LOTTO_DAO.insertLottoTicket(new LottoTickets(lottoRepository), round);
    }

    private ResultDto createResultDto(WinningLotto winningLotto, LottoRepository lottoRepository, int round, String name) {
        Result result = winningLotto.match(new LottoTickets(lottoRepository));
        ResultDto resultDto = new ResultDto.Builder(round, name)
                .first(result.get(Rank.FIRST))
                .second(result.get(Rank.SECOND))
                .third(result.get(Rank.THIRD))
                .fourth(result.get(Rank.FOURTH))
                .fifth(result.get(Rank.FIFTH))
                .miss(result.get(Rank.MISS))
                .build();
        resultDto.setTotalWinningMoney(result.calculateTotalWinningMoney());
        return resultDto;
    }

    private LottoRepository addLottos(String[] inputLottos, int countOfLotto) {
        LottoRepository lottoRepository = new LottoRepository();
        List<Lotto> manualLotto = createManualLottos(inputLottos);
        lottoRepository.addAll(manualLotto);

        List<Lotto> randomLotto = createRandomLottos(countOfLotto - manualLotto.size());
        lottoRepository.addAll(randomLotto);
        return lottoRepository;
    }


    private List<Lotto> createRandomLottos(int countOfRandomLotto) {
        return IntStream.rangeClosed(1, countOfRandomLotto)
                .mapToObj(i -> LottoGenerator.create(new RandomLottoGeneratingStrategy()))
                .collect(toList());
    }

    public int insertWinningLotto(WinningLotto winningLotto, int round) throws SQLException {
        return LOTTO_DAO.insertWinningLotto(winningLotto, round);
    }

    private WinningLotto createWinningLotto(String winningLotto, String bonusBall) {
        List<Integer> inputWinningLotto = splitInputLottoNumbers(winningLotto);
        Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(inputWinningLotto));
        return new WinningLotto(lotto, Integer.parseInt(bonusBall));
    }

    public List<Lotto> selectAllLotto(int round) throws SQLException {
        return LOTTO_DAO.selectAllLotto(round);
    }

    public WinningLotto selectWinningLotto(int round) throws SQLException {
        return LOTTO_DAO.selectWinningLotto(round);
    }

    public long calculateCountOfAutoLotto(List<Lotto> lottoTickets) {
        return lottoTickets.stream().filter(Lotto::getIsAuto).count();
    }
}

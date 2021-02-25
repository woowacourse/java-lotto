package lottogame.domain;

import lottogame.domain.dto.LottoDto;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    private final LottoNumberGenerator numberGenerator;

    public LottoMachine() {
        numberGenerator = new LottoNumberGenerator(LottoNumber.LOTTO_MIN, LottoNumber.LOTTO_MAX);
    }

    public List<Lotto> buyAutoTicket(int quantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            lottos.add(makeLotto());
        }
        return lottos;
    }

    private Lotto makeLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() < Lotto.LOTTO_NUMBER_VOLUME) {
            makeRandomNumber(lottoNumbers);
        }
        return new Lotto(lottoNumbers);
    }

    private void makeRandomNumber(List<LottoNumber> lottoNumbers) {
        int random = numberGenerator.generate();
        if (isPossible(random, lottoNumbers)) {
            lottoNumbers.add(new LottoNumber(random));
        }
    }

    private boolean isPossible(int random, List<LottoNumber> lottoNumbers) {
        return !lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumber.equals(random))
                .findAny()
                .isPresent();
    }

    public List<Lotto> makeManualLotto(List<LottoDto> manualLottos) {
        return manualLottos.stream()
                .map(lottoDto -> makeLotto(lottoDto.getNumbers()))
                .collect(Collectors.toList());
    }

    private Lotto makeLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream()
                .map(number -> new LottoNumber(number))
                .collect(Collectors.toList()));
    }
}

package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Lotto {

    private static final int LOTTO_COUNT = 6;
    private static final int LOTTO_MAX_NUMBER = 45;

    private final List<Integer> lottoNumber;

    /**
     * 자동으로 랜덤 생성한 숫자를 저장
     */
    public Lotto() {
        this.lottoNumber = createLotto();
    }

    /**
     * 사용자가 지정한 번호를 정렬하여 저장
     */
    public Lotto(List<Integer> lottoNumber) {
        sort(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private List<Integer> createLotto() {
        List<Integer> selectedNumber = new ArrayList<>();
        addRandomNumber(selectedNumber);
        sort(selectedNumber);
        return selectedNumber;
    }

    private void sort(List<Integer> numbers) {
        numbers.sort(Comparator.naturalOrder());
    }

    private void addRandomNumber(List<Integer> lottoNumber) {
        while (lottoNumber.size() < LOTTO_COUNT) {
            addSingleRandomNumber(lottoNumber);
        }
    }

    private void addSingleRandomNumber(List<Integer> lottoNumber) {
        Random random = new Random();
        int randomNumber = random.nextInt(LOTTO_MAX_NUMBER) + 1;
        if (!lottoNumber.contains(randomNumber)) {
            lottoNumber.add(randomNumber);
        }
    }

    public String printLottoNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        combineLottoNumber(sb);
        sb.append("]");
        return sb.toString();
    }

    private void combineLottoNumber(StringBuilder sb) {
        for (int i : lottoNumber) {
            sb.append(i);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}

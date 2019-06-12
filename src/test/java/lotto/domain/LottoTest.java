package lotto.domain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lotto.domain.autocreatelotto.MockAutoCreateLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoTest {
    public static final Lotto createLotto = Lotto.createLotto(new MockAutoCreateLotto());
    private Lotto lotto2;
    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {

        lottoNumbers = new ArrayList<>();

        // TODO : mapToObj 알아보기
        lottoNumbers = IntStream.rangeClosed(1, 6).mapToObj(LottoNumber::new).collect(Collectors.toList());

        lotto2 = Lotto.createLotto(lottoNumbers);
    }

    @Test
    void create_생성() {
        assertThat(createLotto).isEqualTo(lotto2);
    }

    @Test
    void create_리스트_개수_예외() {
        lottoNumbers.add(new LottoNumber(7));
        assertThrows(Exception.class, () -> {
            lotto2.createLotto(lottoNumbers);
        });
    }

    @Test
    void create_로또_중복_확인() {
        lottoNumbers.remove(0);
        lottoNumbers.add(new LottoNumber(6));
        assertThrows(Exception.class, () -> {
            lotto2.createLotto(lottoNumbers);
        });
    }

    @Test
    void match_확인() {
        assertThat(createLotto.matchCount(lotto2)).isEqualTo(6);
    }

    @Test
    void jsonTest() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (int i = 1; i <= 6; i++) {
            JsonObject number = new JsonObject();
            number.addProperty("lottoNumber", i);
            jsonArray.add(number);
        }
        jsonObject.add("lotto",jsonArray);
        assertThat(new Gson().toJson(createLotto)).isEqualTo(new Gson().toJson(jsonObject));
    }
}

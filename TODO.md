1. LottoNumber
    * 1~45 중 하나의 값을 가진다.
        * new Lotto(0) -> Exception
2. Lotto
    * Lotto는 하나에 6개의 숫자를 가진다.
        * Lotto.size == 6
    * 각각의 숫자는 중복되지 않는다.
        * Lotto.size == HashSet<>(lotto).size
3. LottoPaper
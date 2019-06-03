# Lotto

## To-do-list

- ~~create a random lotto~~
    - ~~class LottoNumber~~
        - ~~validate lotto number (1 ~ 45)~~
        - ~~create lotto numbers in advance~~
        - ~~compareTo()~~
    - ~~class Lotto~~
        - ~~validate not null~~
        - ~~validate no duplication in lotto numbers~~
        - ~~validate size of 6~~
    - ~~create random lotto~~
- ~~14000 -> 14 random lottos~~
    - ~~class Money~~
        - ~~lottoBuyingMoney > 0~~
        - ~~is multiple of~~
        - ~~divide by~~
    - ~~class LottoVendingMachine~~
        - ~~lottoBuyingMoney % LOTTO_PRICE == 0~~
        - ~~get random lottos given lottoBuyingMoney~~
        - ~~get custom lottos given numbers~~
- ~~winning numbers, bonus ball, lottos -> winning statistics~~
    - ~~class WinningLotto~~
        - ~~validate bonus ball is not in lotto numbers~~
        - ~~return count of matches between two lottos~~
        - ~~match lotto to winning lotto and return rank~~
    - ~~enum Rank~~
    - ~~class WinningStatistics~~
- ~~class Lottos~~
    - ~~match(WinningLotto): List\<Rank\>~~


## Refactoring List

- ~~create LottoFactory class~~
- ~~use StringUtils to verify lotto numbers~~
- ~~add more tests~~
- ~~validate money in LottoBuyingMoney class~~
- ~~create Money class and make LottoBuyingMoney extends it~~
- create LottoCount class
<p align="center">
    <img src="./woowacourse.png" alt="우아한테크코스" width="250px">
</p>

# 로또 - OOP

---

![Generic badge](https://img.shields.io/badge/Level1-lotto-green.svg)
![Generic badge](https://img.shields.io/badge/test-31_passed-blue.svg)
![Generic badge](https://img.shields.io/badge/version-1.0.0-brightgreen.svg)

> 우아한테크코스 웹 백엔드 4기, 로또 - OOP 저장소입니다.

<img src="./lotto-operation.gif" alt="java-lotto-operation" width="400px">

<br><br>

## 코딩 전 설계

---

<img src="class diagram - 2.png" alt="lotto-operation" width="600px">

<br><br>

## 기능 구현 목록

---

### 도메인

#### LottoNumber : `int number`를 가지는 로또 숫자 값 객체

- [X] 동등성 검사를 위한 equals & hashCode 재정의
- [X] 유효성 검사
    - [X] 1 ~ 45 사이의 정수값으로 생성

#### Lotto : 길이가 6인 `List<LottoNumber>` 를 가진 일급 컬렉션

- [X] 다른 Lotto 와 비교하여 일치하는 LottoNumber 의 개수를 반환한다.
- [X] 유효성 검사
    - [X] 생성자에 전달된 List<Lotto> 의 길이가 6인지 검사
    - [X] 생성자에서 전달받은 List 에 중복되는 LottoNumber 가 포함되어있는지 검사

#### Lottos : 길이가 1 이상인 `List<Lotto>` 를 가진 일급 컬렉션

- [X] WinningLotto 를 전달 받아, 당첨 결과를 반환
- [X] 유효성 검사
    - [X] 비어있는 Lotto 가 전달 되었는지 검사

#### WinningLotto : 당첨 번호, 보너스 번호를 각각 `Lotto`, `LottoNumber` 로 가지고 있는 객체

- [X] 유효성 검사
    - [X] BonusNumber 가 Lotto 에 포함되어 있는지 검사

#### InputMoney : 사용자가 입력한 구매 희망 금액을 `int money` 로 가진 값 객체

- [X] 유효성 검사
    - [X] 1000 미만 입력 시 IAE 발생
    - [X] 1000으로 나누어 떨어지지 않는 금액 입력 시 IAE 발생

#### RandomLottoNumberGenerateStrategy

- [X] 랜덤 번호 7개 생성

#### TrialNumber

- [X] 유효성 검사
    - [X] 양수가 아닌 값을 전달 받았을 경우 IAE 발생

<br>

### 입력

#### InputView : 구입금액, 당첨번호를 입력 받는 View

- [X] 로또 구입 금액을 입력한다.
    - [X] (입력값 검증) 정수가 아닌 문자열을 입력 받을 수 없다.
    - [X] (입력값 검증) 0이하의 숫자를 입력 받을 수 없다.
    - [X] (입력값 검증, 선택) 1000으로 나누어 떨어지지 않는 금액은 입력받을 수 없다.
- [X] 로또를 생성한다.
    - [X] 로또 생성 개수는 구입금액 /1000
    - [X] 랜덤 번호 6개를 생성한다
        - [X] 번호의 범위는 `1 ~ 45`
- [X] 생성된 로또 개수와 각 로또들을 출력한다.
- [X] 당첨 번호를 입력한다.
    - [X] 일반 당첨 번호를 입력한다.
        - [X] 각 번호는 `, ` 로 구분한다 (ex `1, 2, 3, 4, 5, 6`)
        - [X] (입력값 검증) 입력 숫자의 개수는 6개여야 한다.
        - [X] (입력값 검증) 정수가 아닌 문자열을 입력 받을 수 없다.
        - [X] (입력 값 검증) 0이하 46이상의 숫자를 입력 받을 수 없다.
        - [X] (입력값 검증) 중복된 숫자가 없어야한다.
    - [X] 보너스 볼 번호를 입력한다.
        - [X] (입력값 검증) 정수가 아닌 문자열을 입력 받을 수 없다.
        - [X] (입력값 검증) 0이하 46이상의 숫자를 입력 받을 수 없다.
        - [X] (입력값 검증) 일반 당첨 번호에 존재하는 번호는 입력받을 수 없다.

<br>

### 출력

#### ResultView : `LottoResultDto` 를 전달받아 당첨 결과를 출력하는 View

- [X] 총 수익률을 (`당첨금액/로또구입금액`) 계산한다.
- [X] 당첨 통계를 출력한다.

<br><br>

# java-lotto

로또 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 구현할 기능 목록

> 구입 금액 입력 

- [ ] 구입 금액은 20억 이하여야 한다.(1000 ~ 20억)
- [ ] 1000으로 나누어 떨어져야 한다.
- [ ] 숫자여야 한다.

> 지난 주 당첨 번호 입력
- [ ] `, ` 로 입력값을 분리한다.
- [ ] 분리한 배열의 크기는 6이어야 한다.
- [ ] 각 문자열은 숫자여야 한다. 
- [ ] 각 숫자는 1~45여야 한다. (로또 번호의 조건)
- [ ] 각 숫자는 중복을 허용하지 않는다. (로또 조건)
- [ ] 오름차순으로 정렬한다.

> 보너스 볼 입력

- [ ] 지난주 당첨번호와 중복되면 안된다.
- [ ] 숫자는 1~45여야 한다.
- [ ] 숫자여야 한다.

> Object Graph

- [ ] Lottos
  - 주어진 개수만큼 로또 생성
  - 당첨 번호가 입력되면 각각의 로또 당첨 여부 확인
  - 수익율을 계산한다.
- [x] Lotto
  - 6개의 로또번호를 가진다.
  - 중복된 로또번호가 있으면 안된다.
  - 특정 숫자가 포함하는지 확인한다.
  - 랜덤한 6개 번호를 가진다.
  - 당첨 번호, 보너스 번호와 비교하여 순위를 알려준다.
- [x] 로또번호
  - 1~45의 숫자를 가진다.
- [x] 구입 금액
  - 1000원 단위의 숫자를 가진다.
  - 1000 ~ 20억 사이여야 한다.
- [x] 당첨 금액 (enum)
  - 1등 ~ 5등의 금액을 가진다.
- [x] 결과(Result)
  - [x] 당첨 결과를 당첨 순위, 개수로 저장한다.

## 페어 프로그래밍 컨벤션
- 10분 간격으로 역할을 바꾼다.
- BDD 테스트 코드 작성 패턴으로 작성한다.
- 테스트만 통과할 수 있을 정도로 TDD를 구현한다.
- 커밋 단위를 README 기준으로 한다.

# 요구 사항 정리

- 자동 로또 생성
  - 자동개수만큼 로또들 생성
- 자동 로또 출력
  - [,,,]\n
- 당첨번호 설정
  - 당첨번호 로또 생성
- 보너스볼 설정
  - 숫자만 가능
  - 당첨번호 로또와 숫자가 달라야함
- 당첨 통계 - 몇개 맞았는지 확인
  - 당첨
- 당첨 통계 출력
- 수익률 확인
- 수익률 출력



* Lottos
  * LottoFactoryMethod



# DONE

구매금액 설정

- 정수만 가능
- 1000원 이상 가능

수동구매액 설정

- 정수만 가능
- 수동구매액*1000이 구입금액액보다 작아야함

수동 몇장, 자동 몇장인지 확인

- 수동개수 : 수동금액액
- 자동개수 : (구매금액-수동구매액*1000)/1000

수동구매액만큼 수동 로또 생성

- 수동개수만큼 로또들 생성



LottoNumber

- 1 ~ 45 사이만 가능

LottoShuffleBase

- 1 ~ 45 List<LottoNumber> 가지고 있는 객체 + static (셔플시 여기서 가지고옴)

- Lotto
  - Lotto.generate(List<Integer>)
  - Lotto.create(List<LottoNumber>)
  - 6개 아니면 예외
  - FactoryMethod로 만들어버림...


# java-lotto

로또 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## step1 요구사항
- [x] 로또 구입 금액
  - (e) 1000원 단위 외의 금액 불가
  - (e) 문자열 입력
  - 구매 로또 개수 구하기
- [x] 구매한 로또 개수 출력
- [x] 로또 목록
  - 로또 생성
    - 숫자는 1 ~ 45
    - (e) 중복된 숫자 불가  
    - Collections.shuffle() 활용  
  - 목록 출력
    - 로또 숫자 정렬
- [x] 당첨 번호 입력
  - 6개 숫자 입력 (ex: 1, 2, 3)
  - 숫자는 1 ~ 45
  - 입력 구분자는 ,
  - (e) 중복된 숫자 불가
- [x] 보너스 번호 입력
  - 숫자는 1 ~ 45
  - (e) 당첨 번호와 중복 불가
- [x] 추첨
  - 숫자랑 위치 일치하는지 확인 
  - 보너스볼 확인
- [x] 추첨 결과 출력
  - 수익률 출력 (수익률 = 당첨 금액 /구입 금액)
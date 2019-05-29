# java-lotto
1. LottoNumber
    - 로또 번호 하나를 저장 (int)
    - 1 ~ 45의 사이의 정수
2. LottoNumbers
    - 6개의 로또 번호만 입력 가능
    - 중복 불가능
    - 다른 로또 번호들과 비교해서 일치 개수 반환
3. LottoNumbersGenerator
    - 1 ~ 45까지의 로또 번호를 미리 저장해 놓고 재사용
    - Shuffle을 활용해서 List의 앞에서부터 6개의 랜덤한 로또 번호로 LottoNumbers를 만들어 반환
4. Lotto
    - LottoNumbers 저장
    - 다른 로또와 비교해서 일치 개수를 반환(LottoNumbers에 메세지 전달)
5. Lottos
    - Lotto들의 리스트
    - LottoNumbers 입력받으면 해당 번호로 로또를 생성해 추가
6. LottoMachine
    - 금액 입력 시 최대 구매 가능 개수 판단
    - 최대 구매 가능 개수 만큼 Lottos 반환
    - 예외 : 1000원 미만 입력, 최대 구매 가능 금액 1,000,000,000원 초과
7. WinningLotto
    - Lotto + 보너스 볼
    - Lotto를 상속 받는다.
8. LottoGame
    - Lottos와 WinningLotto 입력시 LottoResult 반환
9. Rank
    - 당첨 종류를 나타내는 enum
    - 일치 개수를 입력 받으면 해당하는 Rank를 반환
10. LottoResult
    - 당첨 결과 저장 (Map<WinningType, Integer>)
    - 수익률 계산

### Input
- 구입 금액
- 당첨 번호

### output
- 구입한 로또 개수 및 로또 번호들
- 당첨 통계
- 총 수익률

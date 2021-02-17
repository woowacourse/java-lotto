# todo
- LottoNumber implements Comparable 한 다음에 LottoTicket에서 오름차순으로 sort하기
- LottoMatchType 관련 함수 LottoMatchType 클래스 내부로 이동.
- PurchasedLottoTickets size() 디미터 법칙 적용.
- InputView 보너스번호인지 세팅.


# done
- LottoTicket 에서 allNumbers, shuffle 분리. 일급컬렉션으로.
- LottoGenerator new ArrayList()<>; 생성자 내부에서 초기화.
- LottoGenerator.generatePurchasedTickets() 에서 List<LottoTicket> -> PurchasedLottoTickets로 변경.
- LottoGenerator 생성자 수정.
- LottoComparator 함수 수정.
- LottoComparator의 getLottoResult List<LottoTicket> -> PurchasedLottoTickets.
- 지난 주 당첨 번호를 입력해 주세요. 위에 한 줄 띄우기.
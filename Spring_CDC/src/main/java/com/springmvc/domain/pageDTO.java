package com.springmvc.domain;

public class pageDTO {
	private int startpage;		//시작 페이지
	private int endpage;		//끝 페이지
	private boolean prev, next;	//이전 페이지, 다음 페이지 존재 유무	
	private int total;			//전체 개수
	private criteria cri;		//현재 페이지번호 , 한 페이지에 표시할 데이터 정보
	
	public pageDTO(criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		//마지막 페이지
		this.endpage = (int)(Math.ceil(cri.getPagenum()/10.0))*10;
		//시작 페이지
		this.startpage = this.endpage-9;
		//전체 마지막 페이지
		int realend = (int)(Math.ceil(total*1.0)/cri.getAmount());
		//전체 마지막 페이지(realend)가 화면에 보이는 마지막페이지(endPage)보다 작은 경우, 보이는 페이지(endPage) 값 조정 
		if(realend < this.endpage) {
			this.endpage = realend;
		}
		
		//시작 페이지 값이 1보다 큰 경우 true
		this.prev = this.startpage > 1;
		//마지막 페이지 값이 1보다 큰 경우 true
		this.next = this.endpage < realend;
		
		
	}

	public int getStartpage() {
		return startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getTotal() {
		return total;
	}

	public criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageVO [startpage=" + startpage + ", endpage=" + endpage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + "]";
	}
	
	
}

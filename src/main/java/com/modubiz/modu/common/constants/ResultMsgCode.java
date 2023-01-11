package com.modubiz.modu.common.constants;

public enum ResultMsgCode {

	// 공통 응답 메시지/코드
	OK("0000", "OK"),	// 정상처리
	SYSTEM_ERR_REQ_NET_CANCEL("9998", "SYSTEM_ERR_REQ_NET_CANCEL"),	// 시스템 오류(망취소 요청코드)
	SYSTEM_ERR("9999", "SYSTEM_ERR");	// 시스템 오류


	private String rstCd;	// 응답 코드
	private String rstMsg;	// 응답 메세지
	
	// 응답 코드 및 메세지 셋팅 (응답코드, 응답메세지)
	ResultMsgCode(String rstCd, String rstMsg) {
		this.rstCd = rstCd;
		this.rstMsg = rstMsg;
	}
	
	// 응답 코드 조회
	public String getRstCd() {
		return rstCd;
	}
	
	// 응답 메세지 조회
	public String getRstMsg() {
		return rstMsg;
	}
	
}

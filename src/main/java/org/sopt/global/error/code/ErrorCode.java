package org.sopt.global.error.code;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
	/* 400 Bad Request */

	BAD_REQUEST_DATA(HttpStatus.BAD_REQUEST, "E401", "잘못된 요청입니다"),

	/* 403 */
	BOARD_FORBIDDEN(HttpStatus.FORBIDDEN, "E403", "게시판 접근 권한이 없습니다."),

	/* 404 NOT FOUND */
	DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "E404", "데이터가 존재하지 않습니다"),

	/* 500 INTERNAL SERVER ERROR */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E500001", "서버 내부에서 오류가 발생했습니다");
	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}





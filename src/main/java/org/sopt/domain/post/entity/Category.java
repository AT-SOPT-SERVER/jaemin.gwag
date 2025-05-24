package org.sopt.domain.post.entity;

import org.sopt.global.error.code.ErrorCode;
import org.sopt.global.error.exception.BusinessException;

import lombok.Getter;

@Getter
public enum Category {
	BACKEND("백엔드"),
	DATABASE("데이터베이스"),
	INFRASTRUCTURE("인프라"),
	ETC("기타");

	private String name;

	Category(String name) {
		this.name = name;
	}

	public static Category fromName(String name) {
		for (Category category : values()) {
			if(category.name.equals(name)) {
				return category;
			}
		}
		throw new BusinessException(ErrorCode.DATA_NOT_FOUND);
	}
}

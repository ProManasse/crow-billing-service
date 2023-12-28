package com.crow.billing.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
	private String message;
	
	public MessageResponse(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageResponse [ message=" + message + "]";
	}
	
	
	
	
}

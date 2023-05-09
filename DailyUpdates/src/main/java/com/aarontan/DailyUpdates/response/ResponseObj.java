package com.aarontan.DailyUpdates.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class ResponseObj {
	private String status;
	private Object data;
	private Object error;
	
	private ResponseObj(Builder builder) {
		this.status = builder.status;
		this.data = builder.data;
		this.error = builder.error;
	}
	
	public static class Builder {
		private String status;
		private Object data;
		private Object error;
		private HttpStatus httpStatus;
		
		public Builder setStatus(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
			this.status = httpStatus.getReasonPhrase();
			return this;
		}
		
		public Builder setData(Object data) {
			this.data = data;
			return this;
		}
		
		public Builder setError(Object error) {
			this.error = error;
			return this;
		}
		
		public ResponseEntity<ResponseObj> build() {
			return new ResponseEntity<>(new ResponseObj(this), httpStatus);
		}
	}
}
package com.example.demo.Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @packageName   : kr.co.gshs.dt.admin.cmm.component
 * @fileName      : DateJsonSerializer.java
 * @author        : Kenny Kim
 * @date          : 2020.12.11 
 * @description   : 
 * ==============================================
 * DATE            AUTH            NOTE
 * ----------------------------------------------
 * 2020.12.11      Kenny Kim      최초생성
 *
 */
public class DateJsonSerializer extends JsonSerializer<Date> {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @JsonSerialize CommonVO Date 타입 String 타입으로 변환
	 */
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		String formatDate = dateFormat.format(value);
		gen.writeString(formatDate);
	}

}

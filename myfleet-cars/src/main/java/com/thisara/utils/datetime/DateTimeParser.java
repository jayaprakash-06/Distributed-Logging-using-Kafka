package com.thisara.utils.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateTimeParser {

	private Logger logger = LoggerFactory.getLogger(DateTimeParser.class);

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static LocalDate parseDate(String date) {

		LocalDate formattedDate = null;

		formattedDate = LocalDate.parse(date, formatter);

		return formattedDate;
	}

	public static ZonedDateTime parseZonedTimestamp(String timestamp, String userTimeZone) {

		ZoneId zoneId = ZoneId.of(userTimeZone);
		
		ZonedDateTime formattedTimestamp = null;
		
		LocalDateTime localDateTime = LocalDateTime.parse(timestamp, dateTimeFormatter);

		formattedTimestamp = ZonedDateTime.of(localDateTime, zoneId);
		
		return formattedTimestamp;
	}
	
	public static OffsetDateTime parseOffsetTimestamp(String timestamp, String userTimeZone) {

		ZoneId zoneId = ZoneId.of(userTimeZone);
		
		OffsetDateTime formattedTimestamp = null;
		
		LocalDateTime localDateTime = LocalDateTime.parse(timestamp, dateTimeFormatter);

		ZoneOffset zoneOffset = ZonedDateTime.of(localDateTime, zoneId).getOffset();
		
		formattedTimestamp = OffsetDateTime.of(localDateTime, zoneOffset);
		
		return formattedTimestamp;
	}
	
	public static OffsetDateTime parseDateTime(String fromDate, String fromTime, int offset) {
		
		LocalDate date = LocalDate.parse(fromDate);
		
		LocalTime time = LocalTime.parse(fromTime);
		
		OffsetDateTime offsetDateTime = OffsetDateTime.of(date, time, ZoneOffset.ofHours(offset));
		
		return offsetDateTime;
	}
}

package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilDateTimeFormatter {

	public static String formataDataParaString(LocalDateTime data) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");

		return formatter.format(data);
	}

	public static LocalDate formataDataParaLocalDate(String data) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return LocalDate.parse(data, formatter);
	}

}

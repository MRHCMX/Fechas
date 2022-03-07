import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Set;

public class Fechas {
	public static void main(String[] args) {
		System.out.println("***Obtener la fecha actual en diferentes formatos");
		Date fecAct = new Date();
		System.out.println(fecAct);
		
		//Formato de fechas
		//https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
		SimpleDateFormat sdtf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdtf2 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdtf3 = new SimpleDateFormat("HH:mm:s.S");
		SimpleDateFormat sdtf4 = new SimpleDateFormat("yyyy-MM-dd KK:mm:s.S a");
		
		System.out.println("yyyy-MM-dd " + sdtf1.format(fecAct));
		System.out.println("dd/MM/YYYY " + sdtf2.format(fecAct));
		System.out.println("HH:mm:s.S  " + sdtf3.format(fecAct));
		System.out.println("yyyy-MM-dd KK:mm:s.S a " + sdtf4.format(fecAct));
		
		//Parseo de fechas
		System.out.println("\n***Parseo de fechas (de string a date)");
		String sFechaDumm = "04/03/2022";
		System.out.println("Fecha en cadena de texto: " + sFechaDumm);
		try {
			Date dFechaDumm = sdtf2.parse(sFechaDumm);
			System.out.println("Fecha date con formato: " + sdtf2.format(dFechaDumm));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//https://currentmillis.com/
		System.out.println("\n***Parseo de fechas (de Millis a date)");
		System.out.println(System.currentTimeMillis());
		long miliDate = System.currentTimeMillis();
		
		Date lFecha = new Date();
		lFecha.setTime(miliDate);
		System.out.println(lFecha);
		System.out.println(sdtf4.format(lFecha));
		
		//Calendar
		System.out.println("\n *** Calendar");
		Calendar calendar = new GregorianCalendar();
		calendar.set(2022, 03, 03);
		System.out.println(calendar.getTime());
		System.out.println(sdtf4.format(calendar.getTime()));
		
		//Modificación de fechas en calendar
		System.out.println("\n ***");
		System.out.println(sdtf4.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		calendar.add(Calendar.HOUR, -1);
		System.out.println(sdtf4.format(calendar.getTime()));
		
		//LocalDate
		System.out.println("\n *** LocalDate");
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		
		System.out.println("\n *** Modificación de fechas LocalDate (Agregar 2 dias y quitar un mes)");
		LocalDate updlocalDate = LocalDate.parse("2020-03-03");
		System.out.println(updlocalDate);
		updlocalDate = updlocalDate.plus(2, ChronoUnit.DAYS);
		updlocalDate = updlocalDate.minus(1, ChronoUnit.MONTHS);
		System.out.println(updlocalDate);
		
		System.out.println("\n *** LocalDate calculando periodos entre fechas");
		LocalDate localDate2 = LocalDate.of(2020, 02, 02);
		LocalDate localDate3 = LocalDate.parse("2020-03-03");

		System.out.println("del " + localDate2 + " al " + localDate3);
		
		long periodo = ChronoUnit.DAYS.between(localDate2, localDate3);
		int dias = Period.between(localDate2, localDate3).getDays();
		int meses = Period.between(localDate2, localDate3).getMonths();
		int years = Period.between(localDate2, localDate3).getYears();
		
		System.out.println("ChronoUnit: " + periodo + " dias");
		System.out.println("Period: " + dias + " dias");
		System.out.println("Period: " + meses + " meses");
		System.out.println("Period: " + years + " años");
		
		//LocalTime
        System.out.println("*** LocalTime");
    	LocalTime now = LocalTime.now();
    	System.out.println(now);
    	
    	LocalTime sixThirty = LocalTime.parse("06:30");
    	System.out.println(sixThirty);

    	LocalTime sixThirty2 = LocalTime.of(6, 30);
    	System.out.println(sixThirty2);
    	
    	int six = LocalTime.parse("06:30").getHour();
    	System.out.println(six);

    	//Comparación
    	System.out.println("\n *** Comparando LocalTime");
    	boolean isbefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
    	System.out.println(isbefore);

    	System.out.println("\n *** Modificación LocalTime (Agregar 1 hora a 06:30)");
    	LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
    	System.out.println(sevenThirty);

        //LocalDateTime
    	System.out.println("\n*** LocalDateTime");
    	LocalDateTime.now();
    	System.out.println(LocalDateTime.now());
    	
    	System.out.println("\n*** LocalDateTime parse");
    	System.out.println(LocalDateTime.of(2022, Month.FEBRUARY, 20, 06, 30));
    	System.out.println(LocalDateTime.parse("2022-02-20T06:30:00"));
    	
    	System.out.println("\n *** Modificación localDateTime");
    	LocalDateTime localDateTime = LocalDateTime.now();
    	System.out.println("original: " + localDateTime);
		System.out.println("original + 1 dia: " + localDateTime.plusDays(1));
    	System.out.println("original - 2 horas: " + localDateTime.minusHours(2));
    	System.out.println("obtener mes desde original: " + localDateTime.getMonth());
    	
    	System.out.println("\n*** Duración en horas de intervalos");
    	LocalDateTime tiempo01 = LocalDateTime.parse("2022-02-20T06:30:00");
    	LocalDateTime tiempo02 = LocalDateTime.parse("2022-02-20T06:30:00").plus(1, ChronoUnit.HOURS);
    	System.out.println("Duración en horas del rango del " + tiempo01 + " al " + tiempo02);
    	System.out.println(ChronoUnit.MINUTES.between(tiempo01, tiempo02) + " minutos");
    	
    	System.out.println("\n*** Cambiando el formato en LocalDateTime");
    	LocalDateTime lDateTime = LocalDateTime.of(2022, Month.JANUARY, 25, 6, 30);
    	String localDateString = lDateTime.format(DateTimeFormatter.ISO_DATE);
    	System.out.println(lDateTime);
    	System.out.println(localDateString);
    	System.out.println(lDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    	System.out.println(lDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.UK)));
    	System.out.println(lDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.FRANCE)));
    	System.out.println(lDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.JAPANESE)));
    	
    	//Zona horaria
    	System.out.println("\n*** Zona horaria");

    	System.out.println("\nConfigura una zona horaria");
    	ZoneId zoneId = ZoneId.of("Europe/Paris");
    	System.out.println("zoneId para Europe/Paris: " + zoneId);
    	
    	Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
    	System.out.println("Lista zonas horarias");
    	for (String allZoneId:allZoneIds)
    	{
    		System.out.println(allZoneId);
    	}

    	System.out.println("\nAsigna una zona horaria a una fecha");
    	LocalDateTime localDateTime3 = LocalDateTime.now();
    	ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime3, zoneId);
    	System.out.println(localDateTime3);
    	System.out.println(zonedDateTime);

    	System.out.println("\nParseando fecha y zona horaria");
    	ZonedDateTime zonedDateTime2 = ZonedDateTime.parse("2021-05-03T10:15:30+01:00[US/Pacific]");
    	System.out.println(zonedDateTime2);

    	System.out.println("\nParseando fecha y defase");
    	LocalDateTime localDateTimeZone = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
    	System.out.println(localDateTimeZone);
    	ZoneOffset offset = ZoneOffset.of("-05:00");  //zona horaria de México (America/Mexico_City)
    	OffsetDateTime offSetByTwo = OffsetDateTime.of(localDateTimeZone, offset);
    	System.out.println(offSetByTwo);
	}
}

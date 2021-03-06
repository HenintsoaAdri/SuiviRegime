package s6.suiviRegime.utilitaire;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {
	private DateUtil(){}
	private static class Holder
	{		
		private final static DateUtil instance = new DateUtil();
	}
	public static DateUtil getInstance(){
		return Holder.instance;
	}
	
	public LocalTime stringToTime(String heure, String minute){
    	return LocalTime.of(Integer.valueOf(heure), Integer.valueOf(minute));
    }
    public String formatISO(String iso){
    	return formatDateTime(LocalDateTime.parse(iso, DateTimeFormatter.RFC_1123_DATE_TIME));
    }
    public String formatDateTime(LocalDateTime local){
    	return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(local.toLocalDate())+" \u00e0 "+
    			DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(local.toLocalTime());
    }
    public Date stringToDate(String date) throws Exception{
    	DateTimeFormatter format = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("uuuu-MM-d").toFormatter(Locale.FRANCE);
    	try{
			return java.sql.Date.valueOf(LocalDate.parse(date, format));
		} catch (DateTimeParseException e) {
			try{
				format = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d-MM-uuuu").toFormatter(Locale.FRANCE);
				return java.sql.Date.valueOf(LocalDate.parse(date, format));
			}catch(Exception e1){
				e1.printStackTrace();
				throw new Exception("Format de date non support\u00e9");				
			}
		}
    }
    public String LocalDateToString(LocalDate date){
    	return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(date);
    }
    public String DateTimeToString(Date date){
    	return DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.FRANCE).format(date);
    }
    public String DateToString(Date date){
    	return DateFormat.getDateInstance(DateFormat.FULL, Locale.FRANCE).format(date);
    }
    
    public int getBetweenNow(Date compare, Date date){
    	long now = Calendar.getInstance().getTime().getTime();
    	if(compare != null)now = Math.max(now, compare.getTime());
		return (int)Math.max(0,TimeUnit.MILLISECONDS.toDays(date.getTime() - now));
    }

    public int getUntilNow(Date date){
    	long now = Calendar.getInstance().getTime().getTime();
		return (int)Math.max(0,TimeUnit.MILLISECONDS.toDays(now - date.getTime()));
    }
}

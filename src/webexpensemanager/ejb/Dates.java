package webexpensemanager.ejb;

import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Stateless
public class Dates {

	public SimpleDateFormat dateHTML = new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat dateRUS = new SimpleDateFormat("dd.MM.yyyy");
	public SimpleDateFormat dateHTMLinput = new SimpleDateFormat("MM/dd/yyyy");
	public SimpleDateFormat dateSQL = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

	public Date now(){
		return new  Date();
	}

    public Date early(){
		return new Date(0);
	}

    public Date beginningOf(String str){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		switch(str){
			case "Year" :   calendar.set(Calendar.MONTH, Calendar.JANUARY);
			case "Month":   calendar.set(Calendar.DATE, 1);
			case "Day"  :   calendar.set(Calendar.HOUR, -12);
							calendar.set(Calendar.MINUTE, 0);
							calendar.set(Calendar.SECOND, 0);
							break;
			case "Week" :   int dayOfWeek = Calendar.DAY_OF_WEEK;
							calendar.add(Calendar.DATE, -1 * dayOfWeek + 1);
							calendar.set(Calendar.HOUR, 0);
							calendar.set(Calendar.MINUTE, 0);
							calendar.set(Calendar.SECOND, 0);
							break;
			default     :   break;
		}
		Date result = calendar.getTime();
		result = new Date(result.getTime() - 1000);
		return result;
	}

    public String todayHTML(){
		Date today = now();
		return dateHTMLinput.format(today);
	}
}

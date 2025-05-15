package logs;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FormatoHTML extends Formatter {
	

	  @Override
	    public String format(LogRecord record) {
	        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
	                           .format(new Date(record.getMillis()));
	        return fecha + " - " + record.getLevel() + " - " + record.getMessage() + "<br>\n";
	    }


}
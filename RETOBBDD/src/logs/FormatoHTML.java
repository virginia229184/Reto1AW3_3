package logs;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Los loggers crearan un fichero HTML
 * 
 * @author 1AW3-17 FELIPE VIRGINIA JON
 * @version 05.19.2025
 */
public class FormatoHTML extends Formatter {
	

	  @Override
	    public String format(LogRecord record) {
	        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
	                           .format(new Date(record.getMillis()));
	        return fecha + " - " + record.getLevel() + " - " + record.getMessage() + "<br>\n";
	    }


}

/**
 * Paquete que da formato al HTML de registros de log,

 
 */
package logs;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Clase FormatoHTML, sirve para dar formato al html donde se registran los logs
 * @author FELIPE 
 * @author VIRGINIA 
 * @author JON
 * @version 1.0
 */
public class FormatoHTML extends Formatter {
	
	/**
	 * constructor vacio
	 */
	
	public FormatoHTML() {
		
	}

	/**
	 * devuelve cada mensaje de log con fecha, nivel, mensaje
	 */

	@Override
	public String format(LogRecord record) {
		String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(record.getMillis()));
		return fecha + " - " + record.getLevel() + " - " + record.getMessage() + "<br>\n";
	}

}
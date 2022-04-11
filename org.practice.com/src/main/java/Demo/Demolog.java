package Demo;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Demolog {
	
 static final Logger Logger=LogManager.getLogger(Demolog.class.getName());
public static void log(int a) 
{
	if(a==1) {
		Logger.trace("Tracking");
	}
	else if(a==2) {
		Logger.info("Information");
}
	else if(a==3) {
		Logger.error("Error message");
	}
	else if(a==4) {
		Logger.warn("Warning message");
	}
	else if(a==6) {
		Logger.fatal("fatal error");
	}
}
}

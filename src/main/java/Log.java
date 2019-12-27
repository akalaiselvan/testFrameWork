import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class Log{
    private static final Logger logger= LogManager.getLogger(Log.class.getName());

    @Test
    public void tests (){
        System.out.println("Trying to write logs");
        logger.debug("this is debugging");
        logger.info("y=this is info");
        logger.error("this is error");
        logger.warn("this is warn");
        logger.fatal("thia is fatel");
        logger.trace("this is trace");
    }
}
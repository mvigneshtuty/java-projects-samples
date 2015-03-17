
package org.codehowpedia.log4j;

import org.apache.log4j.Logger;

public class HelloLog4j {

    final static Logger lgr = Logger.getLogger(HelloLog4j.class);
    public static void main(String[] args) {
        lgr.debug("This is DEBUG log");
        lgr.info("This is INFO log");
        lgr.warn("This is WARN log");
        lgr.error("This is ERROR log");
        lgr.fatal("This is FATAL log");
    }

}

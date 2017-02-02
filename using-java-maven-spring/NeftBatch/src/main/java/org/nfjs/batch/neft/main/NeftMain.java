/*
 * Copyright - StarAlliance GmbH
 */
package org.nfjs.batch.neft.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NeftMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("neft_batch.xml");

    }

}

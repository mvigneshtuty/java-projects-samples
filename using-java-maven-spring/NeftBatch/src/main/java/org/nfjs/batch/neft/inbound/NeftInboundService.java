/*
 * Copyright - StarAlliance GmbH
 */
package org.nfjs.batch.neft.inbound;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

public class NeftInboundService {
    private Log _logger = LogFactory.getLog(getClass());

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job neftJob;

    @ServiceActivator
    public void neftInboundJobLauncher(Message<File> message) {
        String filePath = message.getPayload().getAbsolutePath();
        _logger.info(filePath);
        JobParametersBuilder jpb = new JobParametersBuilder();
        jpb.addString("neft.input.filepath", filePath);
        jpb.addString("neft.output.filepath",
                "D:\\353453\\learning\\spring_batch\\neft\\outbound\\neft_inbound_test_01.txt");
        try {
            jobLauncher.run(neftJob, jpb.toJobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JobRestartException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

/*
 * Copyright - StarAlliance GmbH
 */
package org.nfjs.batch.neft.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component("neftJobListener")
public class NeftJobListener implements JobExecutionListener {

    private Log _logger = LogFactory.getLog(getClass());

    public void beforeJob(JobExecution jobExecution) {
        _logger.info("Starting Neft job..."
                + jobExecution.getJobInstance().getJobName());
    }

    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            _logger.info("Neft job completed successfully..."
                    + jobExecution.getJobInstance().getJobName());
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            _logger.info("Neft job failure..."
                    + jobExecution.getJobInstance().getJobName());
        } else {
            _logger.info("Neft job execution error...Needs attention"
                    + jobExecution.getJobInstance().getJobName());
        }

    }

}

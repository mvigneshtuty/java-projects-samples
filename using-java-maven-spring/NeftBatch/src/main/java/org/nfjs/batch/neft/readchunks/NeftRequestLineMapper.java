/*
 * Copyright - StarAlliance GmbH
 */
package org.nfjs.batch.neft.readchunks;

import org.nfjs.batch.neft.domain.NeftRecordData;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;

public class NeftRequestLineMapper implements LineMapper<NeftRecordData> {

    DelimitedLineTokenizer neftRecordTokenizer;
    FieldSetMapper<NeftRecordData> neftFieldSetMapper;

    public NeftRecordData mapLine(String line, int lineNumber)
            throws Exception {
        FieldSet fieldSet = neftRecordTokenizer.tokenize(line);
        return neftFieldSetMapper.mapFieldSet(fieldSet);
    }

    public void setNeftRecordTokenizer(
            DelimitedLineTokenizer neftRecordTokenizer) {
        this.neftRecordTokenizer = neftRecordTokenizer;
    }

    public void setNeftFieldSetMapper(
            FieldSetMapper<NeftRecordData> neftFieldSetMapper) {
        this.neftFieldSetMapper = neftFieldSetMapper;
    }

}

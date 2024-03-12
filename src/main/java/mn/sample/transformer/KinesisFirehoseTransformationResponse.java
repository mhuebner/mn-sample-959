/*
 * Copyright 2012-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package mn.sample.transformer;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.io.Serial;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Response model for Kinesis Analytics Preprocessing Lambda function.
 */
@Introspected
@Serdeable.Serializable
public class KinesisFirehoseTransformationResponse implements Serializable {

    public enum Result {

        /**
         * Indicates that processing of this item succeeded.
         */
        Ok,

        /**
         * Indicate that the processing of this item failed
         */
        ProcessingFailed,

        /**
         * Indicates that this item should be silently dropped
         */
        Dropped
    }

    @Serial
    private static final long serialVersionUID = -4651154757825854471L;
    private List<Record> records;

    public KinesisFirehoseTransformationResponse() {
        super();
    }

    @JsonCreator
    public KinesisFirehoseTransformationResponse(List<Record> records) {
        super();
        this.records = records;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Introspected
    @Serdeable.Serializable
    public static class Record implements FirehoseRecord, Serializable {
        @Serial
        private static final long serialVersionUID = -1583558686451236985L;
        private String recordId;
        private Result result;

        public Record() {
            super();
        }

        @JsonCreator
        public Record(String recordId, Result result, ByteBuffer data) {
            super();
            this.recordId = recordId;
            this.result = result;
            this.data = data;
        }

        public ByteBuffer data;

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public ByteBuffer getData() {
            return data;
        }

        public void setData(ByteBuffer data) {
            this.data = data;
        }
    }
}

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
 * Event model for pre-processing Kinesis Firehose records through Kinesis
 * Analytics Lambda pre-processing function.
 */
@Introspected
@Serdeable.Deserializable
public class KinesisFirehoseTransformationInput implements Serializable {
    @Serial
    private static final long serialVersionUID = 3372554211277515302L;
    private String invocationId;
    private String applicationArn;
    private String streamArn;
    private List<Record> records;

    public KinesisFirehoseTransformationInput() {
    }

    @JsonCreator
    public KinesisFirehoseTransformationInput(String invocationId, String applicationArn, String streamArn,
                                              List<Record> records) {
        super();
        this.invocationId = invocationId;
        this.applicationArn = applicationArn;
        this.streamArn = streamArn;
        this.records = records;
    }

    public String getInvocationId() {
        return invocationId;
    }

    public void setInvocationId(String invocationId) {
        this.invocationId = invocationId;
    }

    public String getApplicationArn() {
        return applicationArn;
    }

    public void setApplicationArn(String applicationArn) {
        this.applicationArn = applicationArn;
    }

    public String getStreamArn() {
        return streamArn;
    }

    public void setStreamArn(String streamArn) {
        this.streamArn = streamArn;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Introspected
    @Serdeable.Deserializable
    public static class Record implements FirehoseRecord, Serializable {
        @Serial
        private static final long serialVersionUID = 9130920004800315787L;
        private String recordId;
        private KinesisFirehoseRecordMetadata kinesisFirehoseRecordMetadata;
        private ByteBuffer data;

        public Record() {
        }

        @JsonCreator
        public Record(String recordId, KinesisFirehoseRecordMetadata kinesisFirehoseRecordMetadata, ByteBuffer data) {
            super();
            this.recordId = recordId;
            this.kinesisFirehoseRecordMetadata = kinesisFirehoseRecordMetadata;
            this.data = data;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public ByteBuffer getData() {
            return data;
        }

        public void setData(ByteBuffer data) {
            this.data = data;
        }

        public KinesisFirehoseRecordMetadata getKinesisFirehoseRecordMetadata() {
            return kinesisFirehoseRecordMetadata;
        }

        public void setKinesisFirehoseRecordMetadata(KinesisFirehoseRecordMetadata kinesisFirehoseRecordMetadata) {
            this.kinesisFirehoseRecordMetadata = kinesisFirehoseRecordMetadata;
        }

        @Serdeable.Deserializable
        public static class KinesisFirehoseRecordMetadata implements Serializable {
            @Serial
            private static final long serialVersionUID = 692430771749481045L;
            private Long approximateArrivalTimestamp;

            public KinesisFirehoseRecordMetadata() {
            }

            @JsonCreator
            public KinesisFirehoseRecordMetadata(Long approximateArrivalTimestamp) {
                super();
                this.approximateArrivalTimestamp = approximateArrivalTimestamp;
            }

            public Long getApproximateArrivalTimestamp() {
                return approximateArrivalTimestamp;
            }

            public void setApproximateArrivalTimestamp(Long approximateArrivalTimestamp) {
                this.approximateArrivalTimestamp = approximateArrivalTimestamp;
            }
        }
    }
}

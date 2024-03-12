package mn.sample.transformer;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Introspected
public class EventRequestHandler extends MicronautRequestHandler<KinesisFirehoseTransformationInput, KinesisFirehoseTransformationResponse> {

    @Override
    public KinesisFirehoseTransformationResponse execute(KinesisFirehoseTransformationInput firehoseEvent) {
        ByteBuffer incomingData = firehoseEvent.getRecords().getFirst().getData();

        // sample app - just respond ... with what came in.
        List<KinesisFirehoseTransformationResponse.Record> responseRecords = new ArrayList<>();
        responseRecords.add(new KinesisFirehoseTransformationResponse.Record(UUID.randomUUID().toString(), KinesisFirehoseTransformationResponse.Result.Ok, incomingData));

        return new KinesisFirehoseTransformationResponse(responseRecords);
    }
}

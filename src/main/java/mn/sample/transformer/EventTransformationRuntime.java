package mn.sample.transformer;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.function.aws.runtime.AbstractMicronautLambdaRuntime;

import java.net.MalformedURLException;

public class EventTransformationRuntime extends AbstractMicronautLambdaRuntime<KinesisFirehoseTransformationInput, KinesisFirehoseTransformationResponse, KinesisFirehoseTransformationInput, KinesisFirehoseTransformationResponse> {

    public static void main(String[] args) {
        try {
            new EventTransformationRuntime().run(args);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Nullable
    protected RequestHandler<KinesisFirehoseTransformationInput, KinesisFirehoseTransformationResponse> createRequestHandler(String... args) {
        return new EventRequestHandler();
    }
}

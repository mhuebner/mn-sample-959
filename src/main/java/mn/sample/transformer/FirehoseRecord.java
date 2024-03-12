package mn.sample.transformer;

import java.nio.ByteBuffer;

public interface FirehoseRecord {

    public ByteBuffer getData();

    public void setData(ByteBuffer data);

}

package com.tianzh.cm.network.protocol;

import com.tianzh.cm.network.protocol.decoder.Decoder;
import com.tianzh.cm.network.protocol.encoder.Encoder;

/**
 * Author: cyc
 * Date: 12-4-6
 * Time: 下午2:06
 * Description: to write something
 */
public class KvCodecFactory implements CodecFactory {
    private Decoder decoder;
    private Encoder encoder;

    public KvCodecFactory(Decoder decoder, Encoder encoder) {
        this.decoder = decoder;
        this.encoder = encoder;
    }

    @Override
    public Object decode(Object o) throws Exception {
        return decoder.decode(o);
    }

    @Override
    public Object encode(Object o) throws Exception {
        return encoder.encode(o);
    }
}

package com.wavemaker.tutorial.fileUtil.Serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by srujant on 22/2/17.
 */
public class ByteArrayToStringSerializer extends JsonSerializer<byte[]> {
    public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(new String(value));
    }
}

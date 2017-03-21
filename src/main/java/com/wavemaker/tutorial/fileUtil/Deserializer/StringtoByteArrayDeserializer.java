package com.wavemaker.tutorial.fileUtil.Deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Created by srujant on 22/2/17.
 */
public class StringtoByteArrayDeserializer extends JsonDeserializer<byte[]> {

    public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return p.getValueAsString().getBytes();
    }
}

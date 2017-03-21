package com.wavemaker.tutorial.fileUtil.model;

import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavemaker.tutorial.fileUtil.Deserializer.StringtoByteArrayDeserializer;
import com.wavemaker.tutorial.fileUtil.Serializer.ByteArrayToStringSerializer;

/**
 * Created by srujant on 22/2/17.
 */
public class Employee {

    @JsonSerialize(using = ByteArrayToStringSerializer.class)
    @JsonDeserialize(using = StringtoByteArrayDeserializer.class)
    private byte[] name;
    private int id;


    public byte[] getName() {
        return name;
    }

    public void setName(byte[] name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name=" + Arrays.toString(name) +
                ", id=" + id +
                '}';
    }
}

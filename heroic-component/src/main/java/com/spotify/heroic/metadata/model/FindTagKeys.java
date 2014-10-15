package com.spotify.heroic.metadata.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class FindTagKeys {
    public static final FindTagKeys EMPTY = new FindTagKeys(new HashSet<String>(), 0);

    private final Set<String> keys;
    private final int size;

    @JsonCreator
    public static FindTagKeys create(@JsonProperty("keys") Set<String> keys, @JsonProperty("size") int size) {
        return new FindTagKeys(keys, size);
    }
}
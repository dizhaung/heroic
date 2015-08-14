package com.spotify.heroic;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.spotify.heroic.profile.GeneratedProfile;
import com.spotify.heroic.profile.MemoryProfile;

public class HeroicModules {
    // @formatter:off
    public static final List<Class<?>> ALL_MODULES = ImmutableList.<Class<?>>of(
        com.spotify.heroic.metric.astyanax.Entry.class,
        com.spotify.heroic.metric.datastax.Entry.class,
        com.spotify.heroic.metric.generated.Entry.class,
        com.spotify.heroic.metric.bigtable.Entry.class,

        com.spotify.heroic.metadata.elasticsearch.Entry.class,
        com.spotify.heroic.suggest.elasticsearch.Entry.class,
        // com.spotify.heroic.suggest.lucene.Entry.class,

        com.spotify.heroic.cluster.discovery.simple.Entry.class,

        com.spotify.heroic.aggregation.simple.Entry.class,

        com.spotify.heroic.consumer.kafka.Entry.class,

        com.spotify.heroic.aggregationcache.cassandra2.Entry.class,

        com.spotify.heroic.rpc.httprpc.Entry.class,
        com.spotify.heroic.rpc.nativerpc.Entry.class
    );

    public static final Map<String, HeroicProfile> PROFILES = ImmutableMap.<String, HeroicProfile>builder()
        .put("generated", new GeneratedProfile())
        .put("memory", new MemoryProfile())
    .build();
    // @formatter:on
}
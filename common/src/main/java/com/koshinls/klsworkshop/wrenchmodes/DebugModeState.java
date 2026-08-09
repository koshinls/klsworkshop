package com.koshinls.klsworkshop.wrenchmodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public record DebugModeState(
        Map<ResourceLocation, String> selectedProperties
) {

    public static final DebugModeState EMPTY =
            new DebugModeState(new HashMap<>());

    public static final Codec<DebugModeState> CODEC =
            RecordCodecBuilder.create(instance ->
                    instance.group(
                            Codec.unboundedMap(
                                            ResourceLocation.CODEC,
                                            Codec.STRING
                                    )
                                    .fieldOf("selected_properties")
                                    .forGetter(DebugModeState::selectedProperties)
                    ).apply(instance, DebugModeState::new)
            );

    public static final StreamCodec<
            RegistryFriendlyByteBuf,
            DebugModeState
            > STREAM_CODEC =
            ByteBufCodecs.fromCodecWithRegistries(CODEC);

    public String get(ResourceLocation block) {
        return selectedProperties.get(block);
    }

    public DebugModeState with(
            ResourceLocation block,
            String property
    ) {

        Map<ResourceLocation, String> map =
                new HashMap<>(selectedProperties);

        map.put(block, property);

        return new DebugModeState(map);
    }
}
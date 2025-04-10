package nl.utwente.sosoc.identitymanagement.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.DestinationSetter;

import java.util.function.Function;

public class EntityMapper {

    public <T, R> Function<T, R> to(Class<R> clazz) {
        return t -> new ModelMapper().map(t, clazz);
    }

    @SafeVarargs
    public final <T, R> Function<T, R> to(Class<R> clazz, DestinationSetter<R, ?>... exclude) {
        return t -> new ModelMapper().createTypeMap(t, clazz)
            .addMappings(mapper -> {
                for (DestinationSetter<R, ?> destinationSetter : exclude) {
                    mapper.skip(destinationSetter);
                }
            })
            .map(t);
    }

}

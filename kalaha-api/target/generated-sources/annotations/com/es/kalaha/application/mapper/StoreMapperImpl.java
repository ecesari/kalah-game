package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.StoreDto;
import com.es.kalaha.domain.entities.Pit;
import com.es.kalaha.domain.entities.Player;
import com.es.kalaha.domain.entities.Store;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-17T13:51:50+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public StoreDto fromStore(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDto storeDto = new StoreDto();

        storeDto.setNextId( storeNextId( store ) );
        storeDto.setOwnerId( storeOwnerId( store ) );
        storeDto.setId( store.getId() );
        storeDto.setSeeds( store.getSeeds() );

        return storeDto;
    }

    private UUID storeNextId(Store store) {
        Pit next = store.getNext();
        if ( next == null ) {
            return null;
        }
        return next.getId();
    }

    private UUID storeOwnerId(Store store) {
        Player owner = store.getOwner();
        if ( owner == null ) {
            return null;
        }
        return owner.getId();
    }
}

package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.HouseDto;
import com.es.kalaha.domain.entities.House;
import com.es.kalaha.domain.entities.Pit;
import com.es.kalaha.domain.entities.Player;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-17T13:51:50+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class HouseMapperImpl implements HouseMapper {

    @Override
    public House mapFromHouse(HouseDto dto) {
        if ( dto == null ) {
            return null;
        }

        House house = new House();

        house.setId( dto.id );
        house.setSeeds( dto.seeds );

        return house;
    }

    @Override
    public HouseDto mapToHouse(House entity) {
        if ( entity == null ) {
            return null;
        }

        HouseDto houseDto = new HouseDto();

        houseDto.nextId = entityNextId( entity );
        houseDto.ownerId = entityOwnerId( entity );
        houseDto.oppositeId = HouseMapper.getIdFromHouse( entity.getOpposite() );
        houseDto.id = entity.getId();
        houseDto.seeds = entity.getSeeds();

        return houseDto;
    }

    private UUID entityNextId(House house) {
        Pit next = house.getNext();
        if ( next == null ) {
            return null;
        }
        return next.getId();
    }

    private UUID entityOwnerId(House house) {
        Player owner = house.getOwner();
        if ( owner == null ) {
            return null;
        }
        return owner.getId();
    }
}

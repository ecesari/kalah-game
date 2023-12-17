package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.HouseDto;
import com.es.kalaha.domain.entities.House;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface HouseMapper {
    House mapFromHouse(HouseDto dto);

    @Mapping(target="nextId", source = "next.id")
    @Mapping(target="ownerId", source = "owner.id")
    @Mapping(target="oppositeId", source = "opposite", qualifiedByName = "getIdFromHouse")
    HouseDto mapToHouse(House entity);

    @Named("getIdFromHouse")
    public static UUID getIdFromHouse(Optional<House> house) {
        var houseEntity = house.get();
        return houseEntity.id;
    }
}

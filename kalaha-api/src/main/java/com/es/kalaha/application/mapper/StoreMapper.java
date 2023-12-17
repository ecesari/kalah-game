package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.StoreDto;
import com.es.kalaha.domain.entities.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreMapper {
//    Store toStore (StoreDto storeDto);

    @Mapping(target="nextId", source = "next.id")
    @Mapping(target="ownerId", source = "owner.id")
    StoreDto fromStore (Store store);

}

package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.PlayerDto;
import com.es.kalaha.domain.entities.Player;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper( PlayerMapper.class );

    Player toPlayer (PlayerDto playerDto);

    PlayerDto fromPlayer (Player player);
}

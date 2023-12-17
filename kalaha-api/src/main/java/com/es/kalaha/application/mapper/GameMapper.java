package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.BoardDto;
import com.es.kalaha.application.dto.GameDto;
import com.es.kalaha.domain.entities.Board;
import com.es.kalaha.domain.entities.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {HouseMapper.class,StoreMapper.class})
public interface GameMapper {
    Game mapToGame(GameDto gameDto);


    GameDto mapFromGame(Game game);

}

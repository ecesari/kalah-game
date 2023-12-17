package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.BoardDto;
import com.es.kalaha.domain.entities.Board;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {HouseMapper.class,StoreMapper.class})
public interface BoardMapper {
    Board mapToBoard(BoardDto boardDto);


    BoardDto mapFromBoard(Board board);

}

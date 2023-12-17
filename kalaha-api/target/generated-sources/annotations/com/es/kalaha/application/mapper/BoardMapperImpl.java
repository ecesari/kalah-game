package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.BoardDto;
import com.es.kalaha.application.dto.HouseDto;
import com.es.kalaha.application.dto.StoreDto;
import com.es.kalaha.domain.entities.Board;
import com.es.kalaha.domain.entities.House;
import com.es.kalaha.domain.entities.Store;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-17T13:51:51+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public Board mapToBoard(BoardDto boardDto) {
        if ( boardDto == null ) {
            return null;
        }

        Board board = new Board();

        board.setId( boardDto.id );
        board.setHouses( houseDtoListToHouseList( boardDto.houses ) );
        board.setStores( storeDtoListToStoreList( boardDto.stores ) );

        return board;
    }

    @Override
    public BoardDto mapFromBoard(Board board) {
        if ( board == null ) {
            return null;
        }

        BoardDto boardDto = new BoardDto();

        boardDto.houses = houseListToHouseDtoList( board.getHouses() );
        boardDto.stores = storeListToStoreDtoList( board.getStores() );
        boardDto.id = board.getId();

        return boardDto;
    }

    protected List<House> houseDtoListToHouseList(List<HouseDto> list) {
        if ( list == null ) {
            return null;
        }

        List<House> list1 = new ArrayList<House>( list.size() );
        for ( HouseDto houseDto : list ) {
            list1.add( houseMapper.mapFromHouse( houseDto ) );
        }

        return list1;
    }

    protected Store storeDtoToStore(StoreDto storeDto) {
        if ( storeDto == null ) {
            return null;
        }

        Store store = new Store();

        store.setSeeds( storeDto.getSeeds() );
        store.setId( storeDto.getId() );

        return store;
    }

    protected List<Store> storeDtoListToStoreList(List<StoreDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Store> list1 = new ArrayList<Store>( list.size() );
        for ( StoreDto storeDto : list ) {
            list1.add( storeDtoToStore( storeDto ) );
        }

        return list1;
    }

    protected List<HouseDto> houseListToHouseDtoList(List<House> list) {
        if ( list == null ) {
            return null;
        }

        List<HouseDto> list1 = new ArrayList<HouseDto>( list.size() );
        for ( House house : list ) {
            list1.add( houseMapper.mapToHouse( house ) );
        }

        return list1;
    }

    protected List<StoreDto> storeListToStoreDtoList(List<Store> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreDto> list1 = new ArrayList<StoreDto>( list.size() );
        for ( Store store : list ) {
            list1.add( storeMapper.fromStore( store ) );
        }

        return list1;
    }
}

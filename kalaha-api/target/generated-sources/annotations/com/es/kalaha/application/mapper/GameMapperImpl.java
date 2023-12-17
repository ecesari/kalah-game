package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.BoardDto;
import com.es.kalaha.application.dto.GameDto;
import com.es.kalaha.application.dto.HouseDto;
import com.es.kalaha.application.dto.PlayerDto;
import com.es.kalaha.application.dto.StoreDto;
import com.es.kalaha.domain.entities.Board;
import com.es.kalaha.domain.entities.Game;
import com.es.kalaha.domain.entities.House;
import com.es.kalaha.domain.entities.Player;
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
public class GameMapperImpl implements GameMapper {

    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public Game mapToGame(GameDto gameDto) {
        if ( gameDto == null ) {
            return null;
        }

        Game game = new Game();

        game.setId( gameDto.getId() );
        game.setPlayers( playerDtoListToPlayerList( gameDto.getPlayers() ) );
        game.setBoard( boardDtoToBoard( gameDto.getBoard() ) );
        game.setGameStatus( gameDto.getGameStatus() );
        game.setCurrentPlayer( playerDtoToPlayer( gameDto.getCurrentPlayer() ) );

        return game;
    }

    @Override
    public GameDto mapFromGame(Game game) {
        if ( game == null ) {
            return null;
        }

        GameDto gameDto = new GameDto();

        gameDto.setId( game.getId() );
        gameDto.setPlayers( playerListToPlayerDtoList( game.getPlayers() ) );
        gameDto.setBoard( boardToBoardDto( game.getBoard() ) );
        gameDto.setGameStatus( game.getGameStatus() );
        gameDto.setCurrentPlayer( playerToPlayerDto( game.getCurrentPlayer() ) );

        return gameDto;
    }

    protected Player playerDtoToPlayer(PlayerDto playerDto) {
        if ( playerDto == null ) {
            return null;
        }

        Player player = new Player();

        player.setId( playerDto.getId() );
        player.setName( playerDto.getName() );

        return player;
    }

    protected List<Player> playerDtoListToPlayerList(List<PlayerDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Player> list1 = new ArrayList<Player>( list.size() );
        for ( PlayerDto playerDto : list ) {
            list1.add( playerDtoToPlayer( playerDto ) );
        }

        return list1;
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

    protected Board boardDtoToBoard(BoardDto boardDto) {
        if ( boardDto == null ) {
            return null;
        }

        Board board = new Board();

        board.setId( boardDto.id );
        board.setHouses( houseDtoListToHouseList( boardDto.houses ) );
        board.setStores( storeDtoListToStoreList( boardDto.stores ) );

        return board;
    }

    protected PlayerDto playerToPlayerDto(Player player) {
        if ( player == null ) {
            return null;
        }

        PlayerDto playerDto = new PlayerDto();

        playerDto.setName( player.getName() );
        playerDto.setId( player.getId() );

        return playerDto;
    }

    protected List<PlayerDto> playerListToPlayerDtoList(List<Player> list) {
        if ( list == null ) {
            return null;
        }

        List<PlayerDto> list1 = new ArrayList<PlayerDto>( list.size() );
        for ( Player player : list ) {
            list1.add( playerToPlayerDto( player ) );
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

    protected BoardDto boardToBoardDto(Board board) {
        if ( board == null ) {
            return null;
        }

        BoardDto boardDto = new BoardDto();

        boardDto.houses = houseListToHouseDtoList( board.getHouses() );
        boardDto.stores = storeListToStoreDtoList( board.getStores() );
        boardDto.id = board.getId();

        return boardDto;
    }
}

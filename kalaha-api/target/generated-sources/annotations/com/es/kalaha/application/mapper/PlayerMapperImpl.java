package com.es.kalaha.application.mapper;

import com.es.kalaha.application.dto.PlayerDto;
import com.es.kalaha.domain.entities.Player;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-17T13:51:50+0100",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public Player toPlayer(PlayerDto playerDto) {
        if ( playerDto == null ) {
            return null;
        }

        Player player = new Player();

        player.setId( playerDto.getId() );
        player.setName( playerDto.getName() );

        return player;
    }

    @Override
    public PlayerDto fromPlayer(Player player) {
        if ( player == null ) {
            return null;
        }

        PlayerDto playerDto = new PlayerDto();

        playerDto.setName( player.getName() );
        playerDto.setId( player.getId() );

        return playerDto;
    }
}

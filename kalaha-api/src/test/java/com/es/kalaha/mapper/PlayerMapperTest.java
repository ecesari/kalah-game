package com.es.kalaha.mapper;

import com.es.kalaha.application.mapper.PlayerMapper;
import com.es.kalaha.application.dto.PlayerDto;
import com.es.kalaha.domain.entities.Player;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class PlayerMapperTest {

    @Autowired
    private final PlayerMapper playerMapper = Mappers.getMapper(PlayerMapper.class);

    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect() {

        Player player = new Player();
        player.name = "name";
        PlayerDto dto = playerMapper.fromPlayer(player);
        assertEquals(player.getName(), dto.getName());
    }
}

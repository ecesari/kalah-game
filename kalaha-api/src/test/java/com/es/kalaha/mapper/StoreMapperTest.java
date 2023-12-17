package com.es.kalaha.mapper;

import com.es.kalaha.application.mapper.StoreMapper;
import com.es.kalaha.application.dto.StoreDto;
import com.es.kalaha.domain.entities.Player;
import com.es.kalaha.domain.entities.Store;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StoreMapperTest {
    @Autowired
    private final StoreMapper mapper = Mappers.getMapper(StoreMapper.class);

    @Test
    public void givenStoreToDto_withoutReferences_whenMaps_thenCorrect() {
        var entity = new Store();
        entity.id = UUID.randomUUID();
        entity.setSeeds(4);

        //when
        var dto = mapper.fromStore(entity);

        //then
        assertEquals(dto.getClass(), StoreDto.class);
        assertNotNull(dto);
        assertEquals(entity.id, dto.id);
        assertEquals(entity.seeds, dto.seeds);
    }

    @Test
    public void givenStoreToDto_withNext_whenMaps_thenCorrect() {
        var store = new Store();
        store.id = UUID.randomUUID();

        var entity = new Store();
        entity.next = store;

        var dto = mapper.fromStore(entity);

        assertEquals(dto.nextId, store.id);
    }

    @Test
    public void givenStoreToDto_withOwner_whenMaps_thenCorrect() {

        var player = new Player();
        player.id = UUID.randomUUID();

        var entity = new Store();
        entity.owner = player;

        var dto = mapper.fromStore(entity);

        assertEquals(dto.ownerId, player.id);
    }
}
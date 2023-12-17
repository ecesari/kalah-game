package com.es.kalaha.application.service;

import com.es.kalaha.domain.entities.*;
import com.es.kalaha.infrastructure.repository.PitRepository;
import com.es.kalaha.infrastructure.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.UUID;


@Service
public class PitService {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PitRepository repository;

    LinkedList<House> createHouseForPlayer(int seedCount, Player player) {
        LinkedList<House> houses = new LinkedList<>();

        var lastHouse = new House(seedCount, player);
        houses.add(lastHouse);

        while (houses.size() < seedCount) {
            var house = new House(seedCount, player);
            lastHouse.setNext(house);
            houses.add(house);
            lastHouse = house;
        }
        return houses;
    }

    public void SetOpposites(LinkedList<House> player1Houses, LinkedList<House> player2Houses) {
        for (int i = 0; i < player1Houses.size(); i++) {
            House one = player1Houses.get(i);
            House two = player2Houses.get(player2Houses.size() - i - 1);
            one.setOpposite(two);
            two.setOpposite(one);
        }
    }

    public Store createStoreForPlayer(Player player) {
        var store = new Store(player);
        return store;
    }

    public void SetCircular(LinkedList<House> player1Houses, Store player1Store, LinkedList<House> player2Houses, Store player2Store) {
        player1Houses.getLast().setNext(player1Store);
        player1Store.setNext(player2Houses.getFirst());
        player2Houses.getLast().setNext(player2Store);
        player2Store.setNext(player1Houses.getFirst());
    }


    public Pit sowPitAndGetLastPlayedPit(Board board, Pit house, UUID playerId) {
        var lastPitStream = board.houses.stream().filter(h -> h.id.equals(house.id)).findFirst();
        if (lastPitStream.isEmpty()) throw new IllegalArgumentException("There was an error trying to find the pit");
        var lastPit = (Pit) lastPitStream.get();
        var seedCount = lastPit.seeds;
        lastPit.seeds = 0;


        while (seedCount > 0) {
            lastPit = lastPit.next;
            if (lastPit.canBeSowed(playerId)) {
                seedCount--;
                lastPit.seeds++;
                repository.save(lastPit);
            }
        }
        return lastPit;
    }

    public void checkOppositeAndCapture(Pit house, Pit store, Player player) {

        if (house.owner == player && house.seeds == 1) {
            var oppositePitOptional = house.getOpposite();
            if (oppositePitOptional.isPresent()) {
                var oppositePit = oppositePitOptional.get();
                store.seeds += oppositePit.seeds + 1;
                house.seeds = 0;
                oppositePit.seeds = 0;
                repository.save(oppositePit);
                repository.save(house);
                repository.save(store);
            }
        }
    }

    public void saveAllHouses(LinkedList<House> pits) {
        repository.saveAll(pits);
    }

    public Board captureOpponentSeeds(Board board, Player capturingPlayer) {
        var houses = board.houses;
        var ownerStore = board.stores.stream().filter(x -> x.owner == capturingPlayer).findFirst().get();
        for (var house : houses) {
            if (house.owner == capturingPlayer) {
                ownerStore.seeds += house.opposite.seeds;
                house.opposite.seeds = 0;
                repository.save(house.opposite);
                repository.save(ownerStore);
            }
        }
        return board;
    }


    public Store getStoreByPlayer(Board board, Player player) {
        var store = board.stores.stream().filter(x -> x.owner == player).findFirst().get();
        return store;
    }

    public Pit getFromBoardById(Board board, UUID pitId) {
        var pitStream = board.houses.stream().filter(h -> h.id.equals(pitId)).findAny();
        if (pitStream.isEmpty()) throw new IllegalArgumentException("There was an error trying to find the pit");
        var pit = (Pit) pitStream.get();
        return pit;
    }

    public void saveAllStores(Store storeForPlayer1, Store storeForPlayer2) {
        repository.save(storeForPlayer1);
        repository.save(storeForPlayer2);
    }
}

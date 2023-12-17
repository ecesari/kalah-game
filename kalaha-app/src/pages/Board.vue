<template>
  <div>
    <h4>Lets Play Mancala</h4>
    <div v-if="!isLoading">
      <p>Current Player Name: {{ gameDto.currentPlayer.name }}</p>
      <p>IsProcessing: {{ isProcessing }}</p>
      <div class="board">
        <div class="large-pit-container" :active="isPlayer0Active()">
          <div class="pit" :key="gameDto.board.stores[0].id" :active="false">
            {{ gameDto.board.stores[0].seeds }}
            <!-- <div class="pit-action" :action="player0.largePit.actionType">
              {{ player0.largePit.actionLabel }}
            </div> -->
          </div>
        </div>
        <!-- v-for="pit in player0.pits
                .filter((x, xi) => xi < game.numberOfPits)
                .reverse()" -->
        <div class="pits-rows-container">
          <div class="pits-row" :active="isPlayer0Active()">
            <div
              class="pit"
              v-for="pit in gameDto.board.houses
                .filter((x, xi) => xi < 6)
                .reverse()"
              @click="clickOnPit(pit.id)"
              :key="pit.id"
              :active="isPlayer0Active() && pit.seeds > 0"
            >
              {{ pit.seeds }}
              <!-- <div class="pit-action" :action="pit.actionType">
                {{ pit.id }}
              </div> -->
            </div>
          </div>
          <div class="pits-row" :active="isPlayer1Active()">
            <div
              class="pit"
              v-for="pit in gameDto.board.houses.filter((x, xi) => xi < 6)"
              @click="clickOnPit(pit)"
              :key="pit.id"
              :active="isPlayer1Active() && pit.seeds > 0"
            >
              {{ pit.seeds }}
              <!-- <div class="pit-action" :action="pit.actionType">
                {{ pit.actionLabel }}
              </div> -->
            </div>
          </div>
        </div>
        <div class="large-pit-container" :active="isPlayer1Active()">
          <div class="pit" :key="gameDto.board.stores[1].id" :active="false">
            {{ gameDto.board.stores[1].seeds }}
            <!-- <div class="pit-action" :action="player1.largePit.actionType">
              {{ player1.largePit.actionLabel }}
            </div> -->
          </div>
        </div>
        <BoardNotification
          :isOpen="game.notification !== null"
          :text="game.notification"
        />
      </div>

      <div>
        <EndButton />
      </div>
    </div>
  </div>
</template>

<script>
import MancalaService from "../services/MancalaService";
import EndButton from "../components/EndButton.vue";
import BoardNotification from "../components/BoardNotification.vue";
import sleep from "../helpers/Sleep";
import api from "../api/register";

export default {
  name: "mancala-game",
  data() {
    return {
      isProcessing: false,
      isLoading: true,
      player0: {
        id: "",
        username: "",
        largePit: {
          stones: 5,
          actionLabel: "player0",
        },
        pits: [1, 1, 1, 1, 1, 1],
        pits: [
          {
            stones: 5,
            actionLabel: "player0",
          },
          {
            stones: 5,
            actionLabel: "player0",
          },
          {
            stones: 5,
            actionLabel: "player0",
          },
          {
            stones: 5,
            actionLabel: "player0",
          },
          {
            stones: 5,
            actionLabel: "player0",
          },
          {
            stones: 5,
            actionLabel: "player0",
          },
        ],
      },
      player1: {
        id: "",
        username: "",
        largePit: {
          stones: 5,
          actionLabel: "player1",
        },
        pits: [
          {
            stones: 5,
            actionLabel: "player1",
          },
          {
            stones: 5,
            actionLabel: "player1",
          },
          {
            stones: 5,
            actionLabel: "player1",
          },
          {
            stones: 5,
            actionLabel: "player1",
          },
          {
            stones: 5,
            actionLabel: "player1",
          },
          {
            stones: 5,
            actionLabel: "player1",
          },
        ],
      },
      currentPlayerId: "",
      game: {
        numberOfPits: 12,
        allPints: [],
        notification: null,
      },
      gameDto: {
        players: [],
        board: {
          houses: [],
          stores: [],
          id: "",
        },
        gameStatus: "",
        currentPlayer: {
          id: "",
          name: ""
        },
      },
      sowRequest: {
        pitId: 0,
        boardId: 0,
        playerId: 0,
        gameId: ""
      }
    };
  },
  components: {
    EndButton,
    BoardNotification,
  },
  methods: {
    isPlayer0Active() {
      return (
        !this.isLoading &&
        !this.isProcessing &&
        this.gameDto.players[0].id == this.gameDto.currentPlayer.id
      );
    },
    isPlayer1Active() {


      return (
        !this.isLoading &&
        !this.isProcessing &&
        this.gameDto.players[1].id == this.gameDto.currentPlayer.id
      );
    },
    getCurrentPlayer() {
      return this.getPlayerById(this.currentPlayerId);
    },
    getPlayerById(id) {
      return this.player0.id === id ? this.player0 : this.player1;
    },
    getBoard () {
      let gameObject = JSON.parse(localStorage.getItem("gameObject"));
      this.setupBoard(gameObject);
    },
    clickOnPit(id) {
      console.log("this.gameDto");
      this.sowRequest.boardId = this.gameDto.board.id;
      this.sowRequest.playerId = this.gameDto.currentPlayer.id;
      this.sowRequest.pitId = id;
      this.sowRequest.gameId = this.gameDto.id;
      console.log(this.gameDto);
      console.log(this.sowRequest);
      api.Move(this.sowRequest).then((r) => {
        console.log(r);
        this.setupBoard(r);
      });
    },
    setupBoard(gameDto) {
      this.gameDto = gameDto;
    }
  },
  mounted() {
    this.isLoading = false;
    this.game.numberOfPits = 6;
    this.getBoard();
    // this.setupBoard();
  },
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}

.board {
  padding: 24px;
  border: 1px solid rgb(97, 68, 9);
  border-radius: 10px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-image: url("../assets/wood.jpeg");
  background-size: cover;
}

.board .pits-rows-container {
  display: flex;
  flex-direction: column;
}

.board .pits-rows-container .pits-row {
  display: flex;
  flex-direction: row;
  padding: 12px;
}
.board .pits-rows-container .pits-row[active] {
  background-color: rgb(255 255 255 / 25%);
  box-shadow: 0px 0px 20px rgb(255 255 255 / 25%);
  border-radius: 20px;
}

.pit {
  min-width: 100px;
  height: 100px;
  border: 2px solid rgb(97, 68, 9);
  border-radius: 30px;
  background-color: #00000022;
  margin: 4px;
  color: white;
  font-weight: bold;
  font-size: 24px;
  padding-top: 24px;
}

.pit .pit-action {
  font-size: 18px;
  width: fit-content;
  margin: 0 auto;
  box-shadow: 0px 0px 20px white;
  background: #ffffff61;
  padding: 0px 5px;
  border-radius: 10px;
}

.pit .pit-action[action="add"] {
  color: rgb(2, 154, 2);
}

.pit .pit-action[action="remove"] {
  color: rgb(255, 29, 29);
}

.pit[active] {
  cursor: pointer;
}

.pit[active]:hover {
  background-color: rgba(142, 119, 83, 0.264);
  box-shadow: 0px 0px 10px white;
}

.large-pit-container[active] {
  background-color: rgb(255 255 255 / 25%);
  box-shadow: 0px 0px 20px rgb(255 255 255 / 25%);
}

.large-pit-container .pit {
  height: 248px;
  padding-top: 112px;
}
</style>

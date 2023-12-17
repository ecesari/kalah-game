<template>
  <form>
    <md-card>
      <md-card-header :data-background-color="dataBackgroundColor">
        <h4 class="title">New Game</h4>
        <p class="category">Complete details to start a game</p>
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-85">
            <md-field>
              <label>Players</label>
              <div>
                <multiselect
                  :options="playerList"
                  v-model="gameSettings.players"
                  :multiple="true"
                  :close-on-select="false"
                  :show-labels="false"
                  :taggable="true"
                  placeholder="Select or Add Players"
                  label="name"
                  track-by="id"
                  tag-placeholder="Add this as new user" 
                  @tag="AddUser"
                  :max="2"
                ></multiselect>
              </div>

            </md-field>
          </div>
          <!-- <div class="md-layout-item md-small-size-100 md-size-40">
            <md-field>
              <label>Player 2</label>
              <md-input v-model="gameSettings.player2" type="text"></md-input>
            </md-field>
          </div> -->
          <div class="md-layout-item md-small-size-100 md-size-15">
            <md-field>
              <label>Seed Count</label>
              <md-input
                v-model="gameSettings.seedCount"
                type="number"
              ></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-size-100 text-right">
            <md-button @click="CreateGame()" class="md-raised md-success"
              >Start Game</md-button
            >
          </div>
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>
<script>
import api from "../../api/register";
import Multiselect from "vue-multiselect";

export default {
  name: "edit-profile-form",
  components: {
    Multiselect,
  },
  props: {
    dataBackgroundColor: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      gameSettings: {
        players: [],
        seedCount: 6,
      },
      playerList: [],
      addedUser : {
        name: "",
      }
    };
  },
  mounted() {
    this.GetGames();
    this.GetUsers();
  },
  methods: {
    GetGames() {
      api.GamesCheck().then((r) => {
        console.log(r);
      });
    },
    CreateGame() {
      console.log(this.gameSettings.players)
      api.CreateGame(this.gameSettings).then((r) => {
        document.location.href = "#/table";
      });
    },
    GetUsers() {
      api.GetUsers().then((r) => {
        console.log(r);
        this.playerList = r;
      });
    },
    AddUser(name) {
      this.addedUser.name = name;
      api.AddUser(this.addedUser).then((r) => {
        this.playerList.push(this.addedUser);
        this.gameSettings.players.push(r);
      });
    },
  },
};
</script>
<style></style>

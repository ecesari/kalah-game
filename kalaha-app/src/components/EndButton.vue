<template>
  <div>
    <button
      type="button"
      class="btn btn-danger"
      @click="endGame()"
    >
      End Game
    </button>
  </div>
</template>

<script>
// import MancalaService from "../services/MancalaService";

export default {
  name: "end-game",
  data() {
    return {
    };
  },
  methods: {
    endGame() {
      // MancalaService.endGame()
      //   .then(() => {
      //     this.$router.push("/start")
      //   })
      //   .catch((e) => {
      //     console.log(e);
      //   });
    },
  },
  mounted() {
  },
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
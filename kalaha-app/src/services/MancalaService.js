import http from "../http-common";

class MancalaService {
  getGame() {
    return http.get("/game");
  }
  startGame() {
    return http.post("/game/start");
  }
  endGame() {
    return http.delete("/game/end");
  }
  getActions(id) {
    return http.patch(`/game/actions/${id}`);
  }

}

export default new MancalaService();
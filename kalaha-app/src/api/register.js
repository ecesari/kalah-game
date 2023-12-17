/* eslint-disable no-debugger */
import http from "../utils/http";

var VUE_APP_API = "http://localhost:8080/";
// url, data, handleOnError,successMessage
export default {
  CreateGame(data) {
    return http.post(VUE_APP_API + "games", data, true, null, null);
  },
  GamesCheck() {
    return http.get(VUE_APP_API + "games");
  },
  CreateUser(data) {
    return http.post(VUE_APP_API + "games/users", data, true, null, null);
  },
  GetUsers() {
    return http.get(VUE_APP_API + "users");
  },
  AddUser(data) {
    return http.post(VUE_APP_API + "users", data, true, null, null);
  },
  Move(id) {
    return http.post(VUE_APP_API + "games/sow", id, true, null, null);
  },
};

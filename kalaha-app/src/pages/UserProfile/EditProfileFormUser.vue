<template>
  <form>
    <md-card>
      <md-card-header :data-background-color="dataBackgroundColor">
        <h4 class="title">New Player</h4>
        <p class="category">Enter User Details</p>
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-100">
            <md-field>
              <label>Username</label>
              <md-input v-model="username" type="text"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-size-100 text-right">
            <md-button @click="CreateUser()" class="md-raised md-success"
              >Save User</md-button
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
  props: {
    dataBackgroundColor: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      user : {
        username: "",
      }
    };
  },
  methods: {
    CreateUser() {
      api.CreateUser(this.user).then((r) => {
        console.log(r);
      });
    },
  },
};
</script>
<style></style>

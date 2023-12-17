<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-66">
        <new-game-form data-background-color="green"> </new-game-form>
      </div>
      <div class="md-layout-item md-medium-size-100 md-size-33">
        <user-card> </user-card>
      </div>
    </div>
  </div>
</template>

<script>
import { NewGameForm, UserCard } from "@/pages";

export default {
  components: {
    NewGameForm,
    UserCard,
  },
};
</script>

<template>
    <div class="board-notification" v-if="isOpen">
        <div class="text">
            {{text}}
        </div>
    </div>
</template>

<style>
    .board-notification{
        position: absolute;
        width: 100%;
        margin-left: -24px;
    }
    .board-notification .text{
        width: 100%;
        background: white;
        margin-top: 80px;
        height: 80px;
        font-size: 28px;
        font-weight: bold;
        padding: 20px;
    }
</style>

<script>
    export default {
    name: "board-notification",
    props: {
        isOpen: Boolean,
        text: String,
    },
    data() {
        return {
        }  
    }
    }
</script>

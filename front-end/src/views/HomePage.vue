<template>
    <div class="container">
        <div class="row">
            <div v-for="(image, i) in album" :key="i">
                <img :src="image.url" >
            </div>
        </div>
    </div>
</template>

<script>
import {store} from '../store';
import axios from 'axios';
    export default {
        data(){
            return{
                album: [],
                title: ''
            }
        },
        methods:{
            getAllImages(){
                const data = {
                    params:{
                        title: this.title
                    }
                }
                axios.get(`${store.apiUrl}/images`, data).then((response)=>{
                    console.log(response.data)
                    this.album = response.data
                })
            }
        },
        mounted(){
            this.getAllImages()
        }
    }
</script>

<style lang="scss" scoped>

</style>
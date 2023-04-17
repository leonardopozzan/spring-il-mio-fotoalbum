<template>
    <div class="container my-5 pb-5">
        <div>
            <form @submit.prevent="getAllImages()" class="d-flex">
                <input type="text" class="form-control w-25 me-3" v-model="title">
                <button type="submit" class="btn btn-primary"> Filtra </button>
            </form>
        </div>
        <div class="row g-3 mt-2" v-if="album.length">
            <div v-for="(image, i) in album" :key="i" class="col-4">
                <div class="col-10 img-box mb-2">
                    <img :src="image.url" >
                    <h3 class="title">{{ image.title }}</h3>
                </div>
                <span class="fs-5 fw-bold">Categorie:&nbsp;&nbsp;</span>
                <span v-for="(category, j) in image.categories" :key="j" class="text-capitalize">{{ category.name }}&nbsp;</span>
                <div class="fs-5 fw-bold mt-2 mb-1">Descrizione:</div>
                <div>
                    {{ image.description }}
                </div>
            </div>
        </div>
        <div v-else class="message">Non ci sono Foto!</div>
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

.img-box{
    border-radius: 20px;
    overflow: hidden;
    position: relative;
    .title{
        position: absolute;
        bottom: 15px; left: 20px;
        color: white;
        margin: 0;
        text-transform: capitalize;
    }
}
.message{
    font-weight: bold;
    font-size: 1.3rem;
    text-align: center;
    color: red;
}
</style>
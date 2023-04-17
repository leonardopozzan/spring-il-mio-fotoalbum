<template>
    <div class="container my-5 pb-5">
        <div class="d-flex justify-content-center align-items-start">
            <form @submit.prevent="sendMessage()" class="w-50">
                <div>
                    <label for="" class="form-label">Email:</label>
                    <input type="email" class="form-control" v-model="email">
                </div>
                <div>
                    <label for="" class="form-label">Messaggio:</label>
                    <textarea v-model="message" rows="4" class="form-control"></textarea>
                </div>
                <div class="mt-2">
                    <button type="submit" class="btn btn-primary me-3">Invia</button>
                    <button type="reset" class="btn btn-danger">Reset</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                email: '',
                message: '',

            }
        },
        methods:{
            sendMessage(){
                
            }
        }
    }
</script>

<style lang="scss" scoped>

</style>
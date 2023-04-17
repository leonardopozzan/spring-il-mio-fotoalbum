<template>
    <div class="container my-5 pb-5">
        <div class="d-flex justify-content-center align-items-start">
            <form @submit.prevent="sendMessage()" class="w-50">
                <div>
                    <label for="" class="form-label">Email:</label>
                    <input type="text" class="form-control" v-model="email" :class="{'is-invalid' : errors.email}">
                    <ul :class="{'invalid-feedback' : errors.email}">
                        <li v-for="(error, i) in errors.email" :key="i">{{ error }}</li>
                    </ul>
                </div>
                <div>
                    <label for="" class="form-label">Messaggio:</label>
                    <textarea v-model="message" rows="4" class="form-control" :class="{'is-invalid' : errors.text}"></textarea>
                    <ul :class="{'invalid-feedback' : errors.text}">
                        <li v-for="(error, i) in errors.text" :key="i">{{ error }}</li>
                    </ul>
                </div>
                <div class="mt-2">
                    <button type="submit" class="btn btn-primary me-3" :disabled="sending">{{ sending ? 'Invia...' : 'Invia' }}</button>
                    <button type="reset" class="btn btn-danger">Reset</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
import Swal from 'sweetalert2'
import {store} from '../store';
import axios from 'axios';
    export default {
        data(){
            return{
                email: '',
                message: '',
                errors:{},
                sending: false
            }
        },
        methods:{
            sendMessage(){
                this.sending = true;
                const data= JSON.stringify({
                        email: this.email,
                        text: this.message
                })
                axios.post(`${store.apiUrl}/message` ,  data,{ headers : {'Content-Type': 'application/json'}} ).then((response)=>{
                    console.log(response.data)
                    this.sending = false
                    this.errors= {}
                    this.email = ''
                    this.message = ''
                    if(response.data.success){
                        this.successMessage()
                    }else{
                        let errorList = response.data.errors
                        for(let error of errorList){
                            let key = error.field
                            if(key in this.errors){
                                this.errors[key].push(error.defaultMessage)
                            }else{
                                this.errors[key] = [error.defaultMessage]
                            }
                        }
                        console.log(this.errors)
                    }
                })
            },
            successMessage(){
                Swal.fire({
                    position: 'top',
                    icon: 'success',
                    title: 'Il messaggio è stato inviato',
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        }
    }
</script>

<style lang="scss" scoped>

</style>
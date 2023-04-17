<template>
    <nav>
        <div  class="container my-navbar">
            <div class="logo">
                <div class="img-box">
                    <img src="/img/logo.jpg" >
                </div>
                <h2>Album</h2>
            </div>
            <ul class="links">
                <li><router-link :to="{name: 'home'}">Home</router-link></li>
                <li><router-link :to="{name: 'contact-us'}">Invia un messaggio</router-link></li>
            </ul>
        </div>
    </nav>
</template>

<script>
    export default {
        
    }
</script>

<style lang="scss" scoped>
nav{
    border-bottom: 3px solid black;
    .my-navbar{
        
        height: 100px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        .logo{
            display: flex;
            align-items: center;
            h2{
                font-family: 'Sedgwick Ave Display', cursive;
                font-size: 2.5rem;
            }
            .img-box{
                height: 100px;
            }
        }
        .links{
            display: flex;
            align-items: center;
            text-transform: capitalize;
            font-weight: bold;
            font-size: 1.2rem;
            li{
                margin-left: 1rem;
                a{
                    color: black; 
                    position: relative;
                }
                a::before {
                    content: "";
                    position: absolute;
                    display: block;
                    width: 100%;
                    height: 2px;
                    bottom: -3px;
                    left: 0;
                    background-color: #000;
                    transform: scaleX(0);
                    transition: transform 0.3s ease;
                }
                a:hover::before {
                    transform: scaleX(1);
                }
            }
        }
    }

}
</style>
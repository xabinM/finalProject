<template>
  <header class="header">
    <router-link to="/" class="logo">📘 MyWeb</router-link>

    <nav class="nav">
      <template v-if="isLoggedIn">
        <div class="user-info" @click="toggleMenu">
          {{ user.username }} ({{ user.email }})
          <div v-if="menuVisible" class="dropdown">
            <router-link to="/mypage">내 정보 보기</router-link>
            <button @click="logout">로그아웃</button>
          </div>
        </div>
      </template>
      <template v-else>
        <router-link to="/login">로그인</router-link>
        <router-link to="/signup">회원가입</router-link>
      </template>
    </nav>
  </header>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/userStore'
import axios from '@/axios'

const router = useRouter();
const userStore = useUserStore();

const isLoggedIn = computed(() => !!userStore.token);
const user = computed(() => userStore.user);

const menuVisible = ref(false);

const logout = async () => {
  try {
    userStore.clearUser();
    menuVisible.value = false;

    router.push('/');
  } catch (e) {
    console.error('로그아웃 실패:', e)
  }
}

const toggleMenu = () => {
  menuVisible.value = !menuVisible.value
}

</script>


<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  padding: 12px 20px;
  background-color: #f8f9fa;
  align-items: center;
  border-bottom: 1px solid #ddd;
}

.logo {
  font-weight: bold;
  font-size: 20px;
  text-decoration: none;
  color: #333;
}

.nav>* {
  margin-left: 12px;
  text-decoration: none;
  color: #333;
}

.user-info {
  cursor: pointer;
  position: relative;
}

.dropdown {
  position: absolute;
  top: 30px;
  right: 0;
  background: white;
  border: 1px solid #ccc;
  padding: 8px 12px;
  display: flex;
  flex-direction: column;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  z-index: 10;
}

.dropdown>* {
  margin: 4px 0;
  text-decoration: none;
  color: #333;
}

.dropdown>button {
  background: none;
  border: none;
  cursor: pointer;
  text-align: left;
  padding: 0;
}
</style>

<template>
  <div class="login-container">
    <h2>로그인</h2>
    <form @submit.prevent="login">
      <input v-model="username" placeholder="아이디" />
      <input v-model="password" type="password" placeholder="비밀번호" />
      <button type="submit">로그인</button>
      <p v-if="error" class="error">{{ error }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from '../axios';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/userStore';

const username = ref('');
const password = ref('');
const error = ref('');
const router = useRouter();
const userStore = useUserStore();

const login = async () => {
  try {
    const res = await axios.post('/auth/login', {
      username: username.value,
      password: password.value,
    });

    const { member, token } = res.data;

    userStore.setUser({ token, ...member });

    router.push('/main');
  } catch (e) {
    error.value = e.response?.data?.message || '로그인에 실패했습니다.';
  }
};
</script>

<style scoped>
.login-container {
  max-width: 300px;
  margin: 80px auto 0 auto;
  padding: 20px;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>

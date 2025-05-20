<template>
  <div class="signup-container">
    <h2>회원가입</h2>
    <form @submit.prevent="signup">
      <input v-model="username" placeholder="아이디" />
      <input v-model="password" type="password" placeholder="비밀번호" />
      <input v-model="email" type="email" placeholder="이메일" />
      <button type="submit">회원가입</button>
      <p v-if="error" class="error">{{ error }}</p>
      <p v-if="success" class="success">회원가입이 완료되었습니다!</p>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from '../axios';
import { useRouter } from 'vue-router';

const username = ref('');
const password = ref('');
const email = ref('');
const error = ref('');
const success = ref(false);
const router = useRouter();

const signup = async () => {
  try {
    error.value = '';
    success.value = false;

    const res = await axios.post('/auth/signup', {
      username: username.value,
      password: password.value,
      email: email.value,
    });

    success.value = true;

    // 성공 후 잠깐 기다렸다가 로그인 페이지로 이동
    setTimeout(() => {
      router.push('/login');
    }, 1000);
  } catch (e) {
    error.value = e.response?.data?.message || '회원 가입 실패 : 알 수 없는 오류입니다.';
  }
};
</script>

<style scoped>
.signup-container {
  max-width: 300px;
  margin: auto;
  padding: 20px;
}

.error {
  color: red;
  margin-top: 10px;
}

.success {
  color: green;
  margin-top: 10px;
}
</style>

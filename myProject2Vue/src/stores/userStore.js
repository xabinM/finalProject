import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useUserStore = defineStore('user', () => {
  const user = ref(null);
  const token = ref(null);

  const setUser = (userInfo) => {
    token.value = userInfo.token;
    const { token: _, ...rest } = userInfo; 
    user.value = rest;
  };

  const clearUser = () => {
    user.value = null;
    token.value = null;
  };

  return { user, token, setUser, clearUser };
}, {
  persist: true
},);
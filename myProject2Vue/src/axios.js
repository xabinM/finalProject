// src/axios.js
import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080', // 백엔드 서버 주소에 맞게 수정
});

instance.interceptors.request.use((config) => {
  if (config.url.includes('/auth/login')) {
    return config;
  }

  const token = localStorage.getItem('jwt_token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error)
});

export default instance;

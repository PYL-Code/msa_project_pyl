<template>
  <div class="container">
    <!-- 사이드바 -->
    <Sidebar />

    <!-- 개인정보 관리 -->
    <div class="content">
      <h2>개인정보 관리</h2>
      <div class="info-box">
        <label>아이디</label>
        <p>{{ user.userId }}</p>

        <label>이름</label>
        <p>{{ user.name }}</p>

        <label>이메일</label>
        <p>{{ user.email }}</p>

        <label>전화번호</label>
        <p>{{ user.phone }}</p>

        <label>주소</label>
        <p>{{ user.address }}</p>

        <label>우편번호</label>
        <p>{{ user.postcode }}</p>
      </div>

      <router-link to="/mypage/myinfo/update" class="btn">수정하기</router-link>
      <button class="btn danger" @click="deleteUser">회원 탈퇴</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Sidebar from "../components/sidebar.vue";
import { parseJwt } from "../utils/jwtUtils.js"
import createFakeJwt from "../utils/fakeJwt.js"; //TODO : 추후 삭제 (FakeJWT)

export default {
  components: {
    Sidebar,
  },
  data() {
    return {
      user: {
        id: "",
        userId: "",
        name: "",
        email: "",
        phone: "",
        address: "",
        postcode: "",
      },
    };
  },
  mounted() {
    this.loadUserInfo();
  },
  methods: {
    async loadUserInfo() {
      // const token = localStorage.getItem('token')
      const token = createFakeJwt(); //TODO : 추후 삭제 (Fake JWT)
      if (!token) {
        alert('로그인이 필요합니다.')
        this.$router.push('/signin')
        return
      }

      const payload = parseJwt(token)
      const id = payload?.id || payload?.userId || payload?.sub
      if (!id) {
        console.error('토큰에 사용자 ID가 없습니다.')
        return
      }

      try {
        const response = await axios.get(`http://localhost:9876/api/user/${id}`)
        this.user = response.data
      } catch (error) {
        console.error("회원 정보를 불러오는 중 오류 발생:", error)
      }
    },
    async deleteUser() {
      if (!confirm("정말 탈퇴하시겠습니까?")) return;

      try {
        await axios.delete(`http://localhost:9876/api/user/delete/${this.user.id}`)
        alert("회원 탈퇴가 완료되었습니다.")
        localStorage.clear() //TODO : 어디에 jwt 토큰 저장하는지에 따라 수정
        this.$router.push("/")
      } catch (error) {
        console.error("회원 탈퇴 오류:", error)
      }
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  max-width: 1200px;
  margin: 40px auto;
}

.content {
  flex-grow: 1;
  padding: 40px 50px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 128, 0, 0.1);
}

h2 {
  color: #008000;
  font-size: 26px;
  margin-bottom: 30px;
}

.info-box {
  display: grid;
  grid-template-columns: 1fr;
  row-gap: 15px;
  margin-bottom: 30px;
}

label {
  font-weight: 600;
  margin-bottom: 5px;
  color: #333;
}

p {
  margin: 0;
  padding: 10px 12px;
  border: 1px solid #ccc;
  border-radius: 6px;
  background: #f9f9f9;
  font-size: 15px;
  color: #555;
}

.btn {
  background: #008000;
  color: white;
  padding: 12px 5px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  margin-top: 10px;
  font-size: 15px;
  transition: all 0.3s ease;
  display: inline-block;
  min-width: 120px;
  text-align: center;
  text-decoration: none;
}

.btn:hover {
  background: #006400;
  box-shadow: 0 4px 12px rgba(0, 128, 0, 0.3);
}

.danger {
  background: #ff4444;
  margin-left: 12px;
}

.danger:hover {
  background: #cc0000;
  box-shadow: 0 4px 12px rgba(255, 68, 68, 0.3);
}
</style>

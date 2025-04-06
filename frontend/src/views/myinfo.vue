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
      // TODO: JWT에서 id 추출 후 사용
      const id = 1; //테스트 값

      try {
        const response = await axios.get(`http://localhost:9876/api/user/${id}`);
        // console.log("받은 데이터:", response.data);
        this.user = response.data;
      } catch (error) {
        console.error("회원 정보를 불러오는 중 오류 발생:", error);
      }
    },
    async deleteUser() {
      if (!confirm("정말 탈퇴하시겠습니까?")) return;

      try {
        await axios.delete(`http://localhost:9876/api/user/delete/${this.user.id}`);
        alert("회원 탈퇴가 완료되었습니다.");
        sessionStorage.clear();
        this.$router.push("/");
      } catch (error) {
        console.error("회원 탈퇴 오류:", error);
      }
    },
  },
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
  padding: 30px;
}
h2 {
  color: #008000;
}
label {
  font-weight: bold;
  display: block;
  margin-top: 10px;
}
p {
  margin-top: 5px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background: #f9f9f9;
}
.btn {
  background: #008000;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 15px;
  display: inline-block;
  text-align: center;
  text-decoration: none;
}
.btn:hover {
  background: #006400;
}
.danger {
  background: #ff4444;
  margin-left: 10px;
}
.danger:hover {
  background: #cc0000;
}
</style>

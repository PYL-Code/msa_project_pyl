<template>
  <div class="container">
    <Sidebar />

    <div class="content">
      <h2>회원정보 수정</h2>

      <div class="edit-form">
        <label>이름</label>
        <input v-model="user.name" type="text" />

        <label>이메일</label>
        <input v-model="user.email" type="email" />

        <label>전화번호</label>
        <input v-model="user.phone" type="text" />

        <label>주소</label>
        <input v-model="user.address" type="text" />

        <label>우편번호</label>
        <input v-model="user.postcode" type="text" />

        <div class="button-group">
          <button class="btn cancel" @click="$router.go(-1)">취소</button>
          <button class="btn save" @click="updateUserInfo">저장</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Sidebar from "../components/Sidebar.vue";

export default {
  components: {
    Sidebar,
  },
  data() {
    return {
      user: {
        id: "",
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
      const id = 1; // TODO: JWT로 id 받아오기
      try {
        const res = await axios.get(`http://localhost:9876/api/user/${id}`);
        this.user = res.data;
      } catch (err) {
        console.error("회원 정보 불러오기 오류:", err);
      }
    },
    async updateUserInfo() {
      try {
        await axios.put(`http://localhost:9876/api/user/update/${this.user.id}`, this.user);
        alert("회원정보가 성공적으로 수정되었습니다.");
        this.$router.push("/mypage/myinfo");
      } catch (err) {
        console.error("수정 오류:", err);
        alert("수정에 실패했습니다.");
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
  padding: 40px;
  background: #f9f9f9;
  border-left: 2px solid #e0e0e0;
}

h2 {
  margin-bottom: 30px;
  color: #008000;
  text-align: center;
}

.edit-form label {
  display: block;
  margin-top: 15px;
  font-weight: bold;
}

.edit-form input {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.button-group {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 30px;
}

.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}

.cancel {
  background-color: #ccc;
  color: #333;
}

.save {
  background-color: #008000;
  color: white;
}

.save:hover {
  background-color: #006400;
}

.cancel:hover {
  background-color: #999;
}
</style>

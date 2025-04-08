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
        <div class="postcode-row">
          <input v-model="user.postcode" type="text" readonly />
          <button type="button" class="btn search" @click="openPostcode">우편번호 찾기</button>
        </div>

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
import Sidebar from "../components/sidebar.vue";
import createFakeJwt from "../utils/fakeJwt.js"; //TODO : 추후 삭제 (FakeJWT)
import { parseJwt } from "../utils/jwtUtils.js";

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
      // const token = localStorage.getItem('token')
      const token = createFakeJwt(); //TODO : 추후 삭제 (Fake JWT)
      if (!token) {
        alert('로그인이 필요합니다.')
        this.$router.push('/signin')
        return
      }

      const payload = parseJwt(token)
      const id = payload?.no;
      if (!id) {
        console.error('토큰에 사용자 ID가 없습니다.')
        return
      }

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
    openPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          // 주소 형식 설정
          let fullAddress = data.address;
          let extraAddress = "";

          if (data.addressType === "R") {
            if (data.bname !== "") {
              extraAddress += data.bname;
            }
            if (data.buildingName !== "") {
              extraAddress += (extraAddress ? ", " + data.buildingName : data.buildingName);
            }
            if (extraAddress !== "") {
              fullAddress += " (" + extraAddress + ")";
            }
          }

          // 주소와 우편번호 데이터 바인딩
          this.user.postcode = data.zonecode;
          this.user.address = fullAddress;
        },
      }).open();
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
  text-align: left;
  font-size: 26px;
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

.postcode-row {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-top: 5px;
}

.search {
  background-color: #006400;
  color: white;
  padding: 8px 14px;
}

.search:hover {
  background-color: #004d00;
}
</style>

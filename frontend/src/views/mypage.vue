<template>
  <div class="mypage-container">
    <!-- 좌측 사이드바 -->
    <Sidebar />

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
      <!-- 회원정보 카드 -->
      <div class="user-card">
        <div class="user-profile">
          <div>
            <p class="username">{{ user.name }}님</p>
            <p class="userid">아이디: {{ user.userId }}</p>
            <router-link to="/mypage/myinfo" class="small-link">개인정보 관리</router-link>
          </div>
        </div>
        <div class="user-stats">
          <div>
            <strong>{{ reservationCount }}</strong>
            <span>예약</span>
          </div>
        </div>
      </div>

      <!-- 예약 정보 카드 -->
      <div class="reservation-section">
        <h3>예약 내역</h3>

        <div v-if="reservations.length > 0" class="reservation-list">
          <div
              v-for="r in reservations.slice(0, 2)"
              :key="r.id"
              class="reservation-card"
          >
            <img :src="r.imgUrl" alt="여행 이미지" class="reservation-image" />
            <div class="reservation-info">
              <p class="reservation-title">{{ r.title }}</p>
              <p class="reservation-description">{{ r.description }}</p>
              <p><strong>기간:</strong> {{ r.duration }}</p>
              <p><strong>가격:</strong> {{ r.price.toLocaleString() }}원</p>
            </div>
          </div>
          <router-link to="/mypage/reservations" class="small-link"
          >→ 전체 예약 보기</router-link
          >
        </div>

        <div v-else class="no-reservation">
          <p>현재 예약하신 내역이 없습니다.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Sidebar from "../components/sidebar.vue";
import { parseJwt } from "../utils/jwtUtils.js";
import createFakeJwt from "../utils/fakeJwt.js";

export default {
  components: {
    Sidebar,
  },
  data() {
    return {
      user: {
        id: "",
        name: "",
        userId: "",
      },
      reservations: [],
    };
  },
  computed: {
    reservationCount() {
      return this.reservations.length;
    },
  },
  mounted() {
    this.loadUserInfo();
    this.loadReservations();
  },
  methods: {
    async loadUserInfo() {
      const token = createFakeJwt(); // TODO: 실제 JWT 적용 시 변경
      if (!token) {
        alert("로그인이 필요합니다.");
        this.$router.push("/signin");
        return;
      }

      const payload = parseJwt(token);
      const id = payload?.id || payload?.userId || payload?.sub;
      if (!id) {
        console.error("토큰에 사용자 ID가 없습니다.");
        return;
      }

      try {
        const res = await axios.get(`http://localhost:9876/api/user/${id}`);
        this.user = res.data;
      } catch (err) {
        console.error("회원 정보 에러:", err);
      }
    },
    async loadReservations() {
      const token = createFakeJwt(); // TODO: 실제 JWT 적용 시 변경
      if (!token) {
        alert("로그인이 필요합니다.");
        this.$router.push("/signin");
        return;
      }

      const payload = parseJwt(token);
      const id = payload?.id || payload?.userId || payload?.sub;
      if (!id) {
        console.error("토큰에 사용자 ID가 없습니다.");
        return;
      }

      try {
        const res = await axios.get(`http://localhost:9876/api/reservation/${id}`);
        this.reservations = res.data;
      } catch (err) {
        console.error("예약 정보 에러:", err);
      }
    },
  },
};
</script>

<style scoped>
.mypage-container {
  display: flex;
  max-width: 1200px;
  margin: 40px auto;
}

.main-content {
  flex-grow: 1;
  padding: 30px;
}

/* 회원 정보 카드 */
.user-card {
  background: #f4fef4;
  border: 2px solid #cce5cc;
  border-radius: 10px;
  padding: 20px;
  margin-bottom: 30px;
}

.user-profile {
  display: flex;
  align-items: center;
  gap: 20px;
}

.username {
  font-size: 18px;
  font-weight: bold;
}

.userid {
  color: #666;
}

.user-stats {
  display: flex;
  gap: 40px;
  margin-top: 20px;
}

.user-stats div {
  text-align: center;
}

.user-stats strong {
  display: block;
  font-size: 20px;
  color: #008000;
}

.small-link {
  color: #008000;
  text-decoration: none;
  margin-top: 5px;
  display: inline-block;
}

.small-link:hover {
  text-decoration: underline;
}

/* 예약 카드 */
.reservation-section h3 {
  font-size: 20px;
  margin-bottom: 15px;
  color: #006400;
}

.reservation-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.reservation-card {
  display: flex;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  background: #f9f9f9;
  align-items: flex-start;
  gap: 20px;
}

.reservation-image {
  width: 120px;
  height: 80px;
  margin-top: 20px;
  border-radius: 5px;
  object-fit: cover;
}

.reservation-info {
  flex-grow: 1;
}

.reservation-title {
  font-weight: bold;
  font-size: 16px;
  margin-bottom: 5px;
}

.reservation-description {
  font-size: 14px;
  color: #555;
  margin-bottom: 5px;
}

.no-reservation {
  text-align: center;
  color: #888;
  margin-top: 30px;
}
</style>

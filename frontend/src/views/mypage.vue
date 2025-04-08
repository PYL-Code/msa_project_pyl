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
            <div class="card-img">
              <img :src="r.imgUrl" alt="여행 이미지" />
            </div>
            <div class="reservation-info">
              <p class="reservation-title">{{ r.title }}</p>
              <p class="reservation-description">{{ r.description }}</p>
              <p class="reservation-duration"><strong>기간:</strong> {{ r.duration }}</p>
              <p class="reservation-price">{{ r.price.toLocaleString() }}원</p>
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
import reservation from "./reservation.vue";

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
      // const token = localStorage.getItem('token')
      const token = createFakeJwt(); // TODO: 실제 JWT 적용 시 변경
      if (!token) {
        alert("로그인이 필요합니다.");
        this.$router.push("/signin");
        return;
      }

      const payload = parseJwt(token);
      const id = payload?.no;
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
      // const token = localStorage.getItem('token')
      const token = createFakeJwt(); // TODO: 실제 JWT 적용 시 변경
      if (!token) {
        alert("로그인이 필요합니다.");
        this.$router.push("/signin");
        return;
      }

      const payload = parseJwt(token);
      const id = payload?.no;
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

/* 예약 카드 */
.reservation-section h3 {
  font-size: 24px;
  color: #2e7d32;
  font-weight: bold;
  margin-bottom: 20px;
}

.reservation-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.reservation-card {
  display: flex;
  background-color: #fff;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s ease;
  min-height: 160px;
}


.reservation-card:hover {
  transform: translateY(-3px);
}

.reservation-image {
  width: 200px;
  height: 100%;
  object-fit: cover;
}

.reservation-info {
  flex: 1;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.reservation-title {
  font-size: 20px;
  font-weight: bold;
  color: #006400;
  margin-bottom: 6px;
}

.reservation-description {
  font-size: 14px;
  color: #555;
  margin-bottom: 10px;
}

.reservation-duration {
  font-size: 14px;
  color: #444;
}

.reservation-price {
  font-size: 18px;
  color: #d32f2f;
  font-weight: bold;
  margin-top: 8px;
}

.card-img img {
  width: 200px;
  height: 100%;
  object-fit: cover;
}

.small-link {
  color: #2e7d32;
  text-decoration: none;
  margin-top: 15px;
  display: inline-block;
  font-size: 14px;
}

.small-link:hover {
  text-decoration: underline;
}


.small-link:hover {
  text-decoration: underline;
}


.no-reservation {
  text-align: center;
  color: #888;
  margin-top: 30px;
}
</style>

<template>
  <div class="container">
    <!-- 사이드바 -->
    <Sidebar />

    <!-- 예약 내역 -->
    <div class="content">
      <h2>예약 관리</h2>
      <div v-if="reservations.length === 0" class="no-reservation">
        <p>예약 내역이 없습니다.</p>
      </div>
      <div v-else class="reservation-list">
        <div class="reservation" v-for="reservation in reservations" :key="reservation.id">
          <img :src="reservation.imgUrl" alt="여행 이미지" class="reservation-img" />
          <div class="reservation-info">
            <p class="title">{{ reservation.title }}</p>
            <p class="desc">{{ reservation.description }}</p>
            <p><strong>가격:</strong> {{ reservation.price.toLocaleString() }}원</p>
            <p><strong>기간:</strong> {{ reservation.duration }}</p>
            <button class="btn danger" @click="cancelReservation(reservation.id)">예약 취소</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Sidebar from "../components/sidebar.vue";
import { parseJwt } from "../utils/jwtUtils.js";
import createFakeJwt from "../utils/fakeJwt.js"; //TODO : 추후 삭제 (Fake JWT)

export default {
  components: {
    Sidebar,
  },
  data() {
    return {
      reservations: [],
    };
  },
  mounted() {
    this.fetchReservations();
  },
  methods: {
    async fetchReservations() {
      const token = createFakeJwt(); //TODO : 실제 JWT로 교체
      if (!token) {
        alert('로그인이 필요합니다.');
        this.$router.push('/signin');
        return;
      }

      const payload = parseJwt(token);
      const id = payload?.id || payload?.userId || payload?.sub;
      if (!id) {
        console.error('토큰에 사용자 ID가 없습니다.');
        return;
      }

      try {
        const res = await axios.get(`http://localhost:9876/api/reservation/${id}`);
        this.reservations = res.data;
      } catch (err) {
        console.error('예약 내역 불러오기 실패:', err);
      }
    },

    async cancelReservation(id) {
      if (!confirm('정말 예약을 취소하시겠습니까?')) return;
      try {
        await axios.delete(`http://localhost:9876/api/reservation/delete/${id}`);
        alert('예약이 취소되었습니다.');
        this.fetchReservations();
      } catch (err) {
        console.error('예약 취소 실패:', err);
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
  margin-bottom: 20px;
}

.no-reservation {
  text-align: center;
  color: #666;
  margin-top: 50px;
  font-size: 16px;
}

.reservation-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.reservation {
  display: flex;
  border: 1px solid #ccc;
  border-radius: 8px;
  background: #f9f9f9;
  overflow: hidden;
}

.reservation-img {
  width: 150px;
  height: 100%;
  margin: 15px;
  margin-top: 25px;
  object-fit: cover;
}

.reservation-info {
  padding: 15px;
  flex-grow: 1;
}

.reservation-info p {
  margin: 5px 0;
}

.title {
  font-weight: bold;
  font-size: 18px;
  color: #006400;
}

.desc {
  font-size: 14px;
  color: #555;
  margin-bottom: 8px;
}

.btn {
  background: #008000;
  color: white;
  padding: 8px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;
}

.btn:hover {
  background: #006400;
}

.danger {
  background: #ff4444;
}

.danger:hover {
  background: #cc0000;
}
</style>

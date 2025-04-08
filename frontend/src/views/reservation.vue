<template>
  <div class="mypage-container">
    <Sidebar />

    <div class="mypage-content">
      <h2 class="page-title">예약 내역</h2>

      <div v-if="reservations.length === 0" class="no-data">
        <p>예약 내역이 없습니다.</p>
      </div>

      <div v-else class="reservation-cards">
        <div class="card" v-for="reservation in reservations" :key="reservation.id">
          <div class="card-img">
            <img :src="reservation.imgUrl" alt="여행 이미지" />
          </div>
          <div class="card-info">
            <div class="info-top">
              <h3 class="title">{{ reservation.title }}</h3>
              <p class="desc">{{ reservation.description }}</p>
            </div>
            <div class="info-bottom">
              <p><strong>여행 기간:</strong> {{ reservation.duration }}</p>
              <p class="price">{{ reservation.price.toLocaleString() }}원</p>
              <button class="cancel-btn" @click="cancelReservation(reservation.id)">예약 취소</button>
            </div>
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
      // const token = localStorage.getItem('token')
      const token = createFakeJwt(); // TODO: 추후 삭제 (Fake JWT)
      if (!token) {
        alert('로그인이 필요합니다.');
        this.$router.push('/signin');
        return;
      }

      const payload = parseJwt(token);
      const id = payload?.no;

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
.mypage-container {
  display: flex;
  background-color: #f4f4f4;
  min-height: 100vh;
}

.mypage-content {
  flex-grow: 1;
  padding: 40px;
}

.page-title {
  font-size: 28px;
  color: #2e7d32;
  margin-bottom: 30px;
  font-weight: bold;
}

.no-data {
  text-align: center;
  color: #888;
  font-size: 16px;
  padding: 50px 0;
}

.reservation-cards {
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.card {
  display: flex;
  background-color: #fff;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s ease;
}

.card:hover {
  transform: translateY(-3px);
}

.card-img img {
  width: 200px;
  height: 100%;
  object-fit: cover;
}

.card-info {
  flex: 1;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.title {
  font-size: 20px;
  font-weight: bold;
  color: #006400;
  margin-bottom: 6px;
}

.desc {
  font-size: 14px;
  color: #555;
  margin-bottom: 10px;
}

.info-bottom p {
  margin: 4px 0;
  font-size: 14px;
}

.price {
  color: #d32f2f;
  font-weight: bold;
  font-size: 20px;
  margin-top: 8px;
}

.cancel-btn {
  align-self: flex-start;
  margin-top: 15px;
  padding: 8px 16px;
  background-color: #e53935;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.2s ease;
}

.cancel-btn:hover {
  background-color: #b71c1c;
}
</style>

<template>
  <div class="container">
    <!-- 사이드바 -->
    <div class="sidebar">
      <h2>마이페이지</h2>
      <router-link to="/mypage">마이페이지 홈</router-link>
      <router-link to="/mypage/myinfo">개인정보 관리</router-link>
      <router-link to="/mypage/reservations">예약 관리</router-link>
    </div>

    <!-- 예약 내역 -->
    <div class="content">
      <h2>예약 내역</h2>
      <div v-if="reservations.length === 0">예약 내역이 없습니다.</div>
      <div v-else class="reservation-list">
        <div class="reservation" v-for="reservation in reservations" :key="reservation.id">
          <p><strong>패키지:</strong> {{ reservation.title }}</p>
          <p><strong>여행지:</strong> {{ reservation.destination }}</p>
          <p><strong>가격:</strong> {{ reservation.price.toLocaleString() }}원</p>
          <p><strong>기간:</strong> {{ reservation.duration }}</p>
          <button class="btn danger" @click="cancelReservation(reservation.id)">예약 취소</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
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
      try {
        const res = await axios.get('http://localhost:9876/api/reservation');
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
        this.fetchReservations(); // 취소 후 목록 새로고침
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
  max-width: 1000px;
  margin: 20px auto;
}
.sidebar {
  width: 250px;
  border-right: 2px solid #ddd;
  padding: 20px;
}
.sidebar a {
  display: block;
  padding: 10px;
  text-decoration: none;
  color: black;
}
.sidebar a:hover {
  background: #f0f0f0;
}
.content {
  flex-grow: 1;
  padding: 20px;
}
h2 {
  color: #008000;
}
.reservation-list {
  margin-top: 20px;
}
.reservation {
  border: 1px solid #ccc;
  padding: 15px;
  margin-bottom: 15px;
  border-radius: 6px;
  background: #f9f9f9;
}
p {
  margin: 5px 0;
}
.btn {
  background: #008000;
  color: white;
  padding: 8px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.btn:hover {
  background: #006400;
}
.danger {
  background: #ff4444;
  margin-top: 10px;
}
.danger:hover {
  background: #cc0000;
}
</style>

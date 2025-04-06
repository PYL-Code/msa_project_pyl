package edu.du.msa_project_pyl.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 여행 패키지 제목

    @Column(nullable = false)
    private String destination; // 여행지

    @Column(nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private String duration; // 여행 기간 (일 단위)
}

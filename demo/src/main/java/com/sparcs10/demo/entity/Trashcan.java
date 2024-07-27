package com.sparcs10.demo.entity;

import com.sparcs10.demo.dto.TrashcanDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "trashcan")
@NoArgsConstructor
public class Trashcan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String address;

    @Column
    private String types; // 쓰레기통 종류. , 로 구분

    @Builder
    public Trashcan(String address, String types) {
        this.address = address;
        this.types = types;
    }

    public static Trashcan fromDTO(TrashcanDTO dto) {
        Trashcan entity = new Trashcan();
        entity.setAddress(dto.getAddress());
        entity.setTypes(String.join(",", dto.getTypes()));
        return entity;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}

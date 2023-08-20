package kuit.subway.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kuit.subway.dto.SaveLineReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Line {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private Long distance;
    private String name;

    private Long downStationId;
    private Long upStationId;

    public Line(SaveLineReq saveLineReq){
        this.color = saveLineReq.getColor();
        this.distance = saveLineReq.getDistance();
        this.name = saveLineReq.getName();
        this.downStationId = saveLineReq.getDownStationId();
        this.upStationId = saveLineReq.getUpStationId();
    }
}

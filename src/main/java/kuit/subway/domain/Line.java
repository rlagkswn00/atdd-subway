package kuit.subway.domain;

import jakarta.persistence.*;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.UpdateLineReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Line extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private Long distance;
    private String name;

    @ManyToOne
    @JoinColumn(name = "down_station_id")
    private Station downStation;

    @ManyToOne
    @JoinColumn(name = "up_station_id")
    private Station upStation;

    public void updateLine(UpdateLineReq updateLineReq, Station upStation, Station downStation) {
        this.name = updateLineReq.getName();
        this.distance = updateLineReq.getDistance();
        this.downStation = upStation;
        this.upStation = downStation;
        this.color = updateLineReq.getColor();
    }
}

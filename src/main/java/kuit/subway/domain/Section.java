package kuit.subway.domain;

import jakarta.persistence.*;
import kuit.subway.domain.BaseEntity;
import kuit.subway.domain.Line;
import kuit.subway.domain.Station;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Section extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "up_station_id")
    private Station upStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "down_station_id")
    private Station downStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private Line line;

    public void setLine(Line line){
        this.line = line;
    }

    @Builder
    public Section(Station upStation, Station downStation){
        this.upStation = upStation;
        this.downStation = downStation;
    }
}
